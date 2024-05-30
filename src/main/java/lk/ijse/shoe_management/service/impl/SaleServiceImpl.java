package lk.ijse.shoe_management.service.impl;

import lk.ijse.shoe_management.dto.SalesDTO;
import lk.ijse.shoe_management.dto.SalesInventoryDTO;
import lk.ijse.shoe_management.persistance.entity.Sales;
import lk.ijse.shoe_management.persistance.entity.SalesDetails;
import lk.ijse.shoe_management.persistance.repository.SalesDetailsRepository;
import lk.ijse.shoe_management.persistance.repository.SalesRepository;
import lk.ijse.shoe_management.service.SaleService;
import lk.ijse.shoe_management.service.exception.DublicateRecordException;
import lk.ijse.shoe_management.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    SalesRepository salesRepository;

    SalesDetailsRepository salesDetailsRepository;

    ModelMapper modelMapper;

    public SaleServiceImpl(SalesRepository salesRepository, SalesDetailsRepository salesDetailsRepository, ModelMapper modelMapper) {

        this.salesRepository = salesRepository;
        this.salesDetailsRepository = salesDetailsRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<SalesDTO> getAllSales() {

        return salesRepository.findAll().stream().map(
                sales -> modelMapper.map(sales, SalesDTO.class)
        ).toList();

    }

    @Override
    public SalesDTO getSaleDetails(String id) {

        if(!salesRepository.existsByOrderNo(id)){
            throw new NotFoundException("Sales "+id+" Not Found!");
        }

        SalesDTO salesDTO = modelMapper.map(salesRepository.findByOrderNo(id), SalesDTO.class);
        System.out.println("ID-----------------------"+id);

        List<SalesInventoryDTO> salesInventory = salesDetailsRepository.findAllBySalesOrderNo(id).stream().map(
                salesDetails -> modelMapper.map(salesDetails, SalesInventoryDTO.class)
        ).toList();
        salesDTO.setInventory(salesInventory);

        return salesDTO;
    }

    @Override
    public SalesDTO saveSales(SalesDTO salesDTO) {

        if(salesRepository.existsByOrderNo(salesDTO.getOrderNo())){
            throw new DublicateRecordException("This Sales "+salesDTO.getOrderNo()+" already exicts...");
        }

        SalesDTO newsalesDTO = modelMapper.map(salesRepository.save(modelMapper.map(
                salesDTO, Sales.class)), SalesDTO.class
        );

        List<SalesInventoryDTO> salesInventoryDTO = new ArrayList<>();

        for (SalesInventoryDTO inventoryDTO : salesDTO.getInventory()) {

            SalesDetails savedSaleDetails = salesDetailsRepository.save(modelMapper.map(inventoryDTO, SalesDetails.class));
            salesInventoryDTO.add(modelMapper.map(savedSaleDetails, SalesInventoryDTO.class));

        }

        newsalesDTO.setInventory(salesInventoryDTO);
        return newsalesDTO;
    }

    @Override
    public void updateSales(String id, SalesDTO salesDTO) {

        for (SalesInventoryDTO inventoryDTO : salesDTO.getInventory()) {

            if(!salesDetailsRepository.existsById(inventoryDTO.getId())){

                throw new NotFoundException("Update Failed; Sales id: " +
                        salesDTO.getOrderNo() + " does not exist");

            }

            salesDetailsRepository.save(modelMapper.map(inventoryDTO, SalesDetails.class));

        }

    }

    @Override
    public void deleteSales(String id) {

        if(!salesDetailsRepository.existsBySalesOrderNo(id)){

            throw  new NotFoundException("Sales "+ id + "Not Found...");

        }

        salesDetailsRepository.deleteAllBySalesOrderNo(id);
        salesRepository.deleteByOrderNo(id);

    }
}
