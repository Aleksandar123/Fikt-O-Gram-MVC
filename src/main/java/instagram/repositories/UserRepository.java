package instagram.repositories;

import instagram.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public final static String FIND_BY_USER_USERNAME = "SELECT u " +
            "FROM User u " + "WHERE u.username = :username";

    @Query(FIND_BY_USER_USERNAME)
    User FindUserByUsername(@Param("username")  String username);

}
