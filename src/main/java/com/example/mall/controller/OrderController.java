package com.example.mall.controller;

import com.example.mall.entity.LogisticsRecord;
import com.example.mall.entity.OrderInfo;
import com.example.mall.entity.Product;
import com.example.mall.entity.Snapshot;
import com.example.mall.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SnapshotRepository snapshotRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> saveOrder(@RequestBody List<Snapshot> snapshots)  {
        OrderInfo order = new OrderInfo();
        Long orderId = orderRepository.save(order).getId();
        double totalPrice=0.0f;
        for(Snapshot snapshot:snapshots){
            snapshotRepository.save(snapshot);
            updateSnapshot(snapshot,orderId);
            totalPrice+=snapshot.getPurchasePrice();
        }
        order.setTotalPrice(totalPrice);
        orderRepository.updateTotalPriceById(orderId,totalPrice);
        return new ResponseEntity<>(order, getResponseHeaderById(orderId), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int updateOrderStatus(@PathVariable Long id, @RequestParam(value = "orderStatus") String orderStatus)  {
        if (orderStatus.equals("paid")) {
            createLogisticsRecord(id);
           return orderRepository.updatePaidStatusById(id,orderStatus,String.valueOf(new Date(System.currentTimeMillis())));
        }

        unlockInventories(id);
        return orderRepository.updateWithdrawnStatusById(id,orderStatus,String.valueOf(new Date(System.currentTimeMillis())));

    }

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OrderInfo getOrder(@PathVariable Long id)  {
        return orderRepository.findById((long)id);
    }
    private void unlockInventories(Long orderId) {
        List<Snapshot> snapshots = snapshotRepository.findByOrderId(orderId);
        snapshots.stream().forEach(snapshot->{
            inventoryRepository.updateLockedCount(snapshot.getProductId(), -snapshot.getPurchaseCount());
        });
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<OrderInfo> getUserOrdersByUser(@RequestParam Long userId) {
        return orderRepository.findByUserId(userId);
    }


    private void updateSnapshot(Snapshot snapshot,Long orderId){
        Product product = productRepository.findById((long)snapshot.getProductId());
        snapshot.update(orderId,product.getName(),product.getDescription(),product.getPrice());
        snapshotRepository.save(snapshot);
        lockInventory(snapshot);
    }

    private void lockInventory(Snapshot snapshot){
        inventoryRepository.updateLockedCount(snapshot.getProductId(), snapshot.getPurchaseCount());
    }
    private HttpHeaders getResponseHeaderById(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://172.20.2.208:8083/orders/" + id));
        return headers;

    }

    private void createLogisticsRecord(Long orderId) {
        LogisticsRecord logisticsRecord = new LogisticsRecord(orderId, "readyToShip");
        logisticsRecordRepository.save(logisticsRecord);
    }
}
