package br.com.caelum.cdc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.cdc.models.Product;

@Repository
public class ProductDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}

	public List<Product> listar() {
		
		return manager.createQuery("select distinct p from Product p join fetch p.prices", Product.class).getResultList();
	}

}
