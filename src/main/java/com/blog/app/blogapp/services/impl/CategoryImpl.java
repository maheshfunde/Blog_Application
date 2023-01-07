package com.blog.app.blogapp.services.impl;

import com.blog.app.blogapp.entities.Category;
import com.blog.app.blogapp.exceptions.ResourceNotFoundException;
import com.blog.app.blogapp.payloads.CategoryDto;
import com.blog.app.blogapp.repositories.CategoryRepo;
import com.blog.app.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category cat= this.modelMapper.map(categoryDto, Category.class);
        Category addedCat= this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cat= this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatesCat= this.categoryRepo.save(cat);
        return this.modelMapper.map(updatesCat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","category id",categoryId));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","category id",categoryId));

        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories= this.categoryRepo.findAll();
        List<CategoryDto> catDtos=categories.stream().map((cat -> this.modelMapper.map(cat,CategoryDto.class))).collect(Collectors.toList());
        return catDtos;
    }
}
