package com.example.mall.controller;

import com.example.mall.entity.LogisticsRecord;
import com.example.mall.repository.LogisticsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@EnableAutoConfiguration
@RequestMapping("/logisticsRecords")
public class LogisticsRecordController {

    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;


    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LogisticsRecord getLogisticsRecord(@PathVariable Long id)  {
       return logisticsRecordRepository.findById((long)id);
    }

    @RequestMapping(value="/{id}/orders/{orderId}",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int updateLogisticsStatus(@PathVariable Long id, @PathVariable Long orderId, @RequestParam String logisticsStatus) {
        if(logisticsStatus.equals("shipping")){
           return logisticsRecordRepository.updateShippingStatus(id,orderId,String.valueOf(new Date(System.currentTimeMillis())));
        }
        return logisticsRecordRepository.updateSignedStatus(id,orderId,String.valueOf(new Date(System.currentTimeMillis())));
    }
}
