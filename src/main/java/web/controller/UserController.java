package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.security.Principal;

@Controller
//@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

	@GetMapping("admin")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("admin/users")
	public String usersShow(Model model) {
		model.addAttribute("users", userService.listUsers());
		return "users";
	}

	@GetMapping("admin/users/add")
	public String newUser(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("roles", userService.getRoles());
		return "add";
	}
	@PostMapping("admin/users/add")
	public String addUser(@ModelAttribute("user") User user){
		userService.add(user);
		return "redirect:/admin/users";
	}

	@GetMapping("admin/users/{id}/edit")
	public String showUser(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", userService.show(id));
		model.addAttribute("roles", userService.getRoles());
		return "edit";
	}

	@PatchMapping("admin/users/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.update(id, user);
		return "redirect:/admin/users";
	}

	@DeleteMapping("admin/users/{id}")
	public String removeUser(@PathVariable("id") long id) {
		userService.remove(id);
		return "redirect:/admin/users";
	}

	@RequestMapping("user")
	public String userPage(Principal principal, Model model) {
		model.addAttribute("user", userService.findByName(principal.getName()));
		return "user";
	}

}