package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Override
	public String toString() {
		// return "Category [id=" + id + ", name=" + name + ", articles=" + articles +
		// "]";

		String formatCategory = "| %-4s | %-11s|";
		String separatorCategory = "+------+---------+";

		String rowCategory = String.format(formatCategory, id, name);
		String formatArticles = "| %-4s | %-10s | %-10s | %-8s |";

		String emptyLine = "";

		String separatorArticles = "+------+------------+-------------+----------+";
		String rowArticles = String.format(formatArticles, id, "BRAND", "DESCRIPTION", "PRICE");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(separatorCategory).append(System.lineSeparator());
		stringBuilder.append(rowCategory).append(System.lineSeparator());
		stringBuilder.append(separatorCategory).append(System.lineSeparator());
		stringBuilder.append(emptyLine).append(System.lineSeparator());
		stringBuilder.append(separatorArticles).append(System.lineSeparator());
		stringBuilder.append(rowArticles).append(System.lineSeparator());
		stringBuilder.append(separatorArticles).append(System.lineSeparator());
		stringBuilder.append(articles);
		return stringBuilder.toString();
	}

	public String displayCategory() {

		String format = "| %-4s | %-11s|";
		String separator = "+------+------------+";
		String row = String.format(format, id, name);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(separator).append(System.lineSeparator());
		stringBuilder.append(row).append(System.lineSeparator());
		stringBuilder.append(separator).append(System.lineSeparator());

		return stringBuilder.toString();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Article> articles; // une categorie est lié à plusieurs articles

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}
	
	public Collection<Article> getArticles(){
		return articles;
	}
}