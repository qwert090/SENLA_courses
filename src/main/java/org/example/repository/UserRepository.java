package org.example.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.config.ConnectionHolder;
import org.example.entity.User;
import org.example.utils.UserMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try(Statement statement = connectionHolder.getConnection().createStatement()) {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(long userId) {
        String sqlRequest = """
                delete from users where id = '%d';
                """.formatted(userId);
        try(Statement statement = connectionHolder.getConnection().createStatement()) {
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
        try(Statement statement = connectionHolder.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            user = userMapper.toUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void update(User updateUser){
         String sqlRequest = """
                 update users
                    set nickname = '%s',
                    set description = '%s',
                    set avatar = '%s',
                    set total_experience = '%d'
                 where id = '%d'
                 """.formatted(
                         updateUser.getNickname(), updateUser.getDescription(), updateUser.getAvatar(), updateUser.getTotalExp(), updateUser.getId()
                                );
         try(Statement statement = connectionHolder.getConnection().createStatement()) {
             statement.execute(sqlRequest);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }
}
