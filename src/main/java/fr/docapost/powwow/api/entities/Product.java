package fr.docapost.powwow.api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Product {
	@GeneratedValue @Id
	private Long id ;
	
	private String productName;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
	

	
	
	
	

}
