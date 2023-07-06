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

        Users currentUser = UsersBLL.getLogin(user.getEmail(), user.getPassword());

        if (currentUser != null) {
            if (currentUser.getIdRole() == 4) {
                session.setAttribute("userId", currentUser.getId());
                session.setAttribute("userName", currentUser.getName());
                return "redirect:/home";
            } else {
                result.rejectValue("password", "error.user", "User cannot access this app");
                model.addAttribute("errorMessage", "User cannot access this app");
                return "login";
            }
        } else {
            result.rejectValue("password", "error.user", "Invalid email or password");
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

}
