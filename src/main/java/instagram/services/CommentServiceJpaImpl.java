package instagram.services;

import instagram.models.Comment;
import instagram.models.Post;

import instagram.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


/**
 * Created by aleksandar on 26.8.16.
 */
@Service
@Primary
public class CommentServiceJpaImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> findAll() {
        return this.commentRepo.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return this.commentRepo.findOne(id);
    }

    @Override
    public Comment create(Comment comment) {
        return this.commentRepo.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return this.commentRepo.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepo.delete(id);
    }

    @Override
    public List<Comment> findByPostId(Long id) {
        return this.commentRepo.findAllByPostId(id);
    }

    @Override
    public List<Comment> findByUsername(String username) {
        return commentRepo.findByUsername(username);
    }

    @Override
    public void deleteCommentsFromPost(Post post){
        List<Comment> allComments = new ArrayList<Comment>();
        allComments = this.findByPostId(post.getId());

        for (Comment com : allComments) {
            this.deleteById(com.getId());
            System.out.print("Deleted comment "+ com.getId());
            
        }
    };
}
