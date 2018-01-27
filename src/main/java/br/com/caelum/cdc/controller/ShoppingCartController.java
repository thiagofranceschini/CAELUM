package br.com.caelum.cdc.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.cdc.dao.ProductDao;
import br.com.caelum.cdc.models.BookType;
import br.com.caelum.cdc.models.PaymentData;
import br.com.caelum.cdc.models.Product;
import br.com.caelum.cdc.models.ShoppingCart;
import br.com.caelum.cdc.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	
	@Autowired
	private ProductDao productDao;
	
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<String> checkout(){
		return()->{
		BigDecimal total= shoppingCart.getTotal();
		
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		try {
			String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			System.out.println(response);
			shoppingCart.getList().clear();
			return "redirect:/produtos";
		} catch (HttpClientErrorException exception) {
			System.out.println("Ocorreu um erro ao criar o pagamento"+exception.getMessage());
			return "redirect:/shopping";
		}
		};
	}	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType){
		System.out.println("Adiconado ao carrinho redirecionando para /produtos...");
		ShoppingItem item = createItem(productId, bookType);
		System.out.println("##item booktype"+bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}
	
	private ShoppingItem createItem(Integer productId, BookType bookType){
		Product product = productDao.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public String list(){
		return "/shoppingcart/item";
	}
}
