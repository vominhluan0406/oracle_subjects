package com.luanvo.coincat.rest;

import com.luanvo.coincat.request.RandomValueRequest;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/random")
public class RandomValueCoin {

    @GetMapping("/create")
    public Object randomValue(RandomValueRequest request){
        return new Date(request.getStart_date());
    }
}
