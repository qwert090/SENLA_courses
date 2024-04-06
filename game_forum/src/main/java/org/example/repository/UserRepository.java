package org.example.repository;

import lombok.Getter;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void create(User user) {
        users.add(user);
    }

    public void delete(long userId) {
        users = users.stream()
                .filter(user -> user.getId() != userId)
                .toList();
    }

    public Optional<User> read(long userId) {
        Optional<User> readUser = users.stream()
                .filter(id -> id.getId() == userId)
                .findFirst();
        return readUser;
    }

    public void update(User updateUser){
         users = users.stream()
                 .filter(user -> user.getId() == updateUser.getId())
                 .map(user -> updateUser)
                 .toList();
    }
}
