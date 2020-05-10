package com.example.demo.entity.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submit", schema = "performance", catalog = "")
public class SubmitQueryEntity {
	private Integer id;
	private Integer taskId;
	private Integer userId;
	private String note;
	private Integer states;
	private String userName;
	private String taskName;
	private Integer isState;
	private Integer taskScore;
	private String score;
	
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
    @Column(name = "task_id", nullable = false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	@Basic
    @Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Basic
    @Column(name = "note", nullable = false)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	@Basic
    @Column(name = "is_state", nullable = false)
	public Integer getIsState() {
		return isState;
	}
	public void setIsState(Integer isState) {
		this.isState = isState;
	}
	@Basic
    @Column(name = "taskScore", nullable = false)
	public Integer getTaskScore() {
		return taskScore;
	}
	public void setTaskScore(Integer taskScore) {
		this.taskScore = taskScore;
	}
	@Basic
    @Column(name = "score", nullable = false)
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
