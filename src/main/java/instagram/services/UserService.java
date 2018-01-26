package instagram.services;

import instagram.models.User;

import java.util.List;

/**
 * Created by aleksandar on 11.8.16.
 */
public interface UserService {

    boolean authenticate(String username, String password);
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
    User findByUsername(String username);

}
