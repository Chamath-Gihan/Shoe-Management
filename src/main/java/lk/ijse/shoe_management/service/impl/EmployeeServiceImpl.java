package lk.ijse.shoe_management.service.impl;

import lk.ijse.shoe_management.dto.EmployeeDTO;
import lk.ijse.shoe_management.persistance.entity.Employee;
import lk.ijse.shoe_management.persistance.repository.EmployeeRepository;
import lk.ijse.shoe_management.service.EmployeeService;
import lk.ijse.shoe_management.service.exception.DublicateRecordException;
import lk.ijse.shoe_management.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(
                employee -> modelMapper.map(employee, EmployeeDTO.class)
        ).toList();
    }

    @Override
    public EmployeeDTO getEmployeeDetails(String id) {
        if(!employeeRepository.existsByEmployeeCode(id)){
            throw new NotFoundException("Employee "+id+" Not Found!");
        }
        return modelMapper.map(employeeRepository.findByEmployeeCode(id), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepository.existsByEmployeeCode(employeeDTO.getEmployeeCode())){
            throw new DublicateRecordException("This Employee "+employeeDTO.getEmployeeCode()+" already exicts...");
        }
        return modelMapper.map(employeeRepository.save(modelMapper.map(
                employeeDTO, Employee.class)), EmployeeDTO.class
        );
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findByEmployeeCode(id);

        if(existingEmployee.getEmployeeName().isEmpty()){
            throw new NotFoundException("Employee ID"+ id + "Not Found...");
        }

        existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
        existingEmployee.setGender(employeeDTO.getGender());

        employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(String id) {
        if(!employeeRepository.existsByEmployeeCode(id)){
            throw  new NotFoundException("Employee ID"+ id + "Not Found...");
        }
        employeeRepository.deleteByEmployeeCode(id);
    }
}
