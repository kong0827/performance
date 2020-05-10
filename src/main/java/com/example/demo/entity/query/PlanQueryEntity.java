package com.example.demo.entity.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlanQueryEntity {
	private Integer id;
	private Integer submitId;
	private String content;
	private Integer states;
	private String score;
	private String userName;
	private String taskName;
	
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
    @Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
    @Column(name = "score", nullable = false)
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Basic
    @Column(name = "userName", nullable = false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Basic
    @Column(name = "taskName", nullable = false)
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
