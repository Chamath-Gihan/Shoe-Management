package lk.ijse.shoe_management.service;

import lk.ijse.shoe_management.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    List<InventoryDTO> getAllInventory();

    InventoryDTO getInventoryDetails(String id);

    InventoryDTO saveInventory(InventoryDTO inventoryDTO);

    void updateInventory(String id, InventoryDTO inventoryDTO);
    
    void deleteInventory(String id);
}
