package lk.ijse.finalcoursework.shoeshop.persistence.repository;

import lk.ijse.finalcoursework.shoeshop.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByCustomerCode(String id);
    Boolean existsByCustomerCode(String id);
    void deleteByCustomerCode(String id);
    @Query(value = "SELECT customer_code FROM Customers ORDER BY customer_code DESC LIMIT 1", nativeQuery = true)
    String findLatestCustomerCode();
}
