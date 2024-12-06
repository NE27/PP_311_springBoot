package pp_311_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp_311_springboot.model.User;
import pp_311_springboot.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }


    @GetMapping("/users/edit")
    public String edit(Model model, @RequestParam("id") long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/users/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @PostMapping("/users/delete")
    public String deleteUserById(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @PostMapping("/users/deleteAll")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "redirect:/";
    }
}
