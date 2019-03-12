package com.rest.demo.restdemo.repository;

import com.rest.demo.restdemo.model.Post;
import com.rest.demo.restdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
