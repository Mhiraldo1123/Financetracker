package com.marvin.financetracker.repository;
import com.marvin.financetracker.model.Category;
import com.marvin.financetracker.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findByType(CategoryType type);
}
