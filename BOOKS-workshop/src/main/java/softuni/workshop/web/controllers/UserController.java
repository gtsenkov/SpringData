package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;

//@RequestMapping("/users")
@Controller
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public ModelAndView register() {
        //return super.view("user/register");
        return new ModelAndView("user/register");
    }

    @PostMapping("/users/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterModel userRegisterModel) {

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            //return super.redirect("/users/register");
            return new ModelAndView("redirect:/users/register");
        }

        this.userService.registerUser(userRegisterModel);
        //return super.redirect("/users/login");
        return new ModelAndView("redirect:/users/login");
    }

    @GetMapping("/users/login")
    public ModelAndView login() {
        return super.view("user/login");
    }
}

