package ru.gb.patterns.relationObject;

import ru.gb.patterns.proxy.User;

import java.sql.*;
import java.util.Optional;

public class UserMapperImpl implements UserMapper {

    public UserMapperImpl() {
    }

    @Override
    public Optional<User> find(Long id) {
        System.out.println("execute request");
        try (Connection connection = DBHolder.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, username, password, job_id from user where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error", ex);
        }
        return Optional.empty();
    }

    @Override
    public Long insert(User object) {
        try (Connection connection = DBHolder.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, job_id) " +
                    "VALUES (?, ?) RETURNING *");
            preparedStatement.setString(1, object.getUsername());
            preparedStatement.setLong(2, object.getJobId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error", ex);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = DBHolder.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Error", ex);
        }
    }

    @Override
    public boolean update(User object) {
        try (Connection connection = DBHolder.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET username = ?, job_id = ? where id = ?");
            preparedStatement.setString(1, object.getUsername());
            preparedStatement.setLong(2, object.getJobId());
            preparedStatement.setLong(3, object.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Error", ex);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        try (Connection connection = DBHolder.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, username, job_id from user where username = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error", ex);
        }
        return Optional.empty();
    }
}
