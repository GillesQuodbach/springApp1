package fr.fms.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;
	
	public Article() {}
	

	
	public Article(Long id, String brand, String description, double price) {
		this.id = id;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}


	@Override
	public String toString() {
		return "Article [id=" + id + ", brand=" + brand + ", description=" + description + ", price=" + price + "]";
	}



	public Article( String brand, String description, double price) {
		this.brand = brand;
		this.description = description;
		this.price = price;
	}
}