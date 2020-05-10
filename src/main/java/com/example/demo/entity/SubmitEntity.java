package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submit", schema = "performance", catalog = "")
public class SubmitEntity {
	private Integer id;
	private Integer taskId;
	private Integer userId;
	private String note;
	private Integer states;
	private Integer isState;
	private String score;
	private BigDecimal numbers;
	
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
    @Column(name = "is_state", nullable = false)
	public Integer getIsState() {
		return isState;
	}
	public void setIsState(Integer isState) {
		this.isState = isState;
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
    @Column(name = "numbers", nullable = false)
	public BigDecimal getNumbers() {
		return numbers;
	}
	public void setNumbers(BigDecimal numbers) {
		this.numbers = numbers;
	}
}
