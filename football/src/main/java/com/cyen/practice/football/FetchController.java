package com.cyen.practice.football;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FetchController {
    public final static ThreadLocal<CreateOrderVO> FETCH_LOCAL = new ThreadLocal<>();

    @Autowired
    private FootballService footballService;

    @RequestMapping(value = "/get-region", method = {RequestMethod.POST})
    public Map createOrder() throws Exception {
        HashMap<Object, Object> param = Maps.newHashMap();
        param.put("id", 8);
        return footballService.getBillRegion(param);
    }

    @RequestMapping(value = "/create-order", method = {RequestMethod.POST})
    public Map createOrder(@RequestBody CreateOrderVO param) throws Exception {
        FETCH_LOCAL.set(param);
        return footballService.createOrder();
    }
}