package service;

import exception.ResourceNotFoundException;
import repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import entity.Category;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(int pageRequest) {
        return categoryRepository.findAll(PageRequest.of(pageRequest, 10)); // Pagination (10 per page)
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + id));
    }

    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist");
        }
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist");
        }
    }

	public Page<Category> getAllCategories(PageRequest of) {
		// TODO Auto-generated method stub
		return null;
	}
}

