package instagram.controllers.MVCControllers;

import instagram.models.Authorities;
import instagram.models.Post;
import instagram.models.User;
import instagram.services.AuthoritiesService;
import instagram.services.NotificationService;
import instagram.services.PostService;
import instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by aleksandar on 3.10.16.
 */
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private PostService postService;

    @RequestMapping("users/editProfile/{id}")
    public String viewMyProfile(@PathVariable(value = "id") Long id, Model model, Principal principal){
        User user = userService.findById(id);

        Authorities auth = new Authorities();
        auth = authoritiesService.findByUsername(principal.getName());

        if (user == null) {
            notifyService.addErrorMessage("Cannot find user #" + user.getId());
            return "redirect:/";
        }

        if(!(user.getUsername().equals(principal.getName())) && !(auth.getAuthority().equals("ROLE_ADMIN"))){
            notifyService.addErrorMessage("You don't have access to this post.");
            return "redirect:/";
        }


        model.addAttribute("user",user);

        return "users/editProfile";
    }

    @RequestMapping(value = "users/editProfile" , method = RequestMethod.POST)
    public String editMyProfile(@ModelAttribute User user, BindingResult bindingResult, Model model, Principal principal){

        Authorities auth;
        auth = authoritiesService.findByUsername(principal.getName());
        User usr = userService.findByUsername(principal.getName());

        if(bindingResult.hasErrors()){
            notifyService.addErrorMessage("Error editing :\nError message: \n\n" + bindingResult.toString());
            return "redirect:/";
        }
        else if(user.getId()==null){
            notifyService.addErrorMessage("Error: no id " + user.getId() + " ");
            return "redirect:/";
        }
        else if(!(usr.getUsername().equals(principal.getName())) && !(auth.getAuthority().equals("ROLE_ADMIN")) ){
            notifyService.addErrorMessage("You don't have access to this page.");
            return "redirect:/";

        }else if(user.getAboutMe().length()>140){
            notifyService.addErrorMessage("About me should't be longer than 140 characters.");
            return "redirect:/users/editProfile/" + user.getId();
        }
        else {
            User editedUser = userService.findById(user.getId());
            editedUser.setAboutMe(user.getAboutMe());
            editedUser.setFullName(user.getFullName());
            editedUser.setEnabled(user.getEnabled());
            userService.edit(editedUser);
            notifyService.addInfoMessage("Successful Edit!");
            return "redirect:/";

        }


    }

    @RequestMapping("/users/profile/{id}")
    public String viewProfile(@PathVariable(value = "id")Long id, Model model){
        User user = userService.findById(id);
        List<Post> posts =  postService.FindByUser(id);

        model.addAttribute("user",user);
        model.addAttribute("posts",posts);


        return "users/profile";
    }

    @RequestMapping("/users/myprofile")
    public String myProfile(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);

        return "/users/myprofile";
    }

}
