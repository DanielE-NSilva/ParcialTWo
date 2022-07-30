package co.com.poli.usersservice.service;

import co.com.poli.usersservice.persistence.entity.User;
import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Long id);
    User delete(Long id);
    List<User> findAll();
}