package com.gccloud.demo.controller.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.gccloud.starter.common.annation.DataRulePermission;
import com.gccloud.starter.common.constant.GlobalConst;
import com.gccloud.starter.common.dto.SearchDTO;
import com.gccloud.starter.common.mybatis.page.PageVO;
import com.gccloud.starter.common.utils.BeanConvertUtils;
import com.gccloud.starter.common.validator.ValidatorUtils;
import com.gccloud.starter.common.validator.group.Insert;
import com.gccloud.starter.common.validator.group.Update;
import com.gccloud.starter.common.vo.R;
import com.gccloud.starter.core.controller.SuperController;
import com.gccloud.starter.common.dto.RepeatDTO;
import com.gccloud.demo.demo.dto.TodoDTO;
import com.gccloud.demo.demo.dto.TodoSearchDTO;
import com.gccloud.demo.demo.entity.TodoEntity;
import com.gccloud.demo.demo.vo.TodoExcelExportTemplateVO;
import com.gccloud.demo.demo.vo.TodoExcelImportTemplateVO;
import com.gccloud.demo.demo.vo.TodoVO;
import com.gccloud.demo.service.demo.service.ITodoService;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * 大家听我说，这个是对外开放的接口不难理解吧。
 * 注意：
 * ----1、该控制器需要集成 SuperController（因为他给你写好了几个方法，省去了你多敲几个单词，不香吗!!!）
 *
 * @author qianxing
 * @version 1.0.0
 * @date 2020年11月18日22:30:40
 */
@Slf4j
@RestController
@RequestMapping("/todo")
/**
 * 定义在swagger中展示待办事项模块，并且它的接口排序为 10
 */
@Api(tags = "待办事项")
@ApiSort(value = 10)
public class TodoController extends SuperController {

    @Autowired
    private ITodoService todoService;

    /**
     * 分页条件查询
     *
     * @param searchDTO
     * @return
     */
    @GetMapping
    @DataRulePermission(code = "todo:page")
    /**
     * 这里的 position 定义了这个方法对应的接口在该类的所有接口中的排序为10
     */
    @ApiOperation(value = "列表", position = 10, notes = "分页查询列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "searchKey", value = "查询条件", paramType = "query", dataType = "string")
    })
    public R<PageVO<TodoVO>> getPage(@ApiParam(name = "查询", value = "传入查询的业务条件", required = true) TodoSearchDTO searchDTO) {
        PageVO<TodoEntity> page = todoService.getPage(searchDTO);
        PageVO pageVO = BeanConvertUtils.convert(page, PageVO.class);
        pageVO.setList(page.getList());
        return success(pageVO);
    }

    /**
     * 根据id获取待办事项详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "详情", position = 20, notes = "待办详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    public R<TodoVO> getById(@ApiParam(name = "待办事件ID", value = "传入String格式", required = true) @PathVariable("id") String id) {
        // 查询数据数据
        TodoEntity todoEntity = todoService.getById(id);
        // Entity 转 VO 、隐藏部分敏感字段
        TodoVO todoVO = BeanConvertUtils.convert(todoEntity, TodoVO.class);
        // 可以对VO进行特殊处理，如：添加其他字段、字段汉化、数据脱敏
        return success(todoVO);
    }

    /**
     * 新增待办事项
     *
     * @param todoDTO
     * @return
     */
    @PostMapping
    @RequiresPermissions("todo:add")
    @ApiOperation(value = "新增", position = 30, notes = "新增待办事件", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> add(@ApiParam(name = "新增待办事件JSON对象", value = "传入json格式", required = true) @RequestBody TodoDTO todoDTO) {
        // 校验入参是否合法
        ValidatorUtils.validateEntity(todoDTO, Insert.class);
        // DTO 转 Entity，控制字段是否对外开放新增
        TodoEntity todoEntity = BeanConvertUtils.convert(todoDTO, TodoEntity.class);
        todoService.add(todoEntity);
        // 实体中已经有了数据库主键
        return success(todoEntity.getId());
    }

    /**
     * 修改待办事项
     *
     * @param todoDTO
     * @return
     */
    @PutMapping
    @DataRulePermission(code = "todo:update")
    @RequiresPermissions("todo:update")
    @ApiOperation(value = "修改", position = 40, notes = "修改待办事件", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<Void> update(@ApiParam(name = "修改待办事件JSON对象", value = "传入json格式", required = true) @RequestBody TodoDTO todoDTO) {
        // 校验入参是否合法
        ValidatorUtils.validateEntity(todoDTO, Update.class);
        // DTO 转 Entity，控制字段是否对外开放更新
        TodoEntity todoEntity = BeanConvertUtils.convert(todoDTO, TodoEntity.class);
        todoService.update(todoEntity);
        return success();
    }

    /**
     * 根据id删除待办事项
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @DataRulePermission(code = "todo:delete")
    @RequiresPermissions("todo:delete")
    @ApiOperation(value = "删除", notes = "删除待办事件", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<Void> deleteById(@ApiParam(name = "待办事件ID", value = "传入String格式", required = true) @PathVariable("id") String id) {
        todoService.delete(id);
        return success();
    }

    /**
     * 待办事项名称查重
     *
     * @param repeatDTO
     * @return
     */
    @PostMapping("/repeat")
    @ApiOperation(value = "查重", notes = "查重", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<Boolean> repeat(@ApiParam(name = "查重", value = "传入JSON对象", required = true) @RequestBody RepeatDTO repeatDTO) {
        // 校验入参是否合法
        ValidatorUtils.validateEntity(repeatDTO);
        // 使用superService中提供的查重方法
        boolean repeat = todoService.repeat(repeatDTO.getId(), repeatDTO.getName(), repeatDTO.getValue(), GlobalConst.RepeatStrategy.CREATE_BY);
        return success(repeat);
    }

    @PostMapping("/export")
    @ApiOperation(value = "导出", notes = "导出", produces = MediaType.APPLICATION_JSON_VALUE)
    public void export(HttpServletResponse response) throws Exception {
        // 查询待办
        List<TodoEntity> list = todoService.list();
        // 将查询出来的数据转为模板数据
        List<TodoExcelExportTemplateVO> templateList = Lists.newArrayList();
        for (TodoEntity entity : list) {
            TodoExcelExportTemplateVO vo = BeanConvertUtils.convert(entity, TodoExcelExportTemplateVO.class);
            templateList.add(vo);
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 不设置前端无法从header获取文件名
        response.setHeader("Access-Control-Expose-Headers", "filename");
        // 防止中文乱码
        String fileName = URLEncoder.encode("待办导出.xlsx", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
        response.setHeader("filename", fileName);
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(response.getOutputStream(), TodoExcelExportTemplateVO.class);
        writeWorkBook.sheet("待办Sheet").doWrite(templateList);
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入", notes = "导入", produces = MediaType.APPLICATION_JSON_VALUE)
    public R<Void> export(@RequestParam("file") MultipartFile file) throws Exception {
        final List<TodoExcelImportTemplateVO> todoImportTemplateList = Lists.newArrayList();
        EasyExcel.read(file.getInputStream(), TodoExcelImportTemplateVO.class, new AnalysisEventListener<TodoExcelImportTemplateVO>() {
            @Override
            public void invoke(TodoExcelImportTemplateVO data, AnalysisContext context) {
                // 在这里对data先进行缓存，后面进行批量存储
                todoImportTemplateList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 所有解析成功后回调
                log.info("所有数据解析成功,共导入数据 {} 条", todoImportTemplateList.size());
            }
        }).sheet().doRead();
        List<TodoEntity> entityList = Lists.newArrayList();
        for (TodoExcelImportTemplateVO vo : todoImportTemplateList) {
            TodoEntity entity = BeanConvertUtils.convert(vo, TodoEntity.class);
            entityList.add(entity);
        }
        // 批量插入，这里为了演示，没有进行判重
        todoService.saveBatch(entityList);
        return R.success();
    }
}
