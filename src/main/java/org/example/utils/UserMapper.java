package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.example.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public Users toUser(ResultSet resultSet){
        Users user = new Users();
        try {
            if(resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickname"));
                user.setDescription(resultSet.getString("description"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setTotalExp(resultSet.getInt("total_experience"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
