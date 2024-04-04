package fr.fms.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.*;

public interface CategoryRepository extends JpaRepository<Category, Long>{

public List<Category> findByOrderByNameDesc();

public List<Category> findByOrderByNameAsc();

public List<Category> findByNameStartingWith(String str);

public Category findByName(String str);

public Optional<Category> findById(Long id);
	
}