package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.*;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand, double price);
	public List<Article> findByDescriptionAndPriceLessThan(String brand, double price);
	
	// Requête "a la main"
	
	//@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	//public List<Article> searchArticles(@Param("x") String kw, @Param("y")double price);
	
	// Requête HQL correspondante
	public List<Article> findByBrandContainingAndPriceGreaterThan(String brand, double price);
	
	public List<Article> findByCategoryId(Long categoryId);
}