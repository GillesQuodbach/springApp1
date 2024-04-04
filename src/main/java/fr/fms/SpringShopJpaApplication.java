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

					try {
						String brand = "";
						String description = "";
						double price = 0;
						String category = "";
						System.out.println("Enter new article brand:");
						brand = scan.nextLine();
						System.out.println("Enter new article description:");
						description = scan.nextLine();
						System.out.println("Enter new article price");
						price = Integer.parseInt(scan.nextLine());
						System.out.println("Enter new article category:");
						category = scan.nextLine();

						Category existingCategory = categoryRepository.findByName((String) category);
						if (existingCategory == null) {
							existingCategory = categoryRepository.save(new Category(category));
						}
						articleRepository.save(new Article(brand, description, price, existingCategory));
						System.out.println("New article successfully created");
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				case 4:
					int articleIdToDisplay = -1;
					System.out.println("Display an article");
					System.out.println("Enter article Id: ");
					articleIdToDisplay = Integer.parseInt(scan.nextLine());
					System.out.println(articleRepository.findById((long) articleIdToDisplay));
					break;
				case 5:
					int articleIdToDelete = -1;
					System.out.println("Supprimer un article");
					System.out.println("Enter article Id: ");
					articleIdToDelete = Integer.parseInt(scan.nextLine());
					articleRepository.deleteById((long) articleIdToDelete);
					break;
				case 6:
					String brand = "";
					String description = "";
					double price = 0;
					int categoryId = -1;
					int articleIdToUpdate = -1;

					System.out.println("Modifier un article");
					try {
						System.out.println("Article Id to update:");
						articleIdToUpdate = Integer.parseInt(scan.nextLine());
						System.out.println("Brand to update:");
						brand = scan.nextLine();
						System.out.println("Description to update:");
						description = scan.nextLine();
						System.out.println("Price to update:");
						price = Integer.parseInt(scan.nextLine());
						System.out.println("Article category ID to update:");
						categoryId = Integer.parseInt(scan.nextLine());

						articleRepository.updateArticle(brand, description, price, (long) categoryId,
								(long) articleIdToUpdate);
						System.out.println("New article successfully created");
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				case 7:
					String newCategoryName = "";
					System.out.println("Ajouter une catégorie");
					try {
						System.out.println("Enter new category name:");
						newCategoryName = scan.nextLine();
						categoryRepository.save(new Category(newCategoryName));
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				case 8:

					System.out.println("Afficher une catégorie");
					try {
						int categoryIdToDisplay = -1;
						System.out.println("Enter category id to display:");
						categoryIdToDisplay = Integer.parseInt(scan.nextLine());
						Category category = categoryRepository.findById((long) categoryIdToDisplay).orElse(null);
						if (category != null) {
							System.out.println(category.displayCategory());
						} else {
							System.out.println("Your Id doesn't match any category !");
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				case 9:
					System.out.println("Supprimer une catégorie");
					break;
				case 10:
					System.out.println("Mettre à jour une catégorie");
					break;
				case 11:
					System.out.println("Afficher tous les articles d'une catégorie");
					System.out.println("Enter category id to display:");
					try {
						int categoryIdToDisplay = -1;
						categoryIdToDisplay = Integer.parseInt(scan.nextLine());
						System.out.println(categoryRepository.findById((long) categoryIdToDisplay));
					} catch (Exception e) {
						System.out.println(e);
					}
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
