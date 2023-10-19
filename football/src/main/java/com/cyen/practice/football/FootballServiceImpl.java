package com.cyen.practice.football;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class FootballServiceImpl implements FootballService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map getBillRegion(Map param) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Referer", "https://servicewechat.com/wxffa42ecd6c0e693d/51/page-frame.html");
        httpHeaders.add("Host", "fccdn1.k4n.cc");
        CreateOrderVO createOrderVO = FetchController.FETCH_LOCAL.get();
        httpHeaders.add("Authorization", createOrderVO.getAuthorization());
        Map map = sendReq("https://fccdn1.k4n.cc/fc/wx_api/v1/MiniApp/getBillRegion", HttpMethod.POST, httpHeaders, null, param, Map.class);
        return map;
    }

    @Override
    public Map getCrewsList(Map regionParam) throws Exception {
        CreateOrderVO createOrderVO = FetchController.FETCH_LOCAL.get();
        HashMap<Object, Object> param = Maps.newHashMap();
        param.put("agree", true);
        param.put("id", createOrderVO.getId());
        param.put("regions", Lists.newArrayList(regionParam));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", createOrderVO.getAuthorization());
        log.info("getCrewsList param {}", JSON.toJSONString(param));
        Map map = sendReq("https://fccdn1.k4n.cc/fc/wx_api/v1/Member/getCrewsList", HttpMethod.POST, httpHeaders, null, param, Map.class);
        log.info("getCrewsList result -> {}", JSON.toJSONString(map));
        return map;
    }

    @Override
    public Map createMatchOrder(Map userParam, Map regionParam) {
        CreateOrderVO createOrderVO = FetchController.FETCH_LOCAL.get();
        regionParam = new HashMap<>(regionParam);
        regionParam.put("num", 1);

        HashMap<Object, Object> param = Maps.newHashMap();
        param.put("agree", true);
        param.put("id", createOrderVO.getId());
        param.put("regions", Lists.newArrayList(regionParam));
        userParam = new HashMap<>(userParam);
        userParam.put("disabled", false);
        userParam.put("disabled2", false);
        userParam.put("showText", userParam.get("realname") + " " + userParam.get("real_card_id"));
        param.put("users", Lists.newArrayList(userParam));

        log.info("createMatchOrder param {}", JSON.toJSONString(param));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", createOrderVO.getAuthorization());
        Map map = sendReq("https://fccdn1.k4n.cc/fc/wx_api/v1/MatchOrder/createMatchOrder", HttpMethod.POST, httpHeaders, null, param, Map.class);
        return map;
    }

    @Override
    public Map createOrder() throws Exception {
        CreateOrderVO createOrderVO = FetchController.FETCH_LOCAL.get();
        List<BigDecimal> priceList = createOrderVO.getPrice();

        HashMap<Object, Object> param = Maps.newHashMap();
        param.put("id", createOrderVO.getId());
        Map billRegion = getBillRegion(param);
        Object data = billRegion.get("data");
        if (data == null) {
            throw new Exception("fetch BillRegionData null");
        }
        List<FootballRegionDTO> footballRegionDTOS = JSON.parseArray(JSON.toJSONString(data), FootballRegionDTO.class);
        if (CollectionUtils.isEmpty(footballRegionDTOS)) {
            throw new Exception("fetch没有票了");
        }
        log.info("footballRegionDTO list --->{}", JSON.toJSONString(footballRegionDTOS));

        Optional<FootballRegionDTO> footballRegionOpt = footballRegionDTOS.stream().filter(region -> region.getUsable_count() > 0 && (CollectionUtils.isEmpty(priceList) || priceList.stream().anyMatch(price -> price.compareTo(region.getMax_price()) == 0))).max(Comparator.comparing(FootballRegionDTO::getMax_price));

        if (!footballRegionOpt.isPresent()) {
            throw new Exception("没选择到好票");
        }
        FootballRegionDTO footballRegionDTO = footballRegionOpt.get();
        log.info("footballRegionDTO --->{}", JSON.toJSONString(footballRegionDTO));
        Optional<FootballRegionDTO.FootballRegionItemDTO> itemFootballRegionDTO = footballRegionDTO.getList().stream().filter(item -> item.getUsable_count() > 0).findFirst();
        if (!itemFootballRegionDTO.isPresent()) {
            throw new Exception("fetch item 没选择到好票");
        }

        Map<String, Object> regionParam = new HashMap<>(BeanUtil.beanToMap(itemFootballRegionDTO.get()));
        regionParam.remove("id");
        regionParam.put("region", footballRegionDTO.getRegion());
        Map crewsList = getCrewsList(regionParam);
        Object crewsListData = crewsList.get("data");
        if (crewsListData == null) {
            throw new Exception("fetch crewsListData null");
        }
        List userList = (List) crewsListData;
        if (CollectionUtils.isEmpty(userList)) {
            throw new Exception("fetch userList null");
        }
        Object user = userList.get(0);
        JSONObject userParam = JSON.parseObject(JSON.toJSONString(user));
        Map matchOrder = createMatchOrder(userParam, regionParam);
        return matchOrder;
    }

    public <T> T sendReq(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Object reqBody, Class<T> resClz) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(reqBody, httpHeaders);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
        ResponseEntity<T> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), httpMethod, requestEntity, resClz);
        T resBody = responseEntity.getBody();
        return resBody;
    }

    public static void main(String[] args) {
        FootballRegionDTO.FootballRegionItemDTO itemFootballRegionDTO = new FootballRegionDTO.FootballRegionItemDTO();
        itemFootballRegionDTO.setEstate(1);
        itemFootballRegionDTO.setName("1");
        itemFootballRegionDTO.setUsable_count(2);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(itemFootballRegionDTO);
        Map<String, Object> regionParam = new HashMap<>(stringObjectMap);
        regionParam.remove("id");
        regionParam.put("region", 2);
        System.out.println(JSON.toJSONString(regionParam));
    }


}
