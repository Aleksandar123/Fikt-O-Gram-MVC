package instagram.services;

import instagram.models.Post;
import instagram.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aleksandar on 9.8.16.
 */
public interface PostService {

    List<Post> findAll();
    List findAllByOrderByDateDesc();
    Post findById(Long id);
    Post create(Post post);
    List<Post> FindByUser(Long id);
    Post edit(Post post);
    void deleteById(Long id);
    Page<Post> findAll(Pageable pageable);
    Post findOne(Long id);

}
