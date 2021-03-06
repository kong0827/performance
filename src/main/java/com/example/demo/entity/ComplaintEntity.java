package com.example.demo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint", schema = "performance", catalog = "")
public class ComplaintEntity {
	private Integer id;
	private Integer submitId;
	private String problem;
	private Integer states;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Basic
    @Column(name = "submit_id", nullable = false)
	public Integer getSubmitId() {
		return submitId;
	}
	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}
	@Basic
    @Column(name = "problem", nullable = false)
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	@Basic
    @Column(name = "states", nullable = false)
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
}
