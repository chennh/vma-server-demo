package com.vma.app.demo.api.system.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.vma.app.demo.api.system.demo.domain.req.DemoReq;
import com.vma.app.demo.api.system.demo.domain.resp.AnotherDemoResp;
import com.vma.app.demo.service.business.demo.service.IDemoService;
import com.vma.assist.wraps.BeanWrap;
import com.vma.model.xxx.demo.domain.entity.Demo;
import com.vma.redis.utils.RedisTemplateUtils;
import com.vma.resource.upload.qiniu.QiniuClient;
import com.vma.resource.upload.qiniu.domain.bo.QiniuTokenBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Demo控制器
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@Api(value = "DemoController", description = "相关", tags = {"DemoController"})
@Validated
@RestController
@RequestMapping("/demo/system/v1.0/demo")
@Slf4j
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    @Autowired
    private QiniuClient qiniuClient;

    /**
     * 分页查询
     *
     * @return
     */
    @ApiOperation(value = "分页获取列表", notes = "分页获取列表")
    @GetMapping
    public Page<Demo> page(@Valid DemoReq demoDTO) {
        log.info("分页获取列表");
        AnotherDemoResp vo = new AnotherDemoResp();
        return demoService.selectPage(new Page<>(demoDTO.getCurrent(), demoDTO.getSize()));
    }

    /**
     * 新增
     *
     * @return
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping
    public void addOtherInfo(@RequestBody DemoReq demoDTO) {
        log.info("新增");
        Demo demo = new Demo();
        BeanWrap.copyProperties(demoDTO, demo);
        // 单个插入
        demoService.insert(demo);
        // 批量插入
//        List<Demo> arrayList = new ArrayList<>();
//        demoService.insertBatch(arrayList);
    }

    /**
     * 编辑
     * 接受dto 转为entity 在业务层进行操作
     *
     * @return
     */
    @ApiOperation(value = "编辑", notes = "编辑")
    @PutMapping
    public void updateOtherInfo(@RequestBody DemoReq demoDTO) {
        log.info("编辑");
        Demo demo = new Demo();
        BeanWrap.copyProperties(demoDTO, demo);
        // 更新实体 为空的字段不更新到数据库
        demoService.updateById(demo);
        // 更新实体  为空的字段到数据库
//        demoService.updateAllColumnById(demo);
    }

    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除信息", notes = "删除信息")
    @DeleteMapping("/{id}")
    public void deleteOtherInfo(@ApiParam(required = true, name = "id", value = "系统编号") @PathVariable("id") Integer id) {
        log.info("删除信息");
        demoService.deleteById(id);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取详情", notes = "获取详情")
    @GetMapping("/{id}")
    public Demo getDetail(@ApiParam(required = true, name = "id", value = "系统编号") @PathVariable("id") Integer id) {
        log.info("获取详情");
        return demoService.selectById(id);
    }

    /**
     * 七牛token
     *
     * @return
     */
    @ApiOperation(value = "获取七牛token")
    @GetMapping("qiniu/token")
    public QiniuTokenBO qiniuTokenBO() {
        return qiniuClient.getToken();
    }

}

