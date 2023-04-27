package com.winery.breakdowns.api.dto;

import java.util.List;

public class BreakdownResponse{

	
	public String breakdownType;
    public List<BreakdownDto> breakdown;
    
    public BreakdownResponse() {
	}
    
	public BreakdownResponse(String breakdownType, List<BreakdownDto> breakdown) {
		super();
		this.breakdownType = breakdownType;
		this.breakdown = breakdown;
	}

    
	public String getBreakdownType() {
		return breakdownType;
	}



	public void setBreakdownType(String breakdownType) {
		this.breakdownType = breakdownType;
	}



	public List<BreakdownDto> getBreakdown() {
		return breakdown;
	}



	public void setBreakdown(List<BreakdownDto> breakdown) {
		this.breakdown = breakdown;
	}


	@Override
	public String toString() {
		return "BreakdownResponse [breakdownType=" + breakdownType + ", breakdown=" + breakdown + "]";
	}

  
}