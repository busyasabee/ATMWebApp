package com.dmitrromashov.controller;

import com.dmitrromashov.model.ATMRepair;
import com.dmitrromashov.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile file){
        int savedEntitiesNumber = atmService.uploadFile(file);
        if (savedEntitiesNumber != 0){
            return new ResponseEntity<>(savedEntitiesNumber, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        int updateResult = atmService.updateEntity(id, atrName, atrValue);
        if (updateResult == 0){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
