package com.luanvo.coincat.rest;

import com.luanvo.coincat.io.request.DownloadCSVRequest;
import com.luanvo.coincat.service.CSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/csv")
public class CSVRest {

    private static final Logger logger = LoggerFactory.getLogger(CSVRest.class);

    @Autowired
    CSVService csvService;

    @GetMapping("/statistic")
    public void exportCSVStatisticCoin(HttpServletResponse response, DownloadCSVRequest request) {
        logger.info("GET /csv/statistic?id=" + request);
        csvService.downloadCSV(request,response);
    }
}
