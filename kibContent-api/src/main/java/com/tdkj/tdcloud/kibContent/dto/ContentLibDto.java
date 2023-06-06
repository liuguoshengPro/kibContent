/*
 *    Copyright (c) 2018-2025, tdcloud All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: tdcloud
 */

package com.tdkj.tdcloud.kibContent.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 库
 *
 * @author pigx code generator
 * @date 2023-05-18 11:07:29
 */
@Data
@TableName("content_lib")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "库")
public class ContentLibDto extends Model<ContentLibDto> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="id")
    private Integer id;

    /**
     * 库中文名
     */
    @Schema(description="库中文名")
    private String chineseName;

    /**
     * 库名
     */
    @Schema(description="库名")
    private String libName;

    /**
     * 摘要
     */
    @Schema(description="摘要")
    private String libAbstract;

    /**
     * 图标
     */
    @Schema(description="图标")
    private String icon;

    /**
     * 创建时间
     */
    @Schema(description="创建时间")
    private LocalDateTime createTime;

    /**
     * 0使用1删除
     */
    @Schema(description="0使用1删除")
    private Integer isDelete;

	@TableField(exist = false)
    private List<ContentLibFile> fileInfoList;

}
