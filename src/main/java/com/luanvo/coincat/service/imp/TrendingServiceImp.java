package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Trending;
import com.luanvo.coincat.io.response.TrendingResponse;
import com.luanvo.coincat.repository.TrendingRepository;
import com.luanvo.coincat.service.TrendingService;
import com.luanvo.coincat.utils.ZDateUtils;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrendingServiceImp implements TrendingService {

    @Autowired
    TrendingRepository trendingRepository;

    @Override
    public JSONObject getToday() {
        try{
            JSONObject data = new JSONObject();
            List<TrendingResponse> lsTrending = new ArrayList<>();
            long today = ZDateUtils.getTimeNow();
            List<Trending> lsDB = trendingRepository.findToday(today);
            lsDB.forEach(o->lsTrending.add(TrendingResponse.parse(o)));
            data.put("data",lsTrending);
            return RestResponse.success(data);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
