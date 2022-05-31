package ru.gb.patterns.relationObject;

public abstract class DomainObject<ID> {

    public abstract ID getId();

    protected void markNew() {
        UnitOfWork.getCurrent().registerNew(this);
    }

    protected void markDirty() {
        UnitOfWork.getCurrent().registerDirty(this);
    }
    protected void markRemoved() {
        UnitOfWork.getCurrent().registerRemoved(this);
    }
}
