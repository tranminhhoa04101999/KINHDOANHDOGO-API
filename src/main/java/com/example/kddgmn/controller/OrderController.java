package com.example.kddgmn.controller;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.payload.*;
import com.example.kddgmn.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/allorder")
    public List<Orders> getAll(){
        return  ordersService.getAll();
    }
    @PostMapping("/saveOrder")
    public Integer saveOrder(@RequestBody Orders orders,@RequestParam("name") String name,@RequestParam("thanhToanPaypal") int thanhToanPaypal){
        return ordersService.saveOrder(orders,name,thanhToanPaypal);
    }
    @GetMapping("/searchOrderByIdOrPhone")
    public List<SearchOrderResponse> searchOrderByIdOrPhone(@RequestParam("idStatus") int idStatus, @RequestParam("idOrders") int idOrders, @RequestParam("phone") String phone){
        return ordersService.searchOrderByIdOrPhone(idOrders,idStatus,phone);
    }

    @GetMapping("/searchOrderByIdAccount")
    public List<SearchOrderResponse> searchOrderByIdAccount(@RequestParam("idAccount") int idAccount){
        return ordersService.searchOrderByIdAccount(idAccount);
    }

    @PostMapping("/UpdateStatusByidStatusAndId")
    public Integer UpdateStatusByidStatusAndId(@RequestParam("idStatus") int idStatus,@RequestParam("idOrders") int idOrders,@RequestParam("idEmployee") int idEmployee){
        return ordersService.UpdateStatusByidStatusAndId(idStatus,idOrders,idEmployee);
    }

    @GetMapping("/getDataChartOrders")
    public List<ChartOrdersResponse> getDataChartOrders(){
        return ordersService.getDataChartOrders();
    }

    @GetMapping("/getDataChartTotal")
    public List<ChartTotalResponse> getDataChartTotal(){
        return ordersService.getDataChartTotal();
    }

    @PostMapping("/huyOrder")
    public  int huyOrder(@RequestParam("idOrder") int idOrder){
        return ordersService.huyOrder(idOrder);
    }

    @GetMapping("/getDataThongkeTotal")
    public List<ChartTotalResponse> findBydateBeginAnddateEnd(@RequestParam("begin") Date begin, @RequestParam("end") Date end){
        return ordersService.findBydateBeginAnddateEnd(begin, end);
    }

    @GetMapping("/getDataThongkeOrder")
    public List<ChartOrdersResponse> findBydateBeginAnddateEndAll(@RequestParam("begin") Date begin, @RequestParam("end") Date end){
        return  ordersService.findBydateBeginAnddateEndAll(begin, end);
    }

    @GetMapping("/xuatfilepdf")
    public int xuatfilepdf (@RequestParam("idOrder") int idOrder){
        return ordersService.xuatfilepdf(idOrder);
    }

    @GetMapping("/incomeFindBydateBeginAnddateEnd")
    public List<IncomeStatistical> incomeFindBydateBeginAnddateEnd (@RequestParam("begin") Date begin, @RequestParam("end") Date end){
        return ordersService.incomeFindBydateBeginAnddateEnd(begin, end);
    }

    @GetMapping("/getAllStatusOrder")
    public AllStatusOrder getAllStatusOrder(@RequestParam("begin") Date begin, @RequestParam("end") Date end){
        return ordersService.getAllStatusOrder(begin, end);
    }

    @GetMapping("/statisticalTotalExportFileExcel")
    public ResponseEntity<byte[]> exportStatisticalTotalExcel(@RequestParam("begin") Date begin, @RequestParam("end") Date end){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Thongke.xlsx\"")
                .body(ordersService.exportStatisticalTotalExcel(begin,end));

    }
}
