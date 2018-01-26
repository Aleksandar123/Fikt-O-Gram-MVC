package instagram.controllers.MVCControllers;

import instagram.models.Authorities;
import instagram.models.Comment;
import instagram.models.Post;
import instagram.models.User;
import instagram.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by aleksandar on 10.8.16.
 */
@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @ModelAttribute("comment")
    public Comment comment(){
        return new Comment();
    }

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Post post = postService.findById(id);
        List<Comment> comments = commentService.findByPostId(id);

        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }

        model.addAttribute("post", post);
        model.addAttribute("comments",comments);
        return "posts/view";

    }

    @RequestMapping(value = "/posts/view/{id}" , method = RequestMethod.POST)
    public String postComment(@PathVariable("id") Long id, @ModelAttribute Comment comment, BindingResult bindingResult, Model model, Principal principal){

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "redirect:/";
        }
        comment.setCommentor(userService.findByUsername(principal.getName()));
       comment.setImage(postService.findById(id));
        commentService.create(comment);

        model.addAttribute("comment", comment);
        return "redirect:/posts/view/{id}";
    }


    @RequestMapping("/users/posts/{id}")
    public String viewPostsByUser(@PathVariable("id") Long id, Model model){
        User user = new User();
        user.setId(id);
        List<Post> postsByUser = postService.FindByUser(user.getId());
        if(postsByUser == null){
            notifyService.addErrorMessage("Cannot find user or no posts");
            return "redirect:/";
        }

        model.addAttribute("postsByUser", postsByUser);
        return "users/posts";
    }





    @RequestMapping("/users/myposts/")
    public String viewMyPosts(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Post> postsByUser = postService.FindByUser(user.getId());
        if(postsByUser.isEmpty()){
            notifyService.addErrorMessage("Cannot find user or no posts");
            return "redirect:/";
        }
        if(!(user.getUsername().equals(principal.getName()))){
            notifyService.addErrorMessage("You don't have access to this page.");

            return "redirect:/";
        }

        model.addAttribute("postsByUser", postsByUser);

        return "users/myposts";
    }

    @RequestMapping(value = "/posts/edit/{id}" , method = RequestMethod.GET)
    public String testPost(@PathVariable Long id, Model model, Principal principal){
        Post post = postService.findById(id);

        Authorities auth = new Authorities();
        auth = authoritiesService.findByUsername(principal.getName());

        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }

        if(!(post.getUploader().getUsername().equals(principal.getName())) && !(auth.getAuthority().equals("ROLE_ADMIN"))){
            notifyService.addErrorMessage("You don't have access to this post.");
            return "redirect:/";
        }

        model.addAttribute("post", post);

        return "posts/edit";
    }

    @RequestMapping(value = "/posts/edit" , method = RequestMethod.POST)
    public String editPost(@ModelAttribute Post post, BindingResult bindingResult, Model model, Principal principal){

        Authorities auth = new Authorities();
        auth = authoritiesService.findByUsername(principal.getName());

        if(bindingResult.hasErrors()){
            notifyService.addErrorMessage("Error editing :\nError message: \n\n" + bindingResult.toString());
            return "redirect:/";
        }
        else if(post.getId()==null){
            notifyService.addErrorMessage("Error: no id " + post.getId() + " " + post.getDescription() );
            return "redirect:/";
        }
            else if(!(post.getUploader().getUsername().equals(principal.getName())) && !(auth.getAuthority().equals("ROLE_ADMIN")) ){
            notifyService.addErrorMessage("You don't have access to this post.");
        }
        else{

            Post editedPost;
            editedPost = postService.findById(post.getId());
            editedPost.setDescription(post.getDescription());

            postService.edit(editedPost);

            notifyService.addInfoMessage("Successful Edit!");
        }


        return "redirect:/";
    }

    @RequestMapping(value = "/posts/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id, Principal principal){
        Post post = postService.findById(id);
        List<Comment> comments = new ArrayList<>();
        Authorities auth = new Authorities();
        auth = authoritiesService.findByUsername(principal.getName());

        if(post.getUploader().getUsername().equals(principal.getName()) || (auth.getAuthority().equals("ROLE_ADMIN"))) {
            //File fileDelete = new File("/home/aleksandar/Development/Diplomska-2-0/target/classes/public/" + post.getImgLocation());
            //System.out.println("delete: " + fileDelete.getAbsolutePath() );
            //fileDelete.delete();
            comments = commentService.findByPostId(post.getId());
            for (Comment com : comments) {
                System.out.print("Deleted comment "+ com.getId());
                commentService.deleteById(com.getId());
                System.out.print("Deleted comment "+ com.getId());
                
            }

            postService.deleteById(id);
            notifyService.addInfoMessage("Post successfully deleted!");

            //notifyService.addInfoMessage("" + comments.size());

           // notifyService.addInfoMessage("Comments successfully deleted!");
        }
        else{
            notifyService.addErrorMessage("You don't have access to this post.");
        }
        return "redirect:/";
    }
}
