package com.blog.app.blogapp.services;

import com.blog.app.blogapp.payloads.CommentDto;

public interface CommentService  {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment (Integer commentId);
}
