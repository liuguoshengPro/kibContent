package com.tdkj.tdcloud.kibContent.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 库图标文件对象 content_lib_file
 * 
 * @author lgs
 * @date 2023-05-18
 */
@Data
public class ContentLibFile
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 库id */
    private Long clId;

    /**  */
    private String bucketName;

    /**  */
    private String fileName;

    /**  */
    private String status;

    /**  */
    private Long uid;

    /**  */
    private String url;

	private Date createTime;

}
