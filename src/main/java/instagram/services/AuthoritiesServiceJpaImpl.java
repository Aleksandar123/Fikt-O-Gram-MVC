package instagram.services;

import instagram.models.Authorities;
import instagram.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AuthoritiesServiceJpaImpl implements AuthoritiesService {
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public Authorities findById(Long id) {
        return this.authoritiesRepository.findOne(id);
    }

    @Override
    public Authorities create(Authorities authorities) {
        return this.authoritiesRepository.save(authorities);
    }

    @Override
    public Authorities edit(Authorities authorities) {
        return this.authoritiesRepository.save(authorities);
    }

    @Override
    public Authorities findByUsername(String username) {
        return this.authoritiesRepository.findAuthorityByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        this.authoritiesRepository.delete(id);
    }
}
