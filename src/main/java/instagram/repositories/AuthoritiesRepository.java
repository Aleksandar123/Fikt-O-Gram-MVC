package instagram.repositories;

import instagram.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandar on 2.9.16.
 */
@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    public final static String FIND_BY_USER_USERNAME = "SELECT a " +
            "FROM Authorities a " + "WHERE a.username = :username";

    @Query(FIND_BY_USER_USERNAME)
    Authorities findAuthorityByUsername(@Param("username")  String username);
}
