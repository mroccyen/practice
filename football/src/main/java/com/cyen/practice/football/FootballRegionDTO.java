package com.cyen.practice.football;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FootballRegionDTO {
    private int usable_count;
    private BigDecimal max_price;
    private List<FootballRegionItemDTO> list;
    private int region;

    @Data
    public static class FootballRegionItemDTO {
        private int estate;
        private String name;
        private int usable_count;
    }
}
