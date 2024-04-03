package fr.fms;

import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Bienvenue sur ShopApp");
		System.out.println("1: Afficher tous les articles SANS pagination");
		System.out.println("2: Afficher tous les articles AVEC pagination");
		
		int paginationChoice = scan.nextInt();
		scan.nextLine();
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		/*
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));
		
		articleRepository.save(new Article( "Samsung","S10", 500, smartphone));
		articleRepository.save(new Article( "Samsung","S9", 350, smartphone));
		articleRepository.save(new Article( "Xiaomi","MI10", 100, smartphone));
		
		articleRepository.save(new Article( "Samsung","GalaxyTab", 450, tablet));
		articleRepository.save(new Article( "Apple","Ipad", 350, tablet));
		
		articleRepository.save(new Article( "Asus","R510", 500, pc));
		
		for(Article article : articleRepository.findByCategoryId((long) 1)) {
			System.out.println(article);
		}
		
		//Exo 1.2
		for(Article article : articleRepository.findByBrandLike("Xiaomi")) {
			System.out.println(article);
		}
		
		for(Article article : articleRepository.findByDescriptionIs("S10")) {
			System.out.println(article);
		}
		
		for (Article article : articleRepository.findAll()) {
			System.out.println(article);
		}
		//Exo 1.3
		for (Article article : articleRepository.findByDescriptionAndBrandContains("S10", "Samsung")) {
			System.out.println(article);
		}
		
		// Exo 1.4
		articleRepository.deleteById((long)1);
	
		// Exo 1.5
		articleRepository.updateArticle("Test", "merdouille", 1000, 1L, 2L);
		
		// Exo 1.6
		// Ajout EAGER dans Category.java @OneToMany
		for (Category category : categoryRepository.findByOrderByNameDesc()) {
			System.out.println(category);
		}
		for (Category category : categoryRepository.findByOrderByNameAsc()) {
			System.out.println(category);
		}
		
		// Exo 1.7
		for (Category category : categoryRepository.findByNameStartingWith("Ta")) {
			System.out.println(category);
		}
		// categoryRepository.save(new Category("Samsung"));
//		 articleRepository.save(new Article("Samsung","S9",  250));
		
//		for(Article article : articleRepository.findByBrand("Samsung")) {
//			System.out.println((article));
//		}
		
//		for(Article article : articleRepository.findByBrandAndPrice("Samsung",250)) {
//			System.out.println((article));
//		}
//		for(Article article : articleRepository.findByDescriptionAndPriceLessThan("Samsung",350)) {
//			System.out.println((article));
//		}
		
		//for(Article article : articleRepository.searchArticles("sung", 200)) {
		//	System.out.println(article);
		//}
		
//		for(Article article : articleRepository.findByBrandContainingAndPriceGreaterThan("sung", 200)) {
//			System.out.println(article);
//		}
 * *
 */
		
	}
}
