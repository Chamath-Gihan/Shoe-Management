package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierDetails(String id);

    SupplierDTO saveSupplier(SupplierDTO supplierDTO);

    void updateSupplier(String id, SupplierDTO supplierDTO);

    void deleteSupplier(String id);

    String genarateNextSupplierCode();
}
