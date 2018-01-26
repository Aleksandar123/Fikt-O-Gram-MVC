package instagram.controllers.MVCControllers;

import com.hazelcast.core.HazelcastInstance;
import instagram.models.Comment;
import instagram.models.Post;
import instagram.services.CommentService;
import instagram.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.String.format;
/**
 * Created by aleksandar on 9.8.16.
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    private HazelcastInstance instance;


    @RequestMapping(value = "/" )
    public String index(Model model){
       // List<Post> allposts = postService.findAll();
       //HAZELCAST LOGGING
        //String logFormat = "%s call took %d millis with result: %s";
        //long start1 = nanoTime();


        List<Post> allposts = postService.findAllByOrderByDateDesc();
        //List<Pageable> allposts = postService.findAll(pageable);

        List<Comment> comments = commentService.findAll();
        int commentCount = comments.size();

        //model.addAttribute("allPosts", postService.findAll(new PageRequest(0, 10, Sort.Direction.DESC, "date")));
        model.addAttribute("allPosts",allposts);
        model.addAttribute("comments",commentCount);

        //long end1 = nanoTime();
        //LOGGER.info(format(logFormat, "ALLPOSTS: ", TimeUnit.NANOSECONDS.toMillis(end1 - start1), allposts));
        //System.out.println("[allposts]: HAZELCAST TIME: " + TimeUnit.NANOSECONDS.toMillis(end1-start1) + "\n" +  "[HAZELCAST Cluster info] :  " + instance.getCluster().getClusterState() + " " + instance.getCluster().getMembers() + " " + instance.getCluster().getClusterTime());

        return "index";
    }
}
