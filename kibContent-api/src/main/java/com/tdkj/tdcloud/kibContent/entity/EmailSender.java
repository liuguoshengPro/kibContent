package com.tdkj.tdcloud.kibContent.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EmailSender {

	public String toEmail;
	public String code;
	public String name;
	public String emailType;
	public String passWord;
	public String itemType;

	public Date expireTime;//到期日期

}
