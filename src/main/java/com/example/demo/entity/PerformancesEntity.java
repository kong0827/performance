package com.example.demo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performances", schema = "performance", catalog = "")
public class PerformancesEntity {
	private Integer id;
	private String dimension;
	private Integer indicatorsId;
	private String assessment;
	private String lackOf;
	private String bottomLine;
	private String standard;
	private String challenge;
	private String weight;
	private String completion;
	private String score;
	private String superiorGrade;
	private String superiorEvaluation;
	private Integer userId;
	private Integer audit;
	private String note;
	private Integer taskId;
	private Integer states;
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
    @Column(name = "dimension", nullable = false)
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	@Basic
    @Column(name = "indicators_id", nullable = false)
	public Integer getIndicatorsId() {
		return indicatorsId;
	}
	public void setIndicatorsId(Integer indicatorsId) {
		this.indicatorsId = indicatorsId;
	}
	@Basic
    @Column(name = "assessment", nullable = false)
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	@Basic
    @Column(name = "lack_of", nullable = false)
	public String getLackOf() {
		return lackOf;
	}
	public void setLackOf(String lackOf) {
		this.lackOf = lackOf;
	}
	@Basic
    @Column(name = "bottom_line", nullable = false)
	public String getBottomLine() {
		return bottomLine;
	}
	public void setBottomLine(String bottomLine) {
		this.bottomLine = bottomLine;
	}
	@Basic
    @Column(name = "standard", nullable = false)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@Basic
    @Column(name = "challenge", nullable = false)
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	@Basic
    @Column(name = "weight", nullable = false)
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Basic
    @Column(name = "completion", nullable = false)
	public String getCompletion() {
		return completion;
	}
	public void setCompletion(String completion) {
		this.completion = completion;
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
    @Column(name = "superior_grade", nullable = false)
	public String getSuperiorGrade() {
		return superiorGrade;
	}
	public void setSuperiorGrade(String superiorGrade) {
		this.superiorGrade = superiorGrade;
	}
	@Basic
    @Column(name = "superior_evaluation", nullable = false)
	public String getSuperiorEvaluation() {
		return superiorEvaluation;
	}
	public void setSuperiorEvaluation(String superiorEvaluation) {
		this.superiorEvaluation = superiorEvaluation;
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
    @Column(name = "audit", nullable = false)
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
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
    @Column(name = "task_id", nullable = false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
    @Column(name = "is_score", nullable = false)
	public Integer getIsScore() {
		return isScore;
	}
	public void setIsScore(Integer isScore) {
		this.isScore = isScore;
	}
}
