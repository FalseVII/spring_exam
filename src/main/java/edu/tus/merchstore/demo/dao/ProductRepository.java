package edu.tus.merchstore.demo.dao;

import edu.tus.merchstore.demo.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandContaining(String brand);
    List<Product> findByTypeContaining(String type);

    @Query("SELECT p FROM Product p WHERE (p.rrp - p.online) > ?1")
    List<Product> findByDealsMoreThan(double price);
}
