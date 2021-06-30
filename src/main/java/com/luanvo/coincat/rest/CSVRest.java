package com.luanvo.coincat.rest;

import com.luanvo.coincat.io.request.DownloadCSVRequest;
import com.luanvo.coincat.service.CSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/csv")
@CrossOrigin(maxAge = 3600)
public class CSVRest {

    private static final Logger logger = LoggerFactory.getLogger(CSVRest.class);

    @Autowired
    CSVService csvService;

    @GetMapping("/statistic")
    public void exportCSVStatisticCoin(HttpServletResponse response, DownloadCSVRequest request) {
        logger.info("GET /csv/statistic?id=" + request);
        csvService.downloadCSV(request,response);
    }

    @GetMapping("/statistic_trending_last_month")
    public void exportCSVTrending(HttpServletResponse response) {
        logger.info("GET /csv/statistic_trending_last_month");
        csvService.downloadCSVTrending(response);
    }
}
