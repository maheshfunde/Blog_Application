package com.blog.app.blogapp.controllers;

import com.blog.app.blogapp.entities.Comment;
import com.blog.app.blogapp.payloads.ApiResponse;
import com.blog.app.blogapp.payloads.CommentDto;
import com.blog.app.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){

        CommentDto createdComment= this.commentService.createComment(comment,postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

       this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true), HttpStatus.OK);

    }

}
