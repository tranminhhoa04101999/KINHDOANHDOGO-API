package com.example.kddgmn.service;

import com.example.kddgmn.model.*;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.ImportProductRecive;
import com.example.kddgmn.payload.ListProductImportRecive;
import com.example.kddgmn.repository.ImportDetailsRepository;
import com.example.kddgmn.repository.ImportProductRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImportProductService {
    @Autowired
    private ImportProductRepository importProductRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ImportDetailsRepository importDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ImportProduct> getAll(){
        return importProductRepository.findAll();
    }
    public int save(ImportProduct importProduct){
        try {
            importProductRepository.save(importProduct);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public CommonResponse addImport(int idEmployee,ImportProductRecive importProductRecive){
        CommonResponse commonResponse = new CommonResponse();
        Employee employee = employeeService.findByIdAccount(idEmployee).get(0);
        ImportProduct importProduct = new ImportProduct(importProductRecive.getSourceName(),new Date(),new Date(),employee);
        List<ListProductImportRecive> listProductImportRecive = importProductRecive.getListProd();

        System.out.println(listProductImportRecive);

        try {
            save(importProduct);
            int idMax = importProductRepository.findIdMax();
            listProductImportRecive.stream().forEach(item -> {
                ImportDetailsId importDetailsId = new ImportDetailsId(idMax,item.getId());
                ImportDetails importDetails = new ImportDetails();
                importDetails.setImportProductId(importDetailsId);
                importDetails.setImportProduct(importProduct);
                Product product = productRepository.findById(item.getId()).get();
                importDetails.setProduct(product);
                importDetails.setPrice(item.getPrice());
                importDetails.setQuantity(item.getQuantity());
                importDetailsRepository.save(importDetails);
                // thêm số lượng vào sản phẩm
                int quantityOld = product.getQuantity();
                product.setQuantity(quantityOld + item.getQuantity());
                productRepository.save(product);
            });

            commonResponse.setIdResult(1);
            commonResponse.setMessage("Nhập hàng thành công !!!");
        }
        catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
            commonResponse.setIdResult(0);
            commonResponse.setMessage("Nhập hàng thất bại !!!");
        }
        return commonResponse;
    }
}
