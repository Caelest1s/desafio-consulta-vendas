package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;


public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj "
            + "FROM Sale obj "
            + "JOIN FETCH obj.seller "
            + "WHERE obj.date BETWEEN :min AND :max "
            + "AND UPPER (obj.seller.name) LIKE UPPER (CONCAT ('%', :name, '%')) ", 
            countQuery = "SELECT COUNT(obj) "
            + "FROM Sale obj "
            + "JOIN obj.seller ")
    Page<Sale> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) AS total "
            + "FROM tb_sales "
            + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
            + "WHERE tb_sales.date >= :min AND tb_sales.date <= :max "
            + "GROUP BY tb_seller.name ")
            List<SaleSummaryProjection> searchSummary(LocalDate min, LocalDate max);
}
