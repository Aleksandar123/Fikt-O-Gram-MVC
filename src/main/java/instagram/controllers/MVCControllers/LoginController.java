package instagram.controllers.MVCControllers;

import instagram.forms.LoginForm;
import instagram.forms.RegisterForm;
import instagram.models.Authorities;
import instagram.models.User;
import instagram.services.AuthoritiesService;
import instagram.services.NotificationService;
import instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private AuthoritiesService authService;

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @RequestMapping("/login")
    public String Login(Model model, Principal principal){
        if (principal!=null){
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("users/register")
    public String register(User user,Principal principal) {
        if (principal!=null){
            return "redirect:/";
        }
        return "users/register";
    }


    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String registerPage(@ModelAttribute @Valid User user,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "users/register";
        }else if(userService.findByUsername(user.getUsername())!=null){
            notifyService.addErrorMessage("Username already exists. Please try another one");
            return "users/register";
        }
        else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Authorities authr = new Authorities();
            authr.setAuthority("ROLE_USER");
            user.setAuthorities("USER");
            authr.setUsername(user.getUsername());
            //Bcrypt Test
            String bcryptpass = encoder.encode(user.getPassword());
            System.out.println("bcryptpass: " + bcryptpass);
            user.setPassword(bcryptpass);
            if(user.getId() == null){
                userService.create(user);
                authService.create(authr);

            }
        }


        notifyService.addInfoMessage("Registration successful " + user.getUsername() + " successfully created!");
        return "redirect:/";
    }

}
