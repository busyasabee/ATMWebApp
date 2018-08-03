package com.dmitrromashov.service;

import com.dmitrromashov.dao.ATMRepairDAO;
import com.dmitrromashov.model.ATMRepair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ATMService {
    private ATMRepairDAO atmRepairDAO;

    @Autowired
    public ATMService(ATMRepairDAO atmRepairDAO) {
        this.atmRepairDAO = atmRepairDAO;
    }

    public int uploadFile(MultipartFile xlsxFile){
        List<ATMRepair> atmRepairList = parseXlsx(xlsxFile);
        return atmRepairDAO.saveATMRepairs(atmRepairList);
    }

    private List<ATMRepair> parseXlsx(MultipartFile xlsxFile){
        XSSFWorkbook workbook;
        List<ATMRepair> atmRepairList = new ArrayList<>();

        try {
            byte[] bytes = xlsxFile.getBytes();
            workbook = new XSSFWorkbook(new ByteArrayInputStream(bytes));
            Sheet atmSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = atmSheet.iterator();
            rowIterator.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            while (rowIterator.hasNext()){
                Row currentRow = rowIterator.next();
                ATMRepair atmRepair = new ATMRepair();
                Cell atmCell = currentRow.getCell(0);
                String atmName = atmCell.getStringCellValue();
                atmRepair.setATM(atmName);

                Cell beginRepairCell = currentRow.getCell(1);
                if (beginRepairCell != null){
                    atmRepair.setRepairBeginDate(dateFormat.parse(beginRepairCell.getStringCellValue()));;
                }

                Cell endRepairCell = currentRow.getCell(2);
                if (endRepairCell != null){
                    atmRepair.setRepairEndDate(dateFormat.parse(endRepairCell.getStringCellValue()));;
                }

                Cell workStatusCell = currentRow.getCell(3);
                atmRepair.setWorkingStatus(workStatusCell.getStringCellValue());
                Cell workCost = currentRow.getCell(4);
                atmRepair.setWorkCost((int) workCost.getNumericCellValue());
                atmRepairList.add(atmRepair);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return atmRepairList;
    }

    public void deleteData(){
        atmRepairDAO.clearTable();
    }
}
