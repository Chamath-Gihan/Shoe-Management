package lk.ijse.shoe_management.persistance.repository;

import lk.ijse.shoe_management.persistance.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,String> {

    Boolean existsByItemCode(String id);

    Inventory findByItemCode(String id);
    
    void deleteByItemCode(String id);
}
