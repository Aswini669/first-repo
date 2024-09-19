package com.kodbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class NavigationController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/openSignUp")
	public String openSignUp() {
		return "signUp";
	}
	
	@GetMapping("/openCreatePost")
	public String openCreatePost() {
		return "createPost";
	}
	
	@GetMapping("/openMyProfile")
	public String openMyProfile() {
		return "myProfile";
	}
	@GetMapping("/openEditProfile")
	public String openEditProfile() {
		return "editProfile";
	}
	
	
	
}
