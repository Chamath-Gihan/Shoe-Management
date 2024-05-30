package lk.ijse.shoe_management.persistance.repository;

import lk.ijse.shoe_management.persistance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,String> {

    Boolean existsByEmployeeCode(String id);

    Employee findByEmployeeCode(String id);
    
    void deleteByEmployeeCode(String id);
}
