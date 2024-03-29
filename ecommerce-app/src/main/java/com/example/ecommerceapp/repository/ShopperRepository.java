package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String> {
//    @Query(value = "SELECT * FROM shopper ORDER BY id LIMIT ?1, ?2", nativeQuery = true)
//    List<Shopper> findFirstNEntities(int offset, int limit);
}
