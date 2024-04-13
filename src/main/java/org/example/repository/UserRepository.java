package org.example.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.config.ConnectionHolder;
import org.example.entity.User;
import org.example.utils.UserMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Getter
@RequiredArgsConstructor
public class UserRepository {
    private final ConnectionHolder connectionHolder;
    private final UserMapper userMapper;

    public void create(User user) {
        String sqlRequest = """
                insert into users (nickname, description, avatar, total_experience)
                values('%s', '%s', '%s', '%d');
                """.formatted(
                user.getNickname(), user.getDescription(), user.getAvatar(), user.getTotalExp()
        );
        try (Statement statement = connectionHolder.getConnection().createStatement()) {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(long userId) {
        String sqlRequest = """
                delete from users where id = '%d';
                """.formatted(userId);
        try (Statement statement = connectionHolder.getConnection().createStatement()) {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User read(long userId) {
        User user = new User();
        String sqlRequest = """
                select * from users where id = '%d';
                """.formatted(userId);
        try (Statement statement = connectionHolder.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            user = userMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void update(User updateUser) {
        String sqlRequest = """
            update users
            set nickname = ?,
                description = ?,
                avatar = ?,
                total_experience = ?
            where id = ?
            """;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, updateUser.getNickname());
            statement.setString(2, updateUser.getDescription());
            statement.setString(3, updateUser.getAvatar());
            statement.setInt(4, updateUser.getTotalExp());
            statement.setLong(5, updateUser.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
