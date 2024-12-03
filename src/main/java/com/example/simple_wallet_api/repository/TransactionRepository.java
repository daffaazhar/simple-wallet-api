package com.example.simple_wallet_api.repository;

import com.example.simple_wallet_api.entity.Transaction;
import com.example.simple_wallet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByUserAndTypeAndDateBetween(User user, Transaction.Type type, LocalDate startDate, LocalDate endDate);

    List<Transaction> findTop5ByUserOrderByDateDesc(User user);

    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND MONTH(t.date) = :month AND YEAR(t.date) = :year")
    List<Transaction> findByUserAndMonthAndYear(User user, @Param("month") int month, @Param("year") int year);

    Optional<Transaction> findFirstByUserAndId(User user, String id);

    @Query("SELECT t.category.name AS categoryName, SUM(t.amount) AS totalAmount " +
            "FROM Transaction t " +
            "WHERE t.type = 'INCOME' AND t.user = :user AND MONTH(t.date) = :month AND YEAR(t.date) = :year " +
            "GROUP BY t.category.name")
    List<Object[]> getIncomeAnalytics(User user, @Param("month") int month, @Param("year") int year);

    @Query("SELECT t.category.name AS categoryName, SUM(t.amount) AS totalAmount " +
            "FROM Transaction t " +
            "WHERE t.type = 'EXPENSE' AND t.user = :user AND MONTH(t.date) = :month AND YEAR(t.date) = :year " +
            "GROUP BY t.category.name")
    List<Object[]> getExpenseAnalytics(User user, @Param("month") int month, @Param("year") int year);
}
