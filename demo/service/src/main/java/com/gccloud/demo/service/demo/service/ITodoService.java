package com.gccloud.demo.service.demo.service;


import com.gccloud.starter.common.mybatis.page.PageVO;
import com.gccloud.starter.common.mybatis.service.ISuperService;
import com.gccloud.demo.demo.dto.TodoSearchDTO;
import com.gccloud.demo.demo.entity.TodoEntity;

import com.gccloud.starter.common.dto.SearchDTO;

/**
 * 操作的service，一般我们会创建一个接口并且以大写字母I开头（虽然他不是强制的，但是我们推荐的）
 * 注意：
 * ----1、该接口继承的是 ISuperService ，不要写成了 IService 否则会导致有些方法你调不了
 * ----2、ISuperService<T> 中的T是你操作的实体，别写错了，特别是你从其他地方复制过来的时候
 * ----3、这里的所有方法都不用写 public 开头啦，难道你没看到idea的提示吗，不妨去掉试试
 *
 * @Author qianxing
 * @Date 2020年11月18日22:04:54
 * @Version 1.0.0
 */
public interface ITodoService extends ISuperService<TodoEntity> {

    /**
     * 分页查询
     *
     * @param searchDTO
     * @return
     */
    PageVO<TodoEntity> getPage(TodoSearchDTO searchDTO);

    /**
     * 新增代办事项
     *
     * @param todoEntity
     */
    void add(TodoEntity todoEntity);

    /**
     * 删除代办事项
     *
     * @param id
     */
    void delete(String id);

    /**
     * 修改代办事项
     *
     * @param todoEntity
     */
    void update(TodoEntity todoEntity);
}
