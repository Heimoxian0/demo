package com.gccloud.demo.demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * excel 导入模板
 * 基于EasyExcel实现，官网案例 https://www.yuque.com/easyexcel/doc/easyexcel
 *
 * @Author qianxing
 * @Date 2020年11月19日09:17:44
 * @Version 1.0.0
 */
@Data
public class TodoExcelImportTemplateVO {

    @ColumnWidth(40)
    @ExcelProperty(value = "待办事项名称", index = 0)
    private String name;

    @ColumnWidth(30)
    @ExcelProperty(value = "状态", index = 1)
    private Integer status;

    @ColumnWidth(40)
    @ExcelProperty(value = "备注", index = 2)
    private String remark;
}
