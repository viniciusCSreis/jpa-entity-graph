package com.example.labkotlinjava.config;

import com.example.labkotlinjava.entity.*;
import com.example.labkotlinjava.repository.CustomerDetailRepository;
import com.example.labkotlinjava.repository.CustomerInfoRepository;
import com.example.labkotlinjava.repository.OccupationRoleRepository;
import com.example.labkotlinjava.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;


@Configuration
public class InitDb {

    private static final Logger log = LoggerFactory.getLogger(InitDb.class);

    @Bean
    public CommandLineRunner initPostAndPostComment(PostRepository postRepository) {
        return (args) -> {
            log.info("Inserting Post and comment...");
            log.info("-------------------------------");

            ArrayList<Post> posts = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                Long index = (long) i + 1;
                Post post = new Post();
                post.setId(index);
                post.setTitle("Post " + index);
                PostComment postComment = new PostComment();
                postComment.setId(index);
                postComment.setReview("Comment " + index);
                post.addComment(postComment);
                posts.add(post);
            }

            postRepository.saveAll(posts);
        };
    }

    @Bean
    public CommandLineRunner initCustomers(
            CustomerDetailRepository customerDetailRepository
    ) {
        return (args) -> {
            ArrayList<CustomerDetail> customers = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Long index = (long) (i + 1);
                CustomerDetail customerDetail = new CustomerDetail();
                customerDetail.setId(index);
                customerDetail.setName("Customer " + index);
                CustomerInfo customerInfo = new CustomerInfo();
                customerInfo.setId(index);
                customerInfo.setPhoto("photo customer " + index);
                customerInfo.setCustomerDetail(customerDetail);
                customerDetail.setCustomerInfo(customerInfo);
                ArrayList<OccupationRole> roles = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    Long id = (long) (i * 3 + 3 + j);
                    OccupationRole occupationRole = new OccupationRole();
                    occupationRole.setId(id);
                    occupationRole.setCustomerInfo(customerInfo);
                    occupationRole.setName("Role " + i);
                    roles.add(occupationRole);
                }
                customerInfo.setRoles(roles);
                customers.add(customerDetail);
            }
            customerDetailRepository.saveAll(customers);
        };
    }

}
