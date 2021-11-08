package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public List<User> adminPage() {
        return userService.listUsers();
    }

    @GetMapping("/userInfo")
    public User userInfo(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) userService.add(user);
    }

    @PatchMapping("/edit")
    public void editUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) userService.update(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userService.remove(user);
    }
}
