package br.com.caelum.cdc.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.cdc.dao.ProductDao;
import br.com.caelum.cdc.models.BookType;
import br.com.caelum.cdc.models.Product;
import br.com.caelum.cdc.validator.ProductValidator;

@Controller
@RequestMapping("produtos")

public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("formulario")
	public ModelAndView formulario(Product product){
		System.out.println("Disponibilizando o formulário de produtos");
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes){//binding
		if(bindingResult.hasErrors())
			return formulario(product);
		productDao.save(product);
		System.out.println("Cadastrando o Produto: "+ product);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("message", "Produto salvo com sucesso!");
		return modelAndView;

	
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("products", productDao.listar());
		return modelAndView;
		
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ProductValidator());
	}
	
	
}
