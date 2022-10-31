package com.lr.kingbase.exporter.datakit;

import lombok.Data;

import java.util.Map;

@Data
public class DataKitMetric {
    private String measurement;
    private Map<String,String> tags;
    private Map<String,Object> fields;
    private Long time;
}
