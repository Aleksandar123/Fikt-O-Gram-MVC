package instagram.services;

import instagram.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceStubImpl implements UserService {

    private List<User> users = new ArrayList<User>() {{
        add(new User(1L, "aleksandar", "Aleksandar Nikolov"));
        add(new User(2L, "nikolov", "Nikolov Aleksandar"));
    }};

    @Override
    public boolean authenticate(String username, String password) {
        // Provide a sample password check: username == password
        return Objects.equals(username, password);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User edit(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    /*@Override
    public void newUser(User user) {

    }

    @Override
    public User findUserById(Long id) {
        return this.users.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }*/
}
