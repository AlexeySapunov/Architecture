package ru.gb.patterns.relationObject;

import org.springframework.util.Assert;

import java.util.*;

public class UnitOfWork {

    Map<Class<? extends DomainObject<?>>, Map<Object, DomainObject<?>>> identityMaps = new HashMap<>();

    private static final ThreadLocal<UnitOfWork> current = new ThreadLocal<>();

    public static void newCurrent() {
        setCurrent(new UnitOfWork());
    }

    public static void setCurrent(UnitOfWork unitOfWork) {
        current.set(unitOfWork);
    }

    public static UnitOfWork getCurrent() {
        return current.get();
    }


    private final List<DomainObject<?>> newObjects = new ArrayList<>();
    private final List<DomainObject<?>> dirtyObjects = new ArrayList<>();
    private final List<DomainObject<?>> removedObjects = new ArrayList<>();



    public void registerNew(DomainObject<?> obj) {
        Assert.isTrue(!getCurrent().dirtyObjects.contains(obj), "object not dirty");
        Assert.isTrue(!getCurrent().removedObjects.contains(obj), "object not removed");
        Assert.isTrue(!getCurrent().newObjects.contains(obj),"object not already registered new");
        getCurrent().newObjects.add(obj);
    }

    public void registerDirty(DomainObject<?> obj) {
        Assert.notNull(obj.getId(), "id not null");
        Assert.isTrue(!getCurrent().removedObjects.contains(obj), "object not removed");
        if (!getCurrent().dirtyObjects.contains(obj) && !getCurrent().newObjects.contains(obj)) {
            getCurrent().dirtyObjects.add(obj);
        }
    }

    public void registerRemoved(DomainObject<?> obj) {
        Assert.notNull(obj.getId(), "id not null");
        if (getCurrent().newObjects.remove(obj)) return;
        getCurrent().dirtyObjects.remove(obj);
        if (!getCurrent().removedObjects.contains(obj)) {
            getCurrent().removedObjects.add(obj);
        }
    }

    public void commit() {
        insertNew();
        updateDirty();
        deleteRemoved();
    }

    private void deleteRemoved() {
        for (DomainObject<?> object : removedObjects) {
            var mapper = Registry.getInstance().getMapper(object.getClass());
            mapper.delete(object.getId());
        }
    }

    private void updateDirty() {
        for (DomainObject<?> object : dirtyObjects) {
            var mapper = Registry.getInstance().getMapper(object.getClass());
            mapper.update(object);
        }
    }

    private void insertNew() {
        for (DomainObject<?> object : newObjects) {
            var mapper = Registry.getInstance().getMapper(object.getClass());
            mapper.insert(object);
        }
    }


    public static <T extends DomainObject<ID>, ID> void addDomainObject(T object) {
        getCurrent().identityMaps.computeIfAbsent((Class<T>) object.getClass(), k -> new HashMap<>())
                .put(object.getId(), object);

    }

    public static <T extends DomainObject<ID>, ID> Optional<T> getDomainObject(ID id, Class<T> clazz) {
        return Optional.ofNullable((T) getCurrent().identityMaps.computeIfAbsent(clazz, k -> new HashMap<>()).get(id));

    }
}
