package com.blog.app.blogapp.repositories;

import com.blog.app.blogapp.entities.Category;
import com.blog.app.blogapp.entities.Post;
import com.blog.app.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
//Custom finder methods

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

    /*if findByTitleContaining method doesn't work
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);
     */
    //Implementation method in impl class
    /*
    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos= posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
    */
}
