package com.blog.app.blogapp.payloads;

import com.blog.app.blogapp.entities.Category;
import com.blog.app.blogapp.entities.Comment;
import com.blog.app.blogapp.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;

    private Date addDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments=new HashSet<>();

}


