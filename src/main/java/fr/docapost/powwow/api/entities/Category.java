package fr.docapost.powwow.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
	
	@GeneratedValue @Id
	private Long id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		product.setCategory(this);
		products.add(product);
	}
	
	

	
	

}
