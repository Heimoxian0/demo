package com.gccloud.demo.service.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gccloud.starter.common.dto.SearchDTO;
import com.gccloud.starter.common.exception.GlobalException;
import com.gccloud.starter.common.mybatis.page.PageVO;
import com.gccloud.starter.common.mybatis.utils.QueryWrapperUtils;
import com.gccloud.demo.demo.dto.TodoSearchDTO;
import com.gccloud.demo.demo.entity.TodoEntity;
import com.gccloud.demo.service.demo.dao.TodoDao;
import com.gccloud.demo.service.demo.service.ITodoService;
import org.springframework.stereotype.Service;

/**
 * service的实现类，一般我们会在后面加上Impl结尾（虽然他不是强制的，但是我们推荐的）
 * 注意：
 * ----1、千万不要忘记在类上加上注解 @Service
 * ----2、需要集成 ServiceImpl<dao,entity> ，其中dao是你之前定义的，你应该知道的吧，entity也是你定义的Entity
 * ----3、实现之前定义的接口这个你懂的吧，我们要面向接口开发
 *
 * @Author qianxing
 * @Date 2020年11月18日22:08:55
 * @Version 1.0.0
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoDao, TodoEntity> implements ITodoService {

    @Override
    public PageVO<TodoEntity> getPage(TodoSearchDTO searchDTO) {
        // 假设根据 name、remark进行模糊查询
        LambdaQueryWrapper<TodoEntity> queryWrapper = QueryWrapperUtils.wrapperLike(new LambdaQueryWrapper<>(),
                searchDTO.getSearchKey(), TodoEntity::getName, TodoEntity::getRemark);
        // 根据状态筛选
        if (searchDTO.getStatus() != null) {
            queryWrapper.eq(TodoEntity::getStatus, searchDTO.getStatus());
        }
        /**
         * 看清楚这里的方法带上了 WithDp，意思是：携带数据规则，是需要在菜单中进行配置、角色进行授权才可以访问的，会自动拼接SQL到Where后面
         * 如果没有该需求、你可以使用不带 WithDp 的方法
         */
        return pageWithDp(searchDTO, queryWrapper);
    }

    @Override
    public void add(TodoEntity todoEntity) {
        checkRepeat(todoEntity);
        this.save(todoEntity);
        /**
         * 举手：如果我想获取当前新增的这个数据的ID,我该怎么办
         * 回答：恩，你可以试试 todoEntity.getId() 看看是不是获取到值了呢
         */
    }

    @Override
    public void delete(String id) {
        /**
         * 看清楚这里的方法带上了 WithDp，意思是：携带数据规则，是需要在菜单中进行配置、角色进行授权才可以访问的，会自动拼接SQL到Where后面
         * 如果没有该需求、你可以使用不带 WithDp 的方法
         */
        deleteByIdWithDp(id);
    }

    @Override
    public void update(TodoEntity todoEntity) {
        checkRepeat(todoEntity);
        /**
         * 看清楚这里的方法带上了 WithDp，意思是：携带数据规则，是需要在菜单中进行配置、角色进行授权才可以访问的，会自动拼接SQL到Where后面
         * 如果没有该需求、你可以使用不带 WithDp 的方法
         */
        updateByIdWithDp(todoEntity);
    }

    /**
     * 重复检查
     *
     * @param todoEntity
     */
    private void checkRepeat(TodoEntity todoEntity) {
        /**
         * 注意看下该方法是在哪里的？他有很多其他判重的方法，你不去了了解下吗
         */
        boolean repeat = repeatCreateBy(TodoEntity::getId, todoEntity.getId(), TodoEntity::getName, todoEntity.getName());
        if (repeat) {
            /**
             * 有些错误直接抛出去就完事，我们有全局捕获异常机制
             */
            throw new GlobalException(String.format("【%s】已存在", todoEntity.getName()));
        }
    }

}
