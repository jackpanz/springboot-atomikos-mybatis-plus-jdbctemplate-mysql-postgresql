//package com.softfabrique.test.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.softfabrique.test.entity.mysql.MysqlTable;
//import com.softfabrique.test.service.standAlone.mysql.impl.MysqlTableServiceImpl;
//import com.github.jackpanz.spring.util.ResultMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.Map;
//
///**
//* <p>
//    *  前端控制器
//    * </p>
//*
//* @author
//* @since 2022-04-20
//*/
//
//@Controller
//@RequestMapping("/mysqlTable")
//public class MysqlTableController {
//
//    @Autowired
//    private MysqlTableServiceImpl mysqlTableService;
//
//    @RequestMapping(value = "list")
//    public String list()
//    {
//        return "/mysqlTable/list";
//    }
//
//    @RequestMapping(value = "edit")
//    public String edit()
//    {
//        return "/mysqlTable/edit";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map page(@RequestParam(required = false,defaultValue = "1") Integer page
//                    ,@RequestParam(required = false,defaultValue = "10") Integer length){
//        ResultMap result = new ResultMap(true);
//        IPage pagination = new Page(page, length);
//        pagination = mysqlTableService.page(pagination);
//        result.setPageResult(pagination);
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "get",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map get(Integer id){
//        ResultMap result = new ResultMap();
//        result.put("data",mysqlTableService.getById(id));
//        return result.setSuccess();
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "save",method = {RequestMethod.POST})
//    public Map save(MysqlTable mysqlTable) {
//        ResultMap result = new ResultMap();
//        boolean flag = false;
//        if( mysqlTable.getId() != null ){
//            flag = mysqlTableService.updateById(mysqlTable);
//        } else{
//            flag = mysqlTableService.save(mysqlTable);
//        }
//        return result.setAction(flag);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "delete",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map del(Integer id) {
//        ResultMap result = new ResultMap();
//        if(id != null){
//            result.setAction(mysqlTableService.removeById(id));
//        }
//        return result;
//    }
//
//}