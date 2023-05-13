package com.cyen.practice.football;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderVO {
    private String id;
    private String authorization;
    private List<BigDecimal> price;
}
