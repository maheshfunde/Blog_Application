package com.blog.app.blogapp.services;

import com.blog.app.blogapp.entities.Post;
import com.blog.app.blogapp.payloads.PostDto;
import com.blog.app.blogapp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //Create method
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //Update Post
    PostDto updatePost(PostDto postDto,Integer postId);

    //Delete Post
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDirection);

    //get single post

    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user

    List<PostDto> getAllPostsByUser(Integer userId);

    //Search Posts
    List<PostDto> searchPosts(String keyword);



}
