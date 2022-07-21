package com.example.kddgmn.payload;

import java.util.List;

public class ImportProductRecive {

    private String sourceName;
    private List<ListProductImportRecive> listProd;

    public ImportProductRecive() {
    }

    public ImportProductRecive(String sourceName, List<ListProductImportRecive> listProd) {
        this.sourceName = sourceName;
        this.listProd = listProd;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<ListProductImportRecive> getListProd() {
        return listProd;
    }

    public void setListProd(List<ListProductImportRecive> listProd) {
        this.listProd = listProd;
    }
}
