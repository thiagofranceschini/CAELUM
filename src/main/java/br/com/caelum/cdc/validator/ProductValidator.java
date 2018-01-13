package br.com.caelum.cdc.validator;

import java.util.Objects;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.cdc.models.Product;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "field.required");
		Product product = (Product) target;
		if(Objects.isNull(product.getPaginas())|| product.getPaginas()<=0)
			System.out.println("Rejeitando inserção de dados [paginas]");
			errors.rejectValue("paginas", "field.required");
		
	}

}
