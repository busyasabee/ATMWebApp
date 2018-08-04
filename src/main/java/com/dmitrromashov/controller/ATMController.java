package com.dmitrromashov.controller;

import com.dmitrromashov.model.ATMRepair;
import com.dmitrromashov.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class ATMController {
    private ATMService atmService;

    @Autowired
    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }


    @PostMapping("/upload")
    private int uploadFile(@RequestParam("file") MultipartFile file){
        return atmService.uploadFile(file);
    }

    @DeleteMapping("/delete")
    private void deleteData(){
        atmService.deleteData();
    }

    @GetMapping("/show")
    private List<ATMRepair> showData(){
        return atmService.getData();
    }

    @PostMapping("/update")
    private ResponseEntity updateData(@RequestParam int id, @RequestParam String atrName, @RequestParam String atrValue){
        return atmService.updateEntity(id, atrName, atrValue);
    }

//    @PostMapping("/update")
//    private void updateData(@RequestBody String data){
//        int t = 3;
//        atmService.updateEntity(data);
//    }

}
