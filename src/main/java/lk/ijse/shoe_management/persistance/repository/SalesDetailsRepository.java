package lk.ijse.shoe_management.persistance.repository;

import lk.ijse.shoe_management.persistance.entity.SalesDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesDetailsRepository extends JpaRepository<SalesDetails,String> {

    Boolean existsBySalesOrderNo(String id);

    List<SalesDetails> findAllBySalesOrderNo(String id);

    void deleteAllBySalesOrderNo(String id);

    Boolean existsAllBySalesOrderNo(String id);
}
