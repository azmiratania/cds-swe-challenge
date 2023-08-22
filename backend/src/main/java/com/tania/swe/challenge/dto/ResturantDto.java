package com.tania.swe.challenge.dto;

import java.util.ArrayList;
import java.util.List;

public class ResturantDto {

	private List<String> resturantList;
	private String selectedResturant;
	private Boolean isSessionActive;

	public ResturantDto() {
		super();
		this.resturantList = new ArrayList<>();

	}

	public List<String> getResturantList() {
		return resturantList;
	}

	public void setResturantList(List<String> resturantList) {
		this.resturantList = resturantList;
	}

	public void addResturant(String resturantName) {
		this.resturantList.add(resturantName);
	}

	public String getSelectedResturant() {
		return selectedResturant;
	}

	public void setSelectedResturant(String selectedResturant) {
		this.selectedResturant = selectedResturant;
	}

	public Boolean getIsSessionActive() {
		return isSessionActive;
	}

	public void setIsSessionActive(Boolean isSessionActive) {
		this.isSessionActive = isSessionActive;
	}

	@Override
	public String toString() {
		return "ResturantDto [resturantList=" + resturantList + ", selectedResturant=" + selectedResturant
				+ ", isSessionActive=" + isSessionActive + "]";
	}

}
