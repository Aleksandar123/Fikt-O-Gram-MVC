package instagram.repositories;

import instagram.models.Post;
import instagram.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public final static String FIND_BY_USER_ID = "SELECT p " +
            "FROM Post p LEFT JOIN p.uploader u " +
            "WHERE u.id = :id";


    @Query(FIND_BY_USER_ID)
    List<Post> FindAllByUserId(@Param("id")  Long id);

    @Query("SELECT p FROM Post p ORDER BY date DESC")
    List findAllByOrderByDateDesc();

    Page<Post> findAll(Pageable pageable);
}
