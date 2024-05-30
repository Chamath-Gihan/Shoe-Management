package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerDetails(String id);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(String id, CustomerDTO customerDTO);

    void deleteCustomer(String id);

    String genarateNextCustomerCode();
}
