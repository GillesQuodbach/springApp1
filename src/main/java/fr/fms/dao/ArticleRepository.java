package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.*;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	public List<Article> findByBrand(String brand);
	
}