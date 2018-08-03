package com.dmitrromashov.controller;

import com.dmitrromashov.service.ATMService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    private void deleteData(){
        atmService.deleteData();

    }
}
