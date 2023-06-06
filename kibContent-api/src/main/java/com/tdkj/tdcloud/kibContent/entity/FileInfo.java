package com.tdkj.tdcloud.kibContent.entity;

import lombok.Data;

@Data
public class FileInfo {

	private String bucketName;
	private String fileName;
	private String status;
	private Long uid;
	private String url;

}
