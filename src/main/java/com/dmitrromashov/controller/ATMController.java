package com.dmitrromashov.controller;

import com.dmitrromashov.model.ATMRepair;
import com.dmitrromashov.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/hello")
    public String hello(){
        return "Hello new";
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

}
