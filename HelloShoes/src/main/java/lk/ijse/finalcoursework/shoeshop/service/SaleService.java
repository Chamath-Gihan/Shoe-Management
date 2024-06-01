package lk.ijse.finalcoursework.shoeshop.service;

import lk.ijse.finalcoursework.shoeshop.dto.CustomerDTO;
import lk.ijse.finalcoursework.shoeshop.dto.SalesDTO;

import java.util.List;
import java.util.Map;

public interface SaleService {
    List<SalesDTO> getAllSales();
    SalesDTO getSaleDetails(String id);
    SalesDTO saveSales(SalesDTO salesDTO);
    void updateSales(String id, SalesDTO salesDTO);
    void deleteSales(String id);
    String nextOrderCode();
    Map<String, Double> getWeeklyProfit();
    Double getMonthlyRevenue();
}
