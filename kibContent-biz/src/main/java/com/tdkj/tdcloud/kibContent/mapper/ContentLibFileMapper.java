package com.tdkj.tdcloud.kibContent.mapper;

import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库图标文件Mapper接口
 * 
 * @author lgs
 * @date 2023-05-18
 */

@Mapper
public interface ContentLibFileMapper 
{
    /**
     * 查询库图标文件
     * 
     * @param id 库图标文件主键
     * @return 库图标文件
     */
    public ContentLibFile selectContentLibFileById(Long id);
    public List<ContentLibFile> selectContentLibFileByClId(Long clId);

    /**
     * 查询库图标文件列表
     * 
     * @param contentLibFile 库图标文件
     * @return 库图标文件集合
     */
    public List<ContentLibFile> selectContentLibFileList(ContentLibFile contentLibFile);

    /**
     * 新增库图标文件
     * 
     * @param contentLibFile 库图标文件
     * @return 结果
     */
    public int insertContentLibFile(ContentLibFile contentLibFile);

    /**
     * 修改库图标文件
     * 
     * @param contentLibFile 库图标文件
     * @return 结果
     */
    public int updateContentLibFile(ContentLibFile contentLibFile);

    /**
     * 删除库图标文件
     * 
     * @param id 库图标文件主键
     * @return 结果
     */
    public int deleteContentLibFileById(Long id);
    public int deleteContentLibFileByClId(Long clId);
    public int deleteContentLibFileByFileName(@Param("bucketName") String bucketName,@Param("fileName")String fileName);

    /**
     * 批量删除库图标文件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentLibFileByIds(String[] ids);
}
