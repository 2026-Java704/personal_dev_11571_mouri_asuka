package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	private String name;

	private String password;

	private String password_confilm;

	//コンストラクタ
	public User() {
	}

	public User(String name, String password, String password_confilm) {
		this.name = name;
		this.password = password;
		this.password_confilm = password_confilm;
	}

	//ゲッターセッター
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfilm() {
		return password_confilm;
	}

	public void setPasswordConfilm(String password_confilm) {
		this.password_confilm = password_confilm;
	}

}
