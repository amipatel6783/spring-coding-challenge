package com.winery.breakdowns.api.dto;

import java.util.List;

public class WineDto {
	
   
	
	public String lotCode;
    public double volume;
    public String description;
    public String tankCode;
    public String productState;
    public String ownerName;
    public List<ComponentDto> components;
    
    
    
	public WineDto() {
		super();
	}


	public WineDto(String lotCode, double volume, String description, String tankCode, String productState,
			String ownerName, List<ComponentDto> components) {
		super();
		this.lotCode = lotCode;
		this.volume = volume;
		this.description = description;
		this.tankCode = tankCode;
		this.productState = productState;
		this.ownerName = ownerName;
		this.components = components;
	}


	public String getLotCode() {
		return lotCode;
	}


	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTankCode() {
		return tankCode;
	}


	public void setTankCode(String tankCode) {
		this.tankCode = tankCode;
	}


	public String getProductState() {
		return productState;
	}


	public void setProductState(String productState) {
		this.productState = productState;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public List<ComponentDto> getComponents() {
		return components;
	}


	public void setComponents(List<ComponentDto> components) {
		this.components = components;
	}


	@Override
	public String toString() {
		return "WineDto [lotCode=" + lotCode + ", volume=" + volume + ", description=" + description + ", tankCode="
				+ tankCode + ", productState=" + productState + ", ownerName=" + ownerName + ", components="
				+ components + "]";
	}
    
    
 
}