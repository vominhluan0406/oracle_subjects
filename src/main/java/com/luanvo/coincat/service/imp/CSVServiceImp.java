package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.CurrencyValue;
import com.luanvo.coincat.io.request.DownloadCSVRequest;
import com.luanvo.coincat.repository.CurrencyValueRepository;
import com.luanvo.coincat.service.CSVService;
import com.luanvo.coincat.utils.ZDateUtils;
import com.luanvo.coincat.values.RestResponse;
import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CSVServiceImp implements CSVService {

    @Autowired
    CurrencyValueRepository currencyValueRepository;

    @Override
    public JSONObject importDataFromCSV(MultipartFile fileRequest, int coint_id) {
        try {
            File file = this.convertMultipartFileToFile(fileRequest);
            CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()));
            List<String[]> data = reader.readAll();
            data.remove(data.get(0));

            data.forEach(o -> {
                String[] date = o[0].split("-");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(date[0] == null ? "0" : date[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(date[1] == null ? "1" : date[1]) - 1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2] == null ? "0" : date[2]));

                CurrencyValue entity = new CurrencyValue();
                entity.setDate(calendar.getTime());
                entity.setOpen(Double.parseDouble(o[1]));
                entity.setHigh(Double.parseDouble(o[2]));
                entity.setLow(Double.parseDouble(o[3]));
                entity.setClose(Double.parseDouble(o[4]));
                entity.setAdj_close(Double.parseDouble(o[5]));
                entity.setVolume(Double.parseDouble(o[6]));
                entity.setCurrency_id(coint_id);

                currencyValueRepository.save(entity);
            });
            return RestResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject downloadCSV(DownloadCSVRequest request, HttpServletResponse response) {
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=" + request.getCoin_id() + ".csv");

            OutputStream outputStream = response.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write('\ufeff');
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("STT", "Date", "Open", "High", "Low", "Close", "Adj Close", "Volume"));

            //Data
            List<CurrencyValue> listDB = currencyValueRepository.findAllByRequest(request.getCoin_id(),
                    ZDateUtils.getDateStr(request.getStart()), ZDateUtils.getDateStr(request.getEnd()));

            AtomicInteger counter = new AtomicInteger(1);

            listDB.forEach(o -> {
                try {
                    csvPrinter.printRecord(counter.incrementAndGet(), ZDateUtils.getDateStr(o.getDate().getTime()), o.getOpen(), o.getHigh(), o.getLow(), o.getClose(), o.getAdj_close(), o.getVolume());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            });

            csvPrinter.flush();
            csvPrinter.close();
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
