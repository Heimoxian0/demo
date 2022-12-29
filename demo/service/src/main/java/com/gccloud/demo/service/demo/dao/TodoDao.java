package com.gccloud.demo.service.demo.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gccloud.starter.common.dto.SearchDTO;
import com.gccloud.starter.common.entity.SysUserEntity;
import com.gccloud.starter.common.mybatis.dao.BaseDao;
import com.gccloud.demo.demo.entity.TodoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 这个是用来操作数据库的DAO
 * 注意：
 * ----1、该dao上的注解是 org.apache.ibatis.annotations.Mapper，别导入错了哦
 * ----2、该dao继承的是BaseDao，不要写成BaseMapper哦
 * ----3、BaseDao<T> 中的T是该dao所管理的实体，千万别写错了，特别是你从其他地方复制过来的时候
 *
 * @Author qianxing
 * @Date 2020年11月18日22:01:22
 * @Version 1.0.0
 */
@Mapper
public interface TodoDao extends BaseDao<TodoEntity> {
    /**
     * 分页查询
     *
     * @param page
     * @param searchDTO
     * @return
     */
    Page<TodoEntity> getPageFromXml(Page<SysUserEntity> page, @Param("search") SearchDTO searchDTO);
}
