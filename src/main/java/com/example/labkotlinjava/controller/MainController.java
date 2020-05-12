package com.example.labkotlinjava.controller;

import com.example.labkotlinjava.entity.Post;
import com.example.labkotlinjava.entity.PostComment;
import com.example.labkotlinjava.repository.PostCommentRepository;
import com.example.labkotlinjava.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {

    private final PostRepository postRepository;

    private final PostCommentRepository postCommentRepository;

    @Autowired
    public MainController(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    @GetMapping("/test")
    Map<String, String> test() {
        List<String> titles = postRepository.findAll().stream().map(Post::getTitle).collect(Collectors.toList());
        List<String> reviews = postCommentRepository.findAll().stream().map(PostComment::getReview).collect(Collectors.toList());
        HashMap<String, String> json = new HashMap<>();
        json.put("titles", titles.toString());
        json.put("reviews", reviews.toString());
        return json;
    }

    @GetMapping("/lazy")
    Map<String, String> lazy() {
        List<String> posts = postCommentRepository.findAll().stream().map((it) -> it.getPost().getTitle()).collect(Collectors.toList());
        HashMap<String, String> json = new HashMap<>();
        json.put("posts", posts.toString());
        return json;
    }

    @GetMapping("/inner")
    Map<String, String> inner() {
        List<String> posts = postCommentRepository.listAllEager().stream().map((it) -> it.getPost().getTitle()).collect(Collectors.toList());
        HashMap<String, String> json = new HashMap<>();
        json.put("posts", posts.toString());
        return json;
    }
}
