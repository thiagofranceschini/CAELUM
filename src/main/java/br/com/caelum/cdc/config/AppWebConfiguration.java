package br.com.caelum.cdc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.cdc.controller.HomeController;
import br.com.caelum.cdc.dao.ProductDao;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProductDao.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	//método para disponibilizar as mensagens no properties
	
	@Bean(name="messageSource")
	public MessageSource loudSource(){
		
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("/WEB-INF/messages");//caminho para encontrar as mensagens
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");//alterar o encoding das mensagens
		reloadableResourceBundleMessageSource.setCacheSeconds(1);
		return reloadableResourceBundleMessageSource;
	}
	
}
