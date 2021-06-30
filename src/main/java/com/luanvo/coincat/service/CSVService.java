package com.luanvo.coincat.service;

import com.luanvo.coincat.io.request.DownloadCSVRequest;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface CSVService {
    JSONObject importDataFromCSV(MultipartFile file,int id);
    JSONObject downloadCSV(DownloadCSVRequest request, HttpServletResponse response);

    void downloadCSVTrending(HttpServletResponse response);
}
