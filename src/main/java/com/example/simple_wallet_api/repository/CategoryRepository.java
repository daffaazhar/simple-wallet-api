package com.example.simple_wallet_api.repository;

import com.example.simple_wallet_api.entity.Category;
import com.example.simple_wallet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findFirstByUserAndId(User user, String id);

    List<Category> findAllByUser(User user);
}
