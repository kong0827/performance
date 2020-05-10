package com.example.demo.entity.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task", schema = "performance", catalog = "")
public class TaskQueryEntity {
	private Integer id;
	private String name;
	private String type;
	private String startTime;
	private String endTime;
	private Integer templateId;
	private Integer states;
	private String tempName;
	private Integer isScore;
	
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
    @Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
    @Column(name = "type", nullable = false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Basic
    @Column(name = "start_time", nullable = false)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Basic
    @Column(name = "end_time", nullable = false)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Basic
    @Column(name = "template_id", nullable = false)
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	@Basic
    @Column(name = "states", nullable = false)
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	@Basic
    @Column(name = "tempName", nullable = false)
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	@Basic
    @Column(name = "is_score", nullable = false)
	public Integer getIsScore() {
		return isScore;
	}
	public void setIsScore(Integer isScore) {
		this.isScore = isScore;
	}
}
