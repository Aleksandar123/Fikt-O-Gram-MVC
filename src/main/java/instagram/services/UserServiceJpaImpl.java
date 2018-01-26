package instagram.services;

import instagram.models.User;
import instagram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandar on 26.8.16.
 */
@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepo.findOne(id);
    }

    @Override
    public User create(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepo.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepo.FindUserByUsername(username);
    }
}
