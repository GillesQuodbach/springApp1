package fr.fms.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.*;

public interface CategoryRepository extends JpaRepository<Category, Long>{

public List<Category> findByOrderByNameDesc();

public List<Category> findByOrderByNameAsc();
	
}