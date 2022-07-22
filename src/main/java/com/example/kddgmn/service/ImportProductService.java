package com.example.kddgmn.service;

import com.example.kddgmn.model.*;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.ImportProductRecive;
import com.example.kddgmn.payload.ListProductImportRecive;
import com.example.kddgmn.repository.ImportDetailsRepository;
import com.example.kddgmn.repository.ImportProductRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public ImportProduct findById(int id){
        return importProductRepository.findById(id).get();
    }

    public int exportReceiptWordFile (int idImportProduct){
        ImportProduct importProduct = importProductRepository.findById(idImportProduct).get();
        List<ImportDetails> importDetailsList = importDetailsRepository.findByIdImportProduct(idImportProduct);

        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("PHIẾU NHẬP KHO");
            run.setFontSize(22);
            run.setBold(true);
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            ///
            XWPFParagraph P0 = document.createParagraph();
            run = P0.createRun();
            run.setFontSize(13);
            P0.setAlignment(ParagraphAlignment.CENTER);
            run.setText("Ngày Nhập: "+importProduct.getDateCreate());

            XWPFParagraph P1 = document.createParagraph();
            run = P1.createRun();
            run.setFontSize(13);
            P1.setAlignment(ParagraphAlignment.CENTER);
            run.setText("Mã số: "+importProduct.getIdImportProduct());

            XWPFParagraph P2 = document.createParagraph();
            run.addBreak();
            run = P2.createRun();
            run.setFontSize(13);
            run.setText("Tên nguồn nhập: "+importProduct.getSourceName());

            //create table
            XWPFTable table = document.createTable();

            //create first row
            XWPFParagraph PT = document.createParagraph();
            run = PT.createRun();
            run.setFontSize(13);
            run.setBold(true);
            PT.setAlignment(ParagraphAlignment.CENTER);

            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setParagraph(PT);
            tableRowOne.getCell(0).setText("STT");
            tableRowOne.addNewTableCell();
            tableRowOne.getCell(1).setParagraph(PT);
            tableRowOne.getCell(1).setText("Tên sản phẩm");
            tableRowOne.addNewTableCell().setText("Mã SP");
            tableRowOne.addNewTableCell().setText("Số lượng");
            tableRowOne.addNewTableCell().setText("Đơn giá");
            tableRowOne.addNewTableCell().setText("Thành tiền");

            Locale locale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            Double totalAll = 0.0;
            for (int i = 0; i < importDetailsList.size(); i++) {
                //create second row
                XWPFTableRow tableRowTwo = table.createRow();
                tableRowTwo.getCell(0).setText(i+1+"");
                tableRowTwo.getCell(1).setText("  " +importDetailsList.get(i).getProduct().getNameProduct()+"  ");
                tableRowTwo.getCell(2).setText("  " +importDetailsList.get(i).getProduct().getIdProduct() +" ");
                tableRowTwo.getCell(3).setText("  " + importDetailsList.get(i).getQuantity()+ "  " );
                tableRowTwo.getCell(4).setText("  " + currencyFormatter.format(importDetailsList.get(i).getPrice()) +"  " );
                Double total = importDetailsList.get(i).getQuantity() * importDetailsList.get(i).getPrice();
                tableRowTwo.getCell(5).setText("  "+ currencyFormatter.format(total)+"  " );
                totalAll += total;
            }
            XWPFParagraph P4 = document.createParagraph();
            run = P4.createRun();
            run.setFontSize(13);
            run.setText("Tổng tiền: "+ currencyFormatter.format(totalAll));

            XWPFParagraph P5 = document.createParagraph();
            run = P5.createRun();
            run.addBreak();
            run.setFontSize(14);
            run.setBold(true);
            run.setText("Người lập phiếu" );

            XWPFParagraph P6 = document.createParagraph();
            run = P6.createRun();
            run.setFontSize(11);
            run.setFontFamily("Segoe Script");
            run.setText(" (Ký,Họ và Tên)");

            XWPFParagraph P7 = document.createParagraph();
            run = P7.createRun();
            run.addBreak();
            run.addBreak();
            run.setFontSize(13);
            run.setFontFamily("Segoe Script");
            run.setText(importProduct.getEmployee().getName());

            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\ADMIN\\Desktop\\Import"+importProduct.getIdImportProduct()+".docx"));
            document.write(out);
            out.close();
            document.close();
        }
        catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
            return 0;
        }
        return 1;
    }
}
