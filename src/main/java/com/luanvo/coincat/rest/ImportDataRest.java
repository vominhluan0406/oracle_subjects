package com.luanvo.coincat.rest;

import com.luanvo.coincat.io.request.RandomValueRequest;
import com.luanvo.coincat.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/data")
public class ImportDataRest {

    private static Logger logger = LoggerFactory.getLogger(ImportDataRest.class);

    @Autowired
    CSVService csvService;

    @PostMapping("/import-scv/{id}")
    public Object importFromCSV(@RequestParam("file") MultipartFile file_import,@PathVariable("id") int id){
        return csvService.importDataFromCSV(file_import,id);
    }
}
