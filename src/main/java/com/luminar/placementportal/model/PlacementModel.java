package com.luminar.placementportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "placement")
public class PlacementModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "pl_id")
	private Long plId;
	@Column(name= "pl_job_title")
	private String plJobtitle;
	@Column(name= "pl_company")
	private String plCompany;
	@Column(name= "pl_desc")
	private String plDesc;
	@Column(name= "pl_link")
	private String plLink;
	
	public PlacementModel() {
		
	}

	public Long getPlId() {
		return plId;
	}

	public void setPlId(Long plId) {
		this.plId = plId;
	}

	public String getPlJobtitle() {
		return plJobtitle;
	}

	public void setPlJobtitle(String plJobtitle) {
		this.plJobtitle = plJobtitle;
	}

	public String getPlCompany() {
		return plCompany;
	}

	public void setPlCompany(String plCompany) {
		this.plCompany = plCompany;
	}

	public String getPlDesc() {
		return plDesc;
	}

	public void setPlDesc(String plDesc) {
		this.plDesc = plDesc;
	}

	public String getPlLink() {
		return plLink;
	}

	public void setPlLink(String plLink) {
		this.plLink = plLink;
	}

	
	
	
}
