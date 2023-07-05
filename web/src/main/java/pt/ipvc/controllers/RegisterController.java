package pt.ipvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Users;
import pt.ipvc.models.RegisterModel;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegisterModel());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerSubmit(@ModelAttribute("user") @Valid RegisterModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        boolean emailNotExist = UsersBLL.checkEmail(user.getEmail());
        boolean phoneNotExist = UsersBLL.checkPhone(user.getPhone());

        if (!emailNotExist) {
            result.rejectValue("email", "error.user", "The email is already in use!");
            model.addAttribute("errorMessage", "The email is already in use!");
            return "register";
        } else if (!phoneNotExist) {
            result.rejectValue("phone", "error.user", "The phone is already in use!");
            model.addAttribute("errorMessage", "The phone is already in use!");
            return "register";
        } else if (user.getName().isBlank() || user.getEmail().isBlank() || user.getPhone().isBlank() || user.getPassword().isBlank()) {
            result.rejectValue("error.user", "You have to fill all the fields");
            model.addAttribute("errorMessage", "You have to fill all the fields");
            return "register";
        } else if (emailNotExist && phoneNotExist) {
            Users newUser = new Users();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPhone(user.getPhone());
            newUser.setPassword(user.getPassword());
            newUser.setRoleByIdRole(RoleBLL.get(4));
            UsersBLL.create(newUser);


            return "redirect:/login";
        }

        return "register";
    }
}


