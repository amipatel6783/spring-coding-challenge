package com.winery.breakdowns.api.dto;


public class BreakdownDto {
  
	
	public String percentage;
    public String key;
    
    public BreakdownDto() {
	}
    
	public BreakdownDto(String percentage, String key) {
		this.percentage = percentage;
		this.key = key;
	}
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "Breakdown [percentage=" + percentage + ", key=" + key + "]";
	}

    
    
}