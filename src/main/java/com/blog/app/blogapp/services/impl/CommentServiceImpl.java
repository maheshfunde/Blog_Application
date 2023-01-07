package com.blog.app.blogapp.services.impl;

import com.blog.app.blogapp.entities.Comment;
import com.blog.app.blogapp.entities.Post;
import com.blog.app.blogapp.exceptions.ResourceNotFoundException;
import com.blog.app.blogapp.payloads.CommentDto;
import com.blog.app.blogapp.repositories.CommentRepo;
import com.blog.app.blogapp.repositories.PostRepo;
import com.blog.app.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","post id",postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment );
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","comment id",commentId));
        this.commentRepo.delete(com);

    }
}
