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
public class SpringShopJpaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));

		articleRepository.save(new Article("Samsung", "S10", 500, smartphone));
		articleRepository.save(new Article("Samsung", "S9", 350, smartphone));
		articleRepository.save(new Article("Xiaomi", "MI10", 100, smartphone));

		articleRepository.save(new Article("Samsung", "GalaxyTab", 450, tablet));
		articleRepository.save(new Article("Apple", "Ipad", 350, tablet));

		articleRepository.save(new Article("Asus", "R510", 500, pc));
		Scanner scan = new Scanner(System.in);
		System.out.println("Bienvenue sur ShopApp");

		boolean userInputError = false;

		while (true) {
			if (!userInputError) {
				displayMenu();
			}

			System.out.println("Entrer votre choix:");
			try {
				int customerChoice = Integer.parseInt(scan.nextLine());
			

				userInputError = false;

				switch (customerChoice) {
				case 1:
					System.out.println("---------------------------------------------");
					System.out.println("Afficher tous les articles SANS pagination");
					System.out.println("---------------------------------------------");
					System.out.println();
					for (Article article : articleRepository.findAll()) {
						System.out.println(article.toString());
					}
					System.out.println("---------------------------------------------");
					System.out.println();
					break;
				case 2:
					System.out.println("---------------------------------------------");
					System.out.println("Afficher tous les articles AVEC pagination");
					System.out.println("---------------------------------------------");
					System.out.println();
					for (Article article : articleRepository.findAll()) {
						System.out.println(article.toString());
					}
					System.out.println("---------------------------------------------");
					System.out.println();
					break;
				case 3:
					System.out.println("Ajouter un article");

					System.out.println();
					// articleRepository.save(new Article("Samsung", "S10", 500, smartphone));

					String brand = "";
					String description = "";
					double price = 0;
					String category = "";
					try {
					System.out.println("Enter new article brand:");
					brand = scan.nextLine();
					System.out.println("Enter new article description:");
					description = scan.nextLine();
					System.out.println("Enter new article price");
					price = Integer.parseInt(scan.nextLine());
					System.out.println("Enter new article category:");
					category = scan.nextLine();
					
					Category existingCategory = categoryRepository.findByName((String)category);
					if(existingCategory == null) {
						existingCategory = categoryRepository.save(new Category(category));
					}
					articleRepository.save(new Article(brand, description, price, existingCategory));
					System.out.println("Nouvel article créé avec succès");
					}catch (Exception e) {
						System.out.println(e);
					}
					System.out.println("testnew Article");
					break;
				case 4:
					System.out.println("Afficher un article");
					break;
				case 5:
					System.out.println("Supprimer un article");
					break;
				case 6:
					System.out.println("Modifier un article");
					break;
				case 7:
					System.out.println("Ajouter une catégorie");
					break;
				case 8:
					System.out.println("Afficher une catégorie");
					break;
				case 9:
					System.out.println("Supprimer une catégorie");
					break;
				case 10:
					System.out.println("Mettre à jour une catégorie");
					break;
				case 11:
					System.out.println("Afficher tous les articles d'une catégorie");
					break;
				case 12:
					System.out.println("Sortir du programme");
					break;
				}
			} catch (Exception e) {
				userInputError = true;
				scan.next();
				System.out.println("Saisie invalide");
			}
		}

		/*
		 * for(Article article : articleRepository.findByCategoryId((long) 1)) {
		 * System.out.println(article); }
		 * 
		 * //Exo 1.2 for(Article article : articleRepository.findByBrandLike("Xiaomi"))
		 * { System.out.println(article); }
		 * 
		 * for(Article article : articleRepository.findByDescriptionIs("S10")) {
		 * System.out.println(article); }
		 * 
		 * for (Article article : articleRepository.findAll()) {
		 * System.out.println(article); } //Exo 1.3 for (Article article :
		 * articleRepository.findByDescriptionAndBrandContains("S10", "Samsung")) {
		 * System.out.println(article); }
		 * 
		 * // Exo 1.4 articleRepository.deleteById((long)1);
		 * 
		 * // Exo 1.5 articleRepository.updateArticle("Test", "merdouille", 1000, 1L,
		 * 2L);
		 * 
		 * // Exo 1.6 // Ajout EAGER dans Category.java @OneToMany for (Category
		 * category : categoryRepository.findByOrderByNameDesc()) {
		 * System.out.println(category); } for (Category category :
		 * categoryRepository.findByOrderByNameAsc()) { System.out.println(category); }
		 * 
		 * // Exo 1.7 for (Category category :
		 * categoryRepository.findByNameStartingWith("Ta")) {
		 * System.out.println(category); } // categoryRepository.save(new
		 * Category("Samsung")); // articleRepository.save(new Article("Samsung","S9",
		 * 250));
		 * 
		 * // for(Article article : articleRepository.findByBrand("Samsung")) { //
		 * System.out.println((article)); // }
		 * 
		 * // for(Article article :
		 * articleRepository.findByBrandAndPrice("Samsung",250)) { //
		 * System.out.println((article)); // } // for(Article article :
		 * articleRepository.findByDescriptionAndPriceLessThan("Samsung",350)) { //
		 * System.out.println((article)); // }
		 * 
		 * //for(Article article : articleRepository.searchArticles("sung", 200)) { //
		 * System.out.println(article); //}
		 * 
		 * // for(Article article :
		 * articleRepository.findByBrandContainingAndPriceGreaterThan("sung", 200)) { //
		 * System.out.println(article); // } *
		 */

	}

	public static void displayMenu() {
		System.out.println("1: Afficher tous les articles SANS pagination");
		System.out.println("2: Afficher tous les articles AVEC pagination");
		System.out.println("***********************************************");
		System.out.println("3: Ajouter un article");
		System.out.println("4: Afficher un article");
		System.out.println("5: Supprimer un article");
		System.out.println("6: Modifier un article");
		System.out.println("***********************************************");
		System.out.println("7: Ajouter une catégorie");
		System.out.println("8: Afficher une catégorie");
		System.out.println("9: Supprimer une catégorie");
		System.out.println("10: Mettre à jour une catégorie");
		System.out.println("11: Afficher tous les articles d'une catégorie");
		System.out.println("***********************************************");
		System.out.println("12: Sortir du programme");
		System.out.println();
	}

}
