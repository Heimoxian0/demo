package com.gccloud.demo.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gccloud.starter.common.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 在这里定义实体，和数据库字段映射
 *
 * @author qianxing
 * @since 2020年11月18日22:01:13
 */
@Data
@Accessors(chain = true)
/**
 * 这个是你数据库表的名字
 */
@TableName("todo")
@ApiModel
@ToString(callSuper = true)
/**
 * 如果你继承了 SuperEntity 这个类，那么你的表就必须有SuperEntity中定义的列名，否则会报错的
 * 当然，你可以选择不继承他，或者复制SuperEntity中的部分字段到该类中
 */
public class TodoEntity extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "待办事项名称")
    private String name;

    /**
     * 待办事项状态
     * 如果你的项目中用到了常量，请一定要注明如下信息(方便其他同事可以直接点击跳转)
     * 否则简直是浪费其他人的时间去查找你的常量有哪些可选值
     * 而且还会招来别人口头的问候
     */
    @ApiModelProperty(notes = "状态,0是未完成，1是已完成")
    private Integer status;

    @ApiModelProperty(notes = "备注")
    private String remark;
}
