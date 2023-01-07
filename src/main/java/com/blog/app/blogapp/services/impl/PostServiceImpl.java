package com.blog.app.blogapp.services.impl;

import com.blog.app.blogapp.entities.Category;
import com.blog.app.blogapp.entities.Post;
import com.blog.app.blogapp.entities.User;
import com.blog.app.blogapp.exceptions.ResourceNotFoundException;
import com.blog.app.blogapp.payloads.PostDto;
import com.blog.app.blogapp.payloads.PostResponse;
import com.blog.app.blogapp.repositories.CategoryRepo;
import com.blog.app.blogapp.repositories.PostRepo;
import com.blog.app.blogapp.repositories.UserRepo;
import com.blog.app.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ","User id",userId));

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));


        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);


        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post= this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post","post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post= this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post","post id",postId));
        this.postRepo.delete(post);


    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDirection) {
        Sort sort=null;
        if (sortDirection.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else if (sortDirection.equalsIgnoreCase("dec")){
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pagePost=this.postRepo.findAll(p);
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post id",postId));
        PostDto postDto= this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","catregory id",categoryId));
        List<Post> posts= this.postRepo.findByCategory(cat);
        List<PostDto>postDtos=posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","user id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos= posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
}
