package com.example.currency.model;

import java.util.HashMap;
import java.util.Map;

public class Quotes {

    private Double USDRUB;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getUSDRUB() {
        return USDRUB;
    }

    public void setUSDRUB(Double USDRUB) {
        this.USDRUB = USDRUB;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
