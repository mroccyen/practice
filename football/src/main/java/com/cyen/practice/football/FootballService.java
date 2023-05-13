package com.cyen.practice.football;

import java.util.Map;

public interface FootballService {

    Map getBillRegion(Map param) throws Exception;

    Map getCrewsList(Map param) throws Exception;

    Map createMatchOrder(Map userParam,Map regionParam);

    Map createOrder() throws Exception;
}
