package fr.fms.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.fms.entities.*;

public interface CategoryRepository extends JpaRepository<Category, Long>{

public List<Category> findByOrderByNameDesc();

public List<Category> findByOrderByNameAsc();

public List<Category> findByNameStartingWith(String str);

public Category findByName(String str);

public Optional<Category> findById(Long id);

public void deleteById(Long id);

@Transactional
@Modifying
@Query("update Category set name = :name where id = :id")
void updateCategory(@Param("name") String name,  @Param("id") Long id);
}