package lk.ijse.shoe_management.service.impl;

import lk.ijse.shoe_management.dto.InventoryDTO;
import lk.ijse.shoe_management.persistance.entity.Inventory;
import lk.ijse.shoe_management.persistance.repository.InventoryRepository;
import lk.ijse.shoe_management.service.InventoryService;
import lk.ijse.shoe_management.service.exception.DublicateRecordException;
import lk.ijse.shoe_management.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    InventoryRepository inventoryRepository;
    ModelMapper modelMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll().stream().map(
                inventory -> modelMapper.map(inventory, InventoryDTO.class)
        ).toList();
    }

    @Override
    public InventoryDTO getInventoryDetails(String id) {
        if(!inventoryRepository.existsByItemCode(id)){
            throw new NotFoundException("Inventory "+id+" Not Found!");
        }
        return modelMapper.map(inventoryRepository.findByItemCode(id), InventoryDTO.class);
    }

    @Override
    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        if(inventoryRepository.existsByItemCode(inventoryDTO.getItemCode())){
            throw new DublicateRecordException("This Inventory "+inventoryDTO.getItemCode()+" already exicts...");
        }
        return modelMapper.map(inventoryRepository.save(modelMapper.map(
                inventoryDTO, Inventory.class)), InventoryDTO.class
        );
    }

    @Override
    public void updateInventory(String id, InventoryDTO inventoryDTO) {
        Inventory existingInventory = inventoryRepository.findByItemCode(id);

        if(existingInventory.getItemCode().isEmpty()){
            throw new NotFoundException("Inventory "+ id + "Not Found...");
        }

        existingInventory.setItemDescription(inventoryDTO.getItemDescription());
        existingInventory.setItemPicture(inventoryDTO.getItemPicture());

        inventoryRepository.save(existingInventory);
    }

    @Override
    public void deleteInventory(String id) {
        if(!inventoryRepository.existsByItemCode(id)){
            throw  new NotFoundException("Inventory "+ id + "Not Found...");
        }
        inventoryRepository.deleteByItemCode(id);
    }
}
