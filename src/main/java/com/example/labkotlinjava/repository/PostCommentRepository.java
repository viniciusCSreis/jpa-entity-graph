package com.example.labkotlinjava.repository;

import com.example.labkotlinjava.entity.PostComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    @Query(value = "select p,c from PostComment p inner join p.post c")
    List<PostComment> listAllEager();

    @EntityGraph(attributePaths = "post")
    List<PostComment> findAll();
}
