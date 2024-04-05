package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	//Exo 1.2
	// TODO afficher tous les articles en base
	public List<Article> findByBrandLike(String brand);
	public List<Article> findByDescriptionIs(String description);
	
	public List<Article> findAll();
	
	//Exo 1.3
	public List<Article> findByDescriptionAndBrandContains(String description, String brand);
	
	//Exo 1.4
	public void deleteById(Long id);
	
	//Exo 1.5
	
	@Transactional
	@Modifying
	@Query("update Article set description = :description, brand = :brand, price = :price, category.id = :categoryId where id = :id")
	void updateArticle(@Param("description") String description, @Param("brand") String brand, @Param("price") double price, @Param("categoryId") Long categoryId, @Param("id") Long id);

	// TP pagination
	
	Page<Article> findAll(Pageable pageable);



}