package pt.ipvc.controllers;

import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Users;
import pt.ipvc.models.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginModel());
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSubmit(@Valid @ModelAttribute("user") LoginModel user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

        if (UsersBLL.checkLogin(user.getEmail(), user.getPassword())) {
            Users currentUser = UsersBLL.getLogin(user.getEmail(), user.getPassword());
            session.setAttribute("userName", currentUser.getName());
            return "redirect:/register";
        } else {
            result.rejectValue("password", "error.user", "Invalid email or password");
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }
}
