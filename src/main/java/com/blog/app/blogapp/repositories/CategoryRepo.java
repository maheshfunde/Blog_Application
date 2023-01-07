package com.blog.app.blogapp.repositories;

import com.blog.app.blogapp.entities.Category;
import com.blog.app.blogapp.entities.Post;
import com.blog.app.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {



}
