package lk.ijse.shoe_management.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.ijse.shoe_management.persistance.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,String> {

    Boolean existsBySupplierCode(String id);

    Supplier findBySupplierCode(String id);

    void deleteBySupplierCode(String id);

    @Query(value = "SELECT supplier_code FROM Suppliers ORDER BY supplier_code DESC LIMIT 1", nativeQuery = true)
    String findLatestSupplierCode();
}
