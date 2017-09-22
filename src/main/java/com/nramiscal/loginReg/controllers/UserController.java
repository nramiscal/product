package com.nramiscal.loginReg.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nramiscal.loginReg.models.Package;
import com.nramiscal.loginReg.models.User;
import com.nramiscal.loginReg.services.PackageService;
import com.nramiscal.loginReg.services.UserService;
import com.nramiscal.loginReg.validator.UserValidator;


@Controller
public class UserController {
	
	private UserService userService;
	private UserValidator userValidator;
	private PackageService packageService;
	
	public UserController(UserService userService, UserValidator userValidator, PackageService packageService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.packageService = packageService;
		this.userService.initRoles();
	}
	
	
	// This page loads on successful login.
	@RequestMapping(value = {"/", "/home"})
	public String home(Principal principal, Model model, @ModelAttribute("pkg") Package pkg, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout) {
		
		String username = principal.getName();
		User user = userService.findByUsername(username); // find user by email
		userService.updateUserDate(user.getId(), user); // set updated_at
		
		model.addAttribute("currentUser", user);
		model.addAttribute("users", userService.all());
		model.addAttribute("packages", packageService.all());
		
		
		// if admin, return adminPage
		if (user.checkIfAdmin()) {
			return "adminPage";
		}
		
		if (user.getPackages().size() < 1) {
			return "selectPackage";
		}
	
		return "dashboard";	
		
	}
	
	@RequestMapping("/login")
	public String loginReg(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful");
        }
		return "loginReg";
	}
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		return "redirect:/login";
	}
	

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		
		userValidator.validate(user, result);
		
		if (result.hasErrors()) {
			return "loginReg";
		}
		
		// check if there is a user with admin role
		List<User> users = userService.all();
		int count = 0;
		for (User _user: users) {
			if (_user.checkIfAdmin()) {
				count++;
			}
		}	
		
		// if no admins, save as admin
		if (count == 0) {
			userService.saveUserWithAdminRole(user);
		} else {
			userService.saveWithUserRole(user);
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "redirect:/home";
	}
	
	@RequestMapping("/makeAdmin/{id}")
	public String makeAdmin(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		userService.updateUserWithAdminRole(user);
		return "redirect:/home";
	}
	
	@RequestMapping("/makeUser/{id}")
	public String makeUser(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		userService.updateWithUserRole(user);
		return "redirect:/home";
	}
	
//	@PostMapping("/packages/new")
//	public String createProduct(@Valid @ModelAttribute("package") Package pkg, BindingResult result, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout) {
//		if (result.hasErrors()) {
//			return "adminPage";
//		}else {
//			packageService.addPackage(pkg);
//			return "redirect:/";
//		}
//	}
	
	@RequestMapping("/packages/new")
	public String newPackage(@Valid @ModelAttribute("pkg") Package pkg) {
		return "newPackage";
	}
	
	@PostMapping("/packages/new")
	public String createPackage(@Valid @ModelAttribute("pkg") Package pkg, BindingResult result) {
		if (result.hasErrors()) {
			return "newPackage";
		}else {
			packageService.addPackage(pkg);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/activate/{id}")
	public String activate(@PathVariable("id") Long id) {
		Package pkg = packageService.findPackageById(id);
		packageService.activate(pkg);
		return "redirect:/home";
	}
	
}
