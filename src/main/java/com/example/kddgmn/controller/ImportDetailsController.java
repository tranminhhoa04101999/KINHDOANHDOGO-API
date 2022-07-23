package com.example.kddgmn.controller;


import com.example.kddgmn.model.ImportDetails;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.ImportProductRecive;
import com.example.kddgmn.service.ImportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ImportDetailsController {
    @Autowired
    private ImportDetailsService importDetailsService;

    @GetMapping("/importDetails")
    public List<ImportDetails> getAll() {
        return importDetailsService.getAll();
    }

    @GetMapping("detailsfindbyimportprod")
    public List<ImportDetails> findByIdImportProd(@RequestParam("idImportProduct") int id){
        return importDetailsService.findByIdImportProduct(id);
    }

}
