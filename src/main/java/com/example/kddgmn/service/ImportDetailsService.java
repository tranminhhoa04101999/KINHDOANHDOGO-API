package com.example.kddgmn.service;

import com.example.kddgmn.model.ImportDetails;
import com.example.kddgmn.repository.ImportDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportDetailsService {
    @Autowired
    private ImportDetailsRepository importDetailsRepository;

    public List<ImportDetails> getAll(){
        return importDetailsRepository.findAll();
    }
    public int save(ImportDetails importDetails){
        try {
            importDetailsRepository.save(importDetails);
        }
        catch (Exception ex){
            return  0;
        }
        return 1;
    }
    public List<ImportDetails> findByIdImportProduct(int idImportProduct){
        return importDetailsRepository.findByIdImportProduct(idImportProduct);
    }

    public List<ImportDetails> findByIdProduct(int idProduct){
        return importDetailsRepository.findByIdProduct(idProduct);
    }
}
