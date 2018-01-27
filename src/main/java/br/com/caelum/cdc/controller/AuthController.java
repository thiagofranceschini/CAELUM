package br.com.caelum.cdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	@RequestMapping("/login")
	public String loginPage(){
		return "auth/login";
	}
}
