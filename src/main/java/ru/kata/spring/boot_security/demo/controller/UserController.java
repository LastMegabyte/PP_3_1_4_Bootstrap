package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/userPage")
	public String showUserInfo(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "userPage";
	}
}