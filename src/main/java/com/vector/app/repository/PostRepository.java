package com.vector.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vector.app.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    
}
