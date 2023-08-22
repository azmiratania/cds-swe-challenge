package com.tania.swe.challenge.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NonNull;

@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm:ss")
	private LocalDateTime startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm:ss")
	private LocalDateTime endTime;

	@NonNull
	private String viewKey;

	@NonNull
	private String adminKey;
	
	private String name;

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Boolean getActive() {
		return active;
	}
	
	

	public void setActive(Boolean active) {
		this.active = active;
	}


	

	public String getViewKey() {
		return viewKey;
	}

	public void setViewKey(String viewKey) {
		this.viewKey = viewKey;
	}

	public String getAdminKey() {
		return adminKey;
	}

	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}


	private String selectedResturant;

	@OneToMany
	private Collection<Resturant> resturant;

	public Session() {
		super();
		this.startTime = LocalDateTime.now();
		this.resturant = new ArrayList<>();
		this.active = true;
		this.viewKey = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
		this.adminKey = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
	}

	public void addResturant(Resturant resturant) {
		this.resturant.add(resturant);
	}

	public Long getId() {
		return id;
	}

	// Setter methods for session properties
	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public String getSelectedResturant() {
		return selectedResturant;
	}

	public void setSelectedResturant(String selectedResturant) {
		this.selectedResturant = selectedResturant;
	}

	public Collection<Resturant> getResturant() {
		return resturant;
	}

	public void setResturant(Collection<Resturant> resturant) {
		this.resturant = resturant;
	}

	public Boolean isActive() {
		return active;
	}

	public void deactivate() {
		this.active = false;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", active=" + active + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", viewKey=" + viewKey + ", adminKey=" + adminKey + ", name=" + name + ", selectedResturant="
				+ selectedResturant + ", resturant=" + resturant + "]";
	}
}
