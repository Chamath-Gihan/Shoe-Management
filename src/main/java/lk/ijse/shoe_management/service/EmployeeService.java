package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeDetails(String id);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    void updateEmployee(String id, EmployeeDTO employeeDTO);
    
    void deleteEmployee(String id);
}
