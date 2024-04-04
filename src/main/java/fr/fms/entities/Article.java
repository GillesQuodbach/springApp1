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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;

	@ManyToOne
	public Category category; // plusieurs artciles sont liés à une seule categorie

	public Article() {
	}

	public Article(String brand, String description, double price, Category category) {
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Article(Long id, String brand, String description, double price) {
		this.id = id;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		String format = "| %-4s | %-10s | %-11s | %-8s |";

		String separator = "+------+------------+-------------+----------+";
		String row = String.format(format, id, brand, description, price);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(separator).append(System.lineSeparator());
		stringBuilder.append(row).append(System.lineSeparator());
		stringBuilder.append(separator).append(System.lineSeparator());

		return stringBuilder.toString();
	}

	public Article(String brand, String description, double price) {
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

}

//public String toString() {
//    String format = "| %-4s | %-10s | %-30s | %-8s |";
//    String header = String.format(format, "ID", "BRAND", "DESCRIPTION", "PRICE");
//    String separator = "+------+------------+------------------------------+--------+";
//    String row = String.format(format, id, brand, description, price);
//
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append(separator).append(System.lineSeparator());
//    stringBuilder.append(header).append(System.lineSeparator());
//    stringBuilder.append(separator).append(System.lineSeparator());
//    stringBuilder.append(row).append(System.lineSeparator());
//    stringBuilder.append(separator).append(System.lineSeparator());
//
//    return stringBuilder.toString();
//}