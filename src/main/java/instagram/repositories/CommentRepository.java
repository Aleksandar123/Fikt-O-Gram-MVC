package instagram.repositories;

import instagram.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
    public final static String FIND_BY_POST_ID = "SELECT c " +
            "FROM Comment c LEFT JOIN c.image p " +
            "WHERE p.id = :id";

    public final static String FIND_BY_USERNAME = "SELECT c " +
            "FROM Comment c LEFT JOIN c.commentor u " +
            "WHERE u.username = :username";

   public final static String DELETE_BY_POST_ID="DELETE c" + 
                "FROM Comment c LEFT JOIN c.image p" + 
                "WHERE p.id = :id";

    @Query(FIND_BY_POST_ID)
    List<Comment> findAllByPostId(@Param("id") Long id);

    @Query(FIND_BY_USERNAME)
    List<Comment> findByUsername(@Param("username")String username);

   // @Query(DELETE_BY_POST_ID)
   // void deleteCommentsFromPost(@Param("id")Long id);

}
