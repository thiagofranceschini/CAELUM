package br.com.caelum.cdc.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.cdc.dao.ProductDao;
import br.com.caelum.cdc.models.Product;

@Controller
@RequestMapping("produtos")

public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("formulario")
	public String formulario(){
		System.out.println("Disponibilizando o formulário de produtos");
		return "produtos/form";
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(Product product){//binding
		System.out.println("Cadastrando o Produto: "+ product);
		productDao.save(product);
		return "produtos/ok";
	}
	
	
}
