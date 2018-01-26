package instagram.services;

import instagram.models.Comment;
import instagram.models.Post;


import java.util.List;

/**
 * Created by aleksandar on 26.8.16.
 */
public interface CommentService {

    List<Comment> findAll();
    Comment findById(Long id);
    Comment create(Comment comment);
    Comment edit(Comment comment);
    void deleteById(Long id);
    List<Comment> findByPostId(Long id);
    List<Comment> findByUsername(String username);
    void deleteCommentsFromPost(Post post);
}
