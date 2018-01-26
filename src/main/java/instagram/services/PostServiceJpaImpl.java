package instagram.services;

import instagram.models.Post;
import instagram.models.User;
import instagram.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PostServiceJpaImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }



    @Override
    public List findAllByOrderByDateDesc() {
        return this.postRepo.findAllByOrderByDateDesc();
    }

    @Override
    public Post findById(Long id) {
        return this.postRepo.findOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepo.save(post);
    }

    @Override
    public List<Post> FindByUser(Long id) {


        return postRepo.FindAllByUserId(id);
    }

    @Override
    public Post edit(Post post) {
        return this.postRepo.save(post);
    }

    @Override
    public void deleteById(Long id) {
        this.postRepo.delete(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return this.postRepo.findAll(pageable);
    }

    public Post findOne(Long id){
        return postRepo.findOne(id);
    }

}

