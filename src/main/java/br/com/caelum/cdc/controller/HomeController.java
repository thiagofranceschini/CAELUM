package br.com.caelum.cdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("home")
	public String index(){
		System.out.println("Carregando a página index.jsp");
		return "index";
	}
}
