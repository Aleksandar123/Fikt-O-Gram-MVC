package instagram.services;

import instagram.models.Authorities;

/**
 * Created by aleksandar on 2.9.16.
 */
public interface AuthoritiesService {

    Authorities findById(Long id);
    Authorities create(Authorities authorities);
    Authorities edit(Authorities authorities);
    Authorities findByUsername(String username);
    void deleteById(Long id);
}
