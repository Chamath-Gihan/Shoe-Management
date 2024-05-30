package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.dto.SalesDTO;

import java.util.List;

public interface SaleService {

    List<SalesDTO> getAllSales();

    SalesDTO getSaleDetails(String id);

    SalesDTO saveSales(SalesDTO salesDTO);

    void updateSales(String id, SalesDTO salesDTO);
    
    void deleteSales(String id);
}
