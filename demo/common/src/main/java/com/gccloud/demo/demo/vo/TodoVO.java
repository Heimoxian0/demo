package com.gccloud.demo.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * VO：视图对象，用于接口返回的数据封装
 * 那么，为什么要这样写呢？直接返回Entity不方便吗？
 * ----1、对的，从数据库查询出来结果后，直接返回是很方便，非常方便，but!!! 它不安全，人家需要的是5个属性，你返回了Entity的全部属性，而且还包含了敏感信息。
 * ----2、通过定义VO，可以将Entity对应的属性赋值到VO中，可以控制返回的参数信息
 *
 * @Author qianxing
 * @Date 2020年11月19日09:17:44
 * @Version 1.0.0
 */
@Data
public class TodoVO {

    @ApiModelProperty(notes = "主键")
    private String id;

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
