package com.learning.rest.webservice.restfulwebservices.helloworld.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends  JpaRepository<Post, Integer>{

}
