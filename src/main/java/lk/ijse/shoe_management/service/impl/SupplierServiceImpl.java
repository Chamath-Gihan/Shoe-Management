package lk.ijse.shoe_management.service.impl;

import lk.ijse.shoe_management.dto.SupplierDTO;
import lk.ijse.shoe_management.persistance.entity.Supplier;
import lk.ijse.shoe_management.persistance.repository.SupplierRepository;
import lk.ijse.shoe_management.service.SupplierService;
import lk.ijse.shoe_management.service.exception.DublicateRecordException;
import lk.ijse.shoe_management.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    SupplierRepository supplierRepository;
    ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        System.out.println(genarateNextSupplierCode());
        return supplierRepository.findAll().stream().map(
                supplier -> modelMapper.map(supplier, SupplierDTO.class)
        ).toList();
    }

    @Override
    public SupplierDTO getSupplierDetails(String id) {
        if(!supplierRepository.existsBySupplierCode(id)){
            throw new NotFoundException("Supplier "+id+" Not Found!");
        }
        return modelMapper.map(supplierRepository.findBySupplierCode(id), SupplierDTO.class);
    }

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        if(supplierRepository.existsBySupplierCode(supplierDTO.getSupplierCode())){
            throw new DublicateRecordException("This Supplier "+supplierDTO.getSupplierCode()+" already exicts...");
        }
        return modelMapper.map(supplierRepository.save(modelMapper.map(
                supplierDTO, Supplier.class)), SupplierDTO.class
        );
    }

    @Override
    public void updateSupplier(String id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findBySupplierCode(id);

        if(existingSupplier.getSupplierCode().isEmpty()){
            throw new NotFoundException("Supplier ID"+ id + "Not Found...");
        }

        existingSupplier.setSupplierName(supplierDTO.getSupplierName());
        existingSupplier.setCategory(supplierDTO.getCategory());

        supplierRepository.save(existingSupplier);
    }

    @Override
    public void deleteSupplier(String id) {
        if(!supplierRepository.existsBySupplierCode(id)){
            throw  new NotFoundException("Supplier ID"+ id + "Not Found...");
        }
        supplierRepository.deleteBySupplierCode(id);
    }

    @Override
    public String genarateNextSupplierCode() {
        String lastSupplierCode = supplierRepository.findLatestSupplierCode();
        int numericPart = Integer.parseInt(lastSupplierCode.substring(3));
        numericPart++;
        String nextSupplierCode = "SUP" + String.format("%03d", numericPart);
        return nextSupplierCode;
    }
}
