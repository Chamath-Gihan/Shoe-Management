package lk.ijse.shoe_management.persistance.repository;

import lk.ijse.shoe_management.persistance.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,String> {

    Boolean existsByOrderNo(String id);

    Sales findByOrderNo(String id);
    
    void deleteByOrderNo(String id);
}
