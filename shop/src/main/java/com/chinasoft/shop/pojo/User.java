package com.chinasoft.shop.pojo;

import java.util.Date;

public class User {
    private String id;

    private String name;

    private String password;

    private String email;

    private String avatar;

    private Double balance;

    private Double total;

    private Integer orderNums;

    private Integer evaluateNums;

    private Integer payNums;

    private Integer receiptNums;

    private String states;

    private String token;
    
    private String phone;
    
    private Date regTime;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(Integer orderNums) {
        this.orderNums = orderNums;
    }

    public Integer getEvaluateNums() {
        return evaluateNums;
    }

    public void setEvaluateNums(Integer evaluateNums) {
        this.evaluateNums = evaluateNums;
    }

    public Integer getPayNums() {
        return payNums;
    }

    public void setPayNums(Integer payNums) {
        this.payNums = payNums;
    }

    public Integer getReceiptNums() {
        return receiptNums;
    }

    public void setReceiptNums(Integer receiptNums) {
        this.receiptNums = receiptNums;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
    
}