package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Category {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<Article> articles; // une categorie est lié à plusieurs articles
	
	public Category() {}


	public Category(String name) {
	this.name = name;
	}
}