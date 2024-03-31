package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.Shelfitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShelfitemRepository extends JpaRepository<Shelfitem, Long> {
    List<Shelfitem> findAllByShopperId(String shopperId);
    void deleteAllByShopperId(String shopperId);

//    List<Shelfitem> findByNameContainingAndFatherNameContaining(String nameKeyword, String fatherNameKeyword);

//    @Query("SELECT s FROM Student s WHERE s.name LIKE %:nameKeyword% AND s.fatherName LIKE %:fatherNameKeyword%")
//    List<Shelfitem> findByCustomQuery(String nameKeyword, String fatherNameKeyword);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:nameKeyword% AND s.fatherName LIKE %:fatherNameKeyword%")
    List<Shelfitem> findByCustomQuery(String shopperId, String category, String brand, int limit);


//    String shopperId, String category, String brand, int limit
}


