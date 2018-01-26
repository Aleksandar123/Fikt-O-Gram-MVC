package instagram.services;

import instagram.models.Comment;
import instagram.models.Post;
import instagram.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private List<Post> posts = new ArrayList<Post>() {{
    add(new Post(1L, "First Image", "/userImages/1.jpg", new User(1L, "aleksandar", "Aleksandar Nikolov")));
        add(new Post(2L, "Second Image", "/userImages/5.jpg", new User(2L, "nikolov", "Nikolov Aleksandar")));
        add(new Post(3L, "Third Image", "/userImages/2.jpg", new User(1L, "aleksandar", "Aleksandar Nikolov")));
        add(new Post(4L, "Forth Image", "/userImages/3.jpg", new User(1L, "aleksandar", "Aleksandar Nikolov")));
        add(new Post(5L, "Fifth Image", "/userImages/4.jpg", new User(2L, "nikolov", "Nikolov Aleksandar")));
        add(new Post(6L, "Test Image", "/userImages/1471992418723_6.jpg", new User(2L, "nikolov", "Nikolov Aleksandar")));


    }};



    @Override
    public List<Post> findAll() {
        return this.posts;
    }

    @Override
    public List findAllByOrderByDateDesc() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return this.posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(Post post) {
/*        post.setId(this.posts.stream().mapToLong(
                p -> p.getId()).max().getAsLong() + 1);*/
        post.setId(this.posts.size()+1L);
        this.posts.add(post);
        return post;
    }

    @Override
    public List<Post> FindByUser(Long id) {
        return null;
    }


    public List<Post> FindByUser(User user) {
        ArrayList<Post> postsByuser = new ArrayList<Post>();

        for(Post p : this.posts){
            if(p.getUploader().getId()==user.getId()){
                postsByuser.add(p);
            }
        }

        return postsByuser;
        /*return (List<Post>)this.posts.stream()
                .filter(p -> Objects.equals(p.getUploader().getId(), user.getId()));*/
    }

    @Override
    public Post edit(Post post) {
        for (int i = 0; i < this.posts.size(); i++) {
            if (Objects.equals(this.posts.get(i).getId(), post.getId())) {
                this.posts.set(i, post);
                return post;
            }
        }
        throw new RuntimeException("Post not found: " + post.getId());
    }

    @Override
    public void deleteById(Long id) {
        for (int i = 0; i < this.posts.size(); i++) {
            if (Objects.equals(this.posts.get(i).getId(), id)) {
                this.posts.remove(i);
                return;
            }
        }
        throw new RuntimeException("Post not found: " + id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Post findOne(Long id) {
        return null;
    }

    public List<Comment> commentsOnPost(Post post){
        ArrayList<Comment> commentsOnPost = new ArrayList<Comment>();
        for(Comment c : post.getComments()){
            commentsOnPost.add(c);
        }

        return commentsOnPost;
    }
}

