package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String starterPage() { return "login"; }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

	@GetMapping("admin")
	public String adminPage(Principal principal, Model model) {
		model.addAttribute("user", userService.findByEmail(principal.getName()));
		model.addAttribute("roles", userService.getRoles());
		return "admin";
	}

	@RequestMapping("user")
	public String userPage(Principal principal, Model model) {
		model.addAttribute("user", userService.findByEmail(principal.getName()));
		return "user";
	}

}