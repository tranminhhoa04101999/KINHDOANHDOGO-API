package com.example.kddgmn.controller;

import com.example.kddgmn.model.ImportProduct;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.ImportProductRecive;
import com.example.kddgmn.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class ImportProductController {
    @Autowired
    private ImportProductService importProductService;


    @GetMapping("/importProducts")
    public List<ImportProduct> getAll(){
        return importProductService.getAll();
    }

    @PostMapping("/addImport")
    public CommonResponse addImport(@RequestParam("idEmployee") int idEmployee, @RequestBody ImportProductRecive importProductRecive){
        return importProductService.addImport(idEmployee,importProductRecive);
    }
    @GetMapping("/importproductfindbyid")
    public ImportProduct findById(@RequestParam("id") int id){
        return importProductService.findById(id);
    }
    @GetMapping("/exportreceiptimport")
    public int exportreceiptimport(@RequestParam("idImportProduct") int id){
        return importProductService.exportReceiptWordFile(id);
    }
    @PostMapping("/editImport")
    public CommonResponse editImport(@RequestParam("idImportProduct") int idImportProduct, @RequestBody ImportProductRecive importProductRecive){
        return importProductService.editImport(idImportProduct, importProductRecive);
    }
}



































