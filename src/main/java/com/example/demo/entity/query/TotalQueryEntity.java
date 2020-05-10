package com.example.demo.entity.query;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TotalQueryEntity {
	private Integer id;
	private String name;
	private double number;
	private String levelA;
	private String levelB;
	private String levelC;
	private String levelD;
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
    @Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
    @Column(name = "number", nullable = false)
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	@Basic
    @Column(name = "levelA", nullable = false)
	public String getLevelA() {
		return levelA;
	}
	public void setLevelA(String levelA) {
		this.levelA = levelA;
	}
	@Basic
    @Column(name = "levelB", nullable = false)
	public String getLevelB() {
		return levelB;
	}
	public void setLevelB(String levelB) {
		this.levelB = levelB;
	}
	@Basic
    @Column(name = "levelC", nullable = false)
	public String getLevelC() {
		return levelC;
	}
	public void setLevelC(String levelC) {
		this.levelC = levelC;
	}
	@Basic
    @Column(name = "levelD", nullable = false)
	public String getLevelD() {
		return levelD;
	}
	public void setLevelD(String levelD) {
		this.levelD = levelD;
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
