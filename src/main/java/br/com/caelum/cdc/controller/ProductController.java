package br.com.caelum.cdc.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.cdc.dao.ProductDao;
import br.com.caelum.cdc.infra.FileSaver;
import br.com.caelum.cdc.models.BookType;
import br.com.caelum.cdc.models.Product;
import br.com.caelum.cdc.validator.ProductValidator;

@Controller
@RequestMapping("produtos")

public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping("formulario")
	public ModelAndView formulario(Product product){
		System.out.println("Disponibilizando o formulário de produtos");
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value={"ultimosProdutos"},allEntries=true)
	public ModelAndView salvar(MultipartFile summary, @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes){//binding
		if(bindingResult.hasErrors())
			return formulario(product);
		
		String webPath = fileSaver.write("uploaded-summaries", summary);
		product.setSummaryPath(webPath);
		productDao.save(product);
		System.out.println("Cadastrando o Produto: "+ product);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("message", "Produto salvo com sucesso!");
		return modelAndView;

	
	}
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="ultimosProdutos")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("products", productDao.listar());
		return modelAndView;
		
	}
	//@InitBinder comentado porque utilizamos hibernate validator
	//public void initBinder(WebDataBinder webDataBinder){
		//webDataBinder.addValidators(new ProductValidator());
	//}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable ("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("produtos/show");
		modelAndView.addObject("product", productDao.find(id));
		return modelAndView;
	}
	
	
}
