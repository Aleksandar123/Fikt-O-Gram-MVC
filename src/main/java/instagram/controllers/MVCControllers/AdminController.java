package instagram.controllers.MVCControllers;

import instagram.models.Authorities;
import instagram.models.Post;
import instagram.models.User;
import instagram.services.AuthoritiesService;
import instagram.services.PostService;
import instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandar on 3.10.16.
 */

@Controller
public class AdminController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @RequestMapping("/admin")
    public String adminHome(){

        return "admin";
    }

    @RequestMapping("/admin/users")
    public String adminViewAllUsers(Model model){
        List<User> users = userService.findAll();
        List<Post> posts = postService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts.size());


        return "admin/users";
    }

    @RequestMapping("/admin/user/{id}")
    public String adminViewUser(@PathVariable("id") Long id, Model model){
        User user = new User();
        List<Post> posts = new ArrayList<>();
        user = userService.findById(id);
        posts = postService.FindByUser(id);
        model.addAttribute("user",user);
        model.addAttribute("posts",posts);
        return "admin/user";

    }

}
