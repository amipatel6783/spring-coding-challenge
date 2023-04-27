package com.winery.breakdowns.api.dto;


public class ComponentDto{

	
	public double percentage;
    public int year;
    public String variety;
    public String region;
    
    public ComponentDto() {
	}
	
	public ComponentDto(double percentage, int year, String variety, String region) {
		super();
		this.percentage = percentage;
		this.year = year;
		this.variety = variety;
		this.region = region;
	}



	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	@Override
	public String toString() {
		return "ComponentDto [percentage=" + percentage + ", year=" + year + ", variety=" + variety + ", region="
				+ region + "]";
	}
    
 
}