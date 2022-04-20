//package com.softfabrique.test.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.softfabrique.test.entity.database2.Db2Table;
//import com.softfabrique.test.service.database2.impl.Db2TableServiceImpl;
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
//* @since 2021-09-20
//*/
//
//@Controller
//@RequestMapping("/db2Table")
//public class Db2TableController {
//
//    @Autowired
//    private Db2TableServiceImpl db2TableService;
//
//    @RequestMapping(value = "list")
//    public String list()
//    {
//        return "/db2Table/list";
//    }
//
//    @RequestMapping(value = "edit")
//    public String edit()
//    {
//        return "/db2Table/edit";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map page(@RequestParam(required = false,defaultValue = "1") Integer page
//                    ,@RequestParam(required = false,defaultValue = "10") Integer length){
//        ResultMap result = new ResultMap(true);
//        IPage pagination = new Page(page, length);
//        pagination = db2TableService.page(pagination);
//        result.setPageResult(pagination);
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "get",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map get(Integer id){
//        ResultMap result = new ResultMap();
//        result.put("data",db2TableService.getById(id));
//        return result.setSuccess();
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "save",method = {RequestMethod.POST})
//    public Map save(Db2Table db2Table) {
//        ResultMap result = new ResultMap();
//        boolean flag = false;
//        if( db2Table.getId() != null ){
//            flag = db2TableService.updateById(db2Table);
//        } else{
//            flag = db2TableService.save(db2Table);
//        }
//        return result.setAction(flag);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "delete",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map del(Integer id) {
//        ResultMap result = new ResultMap();
//        if(id != null){
//            result.setAction(db2TableService.removeById(id));
//        }
//        return result;
//    }
//
//}