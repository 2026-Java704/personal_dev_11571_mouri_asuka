package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Integer taskId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "category_id")
	private Integer categoryId;

	private String title;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "closing_date")
	private LocalDate closingDate;

	private Integer progress;

	private String memo;

	private Integer time;

	private LocalDate date;

	//	コンストラクタ
	public Task() {
	}

	public Task(Integer categoryId, String title, LocalDate closingDate,
			Integer progress, String memo, Integer time, LocalDate date) {

		//		this.taskId = taskId;
		//		this.userId = userId;
		this.categoryId = categoryId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
		this.time = time;
		this.date = date;

	}

	public Task(Integer categoryId, String title, LocalDate closingDate,
			String memo, Integer time, LocalDate date) {

		//		this.taskId = taskId;
		//		this.userId = userId;
		this.categoryId = categoryId;
		this.title = title;
		this.closingDate = closingDate;
		this.memo = memo;
		this.time = time;
		this.date = date;

	}

	//	ゲッターセッター
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
