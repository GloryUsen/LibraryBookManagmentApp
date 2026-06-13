package com.glory.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glory.entity.User;
import com.glory.repository.UserRepository;

@Configuration
public class DataCleanupRunner {

    @Bean
    CommandLineRunner cleanDuplicates(UserRepository repo) {
        return args -> {
            List<User> users = repo.findAll();

            Map<String, List<User>> grouped =
                    users.stream().collect(Collectors.groupingBy(User::getEmail));

            grouped.forEach((email, list) -> {
                if (list.size() > 1) {
                    list.stream().skip(1).forEach(repo::delete);
                }
            });

            System.out.println("✅ Duplicate emails cleaned");
        };
    }

}
