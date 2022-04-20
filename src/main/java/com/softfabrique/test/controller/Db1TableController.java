//package com.softfabrique.test.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.softfabrique.test.service.database1.impl.Db1TableServiceImpl;
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
//@RequestMapping("/db1Table")
//public class Db1TableController {
//
//    @Autowired
//    private Db1TableServiceImpl db1TableService;
//
//    @RequestMapping(value = "list")
//    public String list()
//    {
//        return "/db1Table/list";
//    }
//
//    @RequestMapping(value = "edit")
//    public String edit()
//    {
//        return "/db1Table/edit";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map page(@RequestParam(required = false,defaultValue = "1") Integer page
//                    ,@RequestParam(required = false,defaultValue = "10") Integer length){
//        ResultMap result = new ResultMap(true);
//        IPage pagination = new Page(page, length);
//        pagination = db1TableService.page(pagination);
//        result.setPageResult(pagination);
//        return result;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "get",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map get(Integer id){
//        ResultMap result = new ResultMap();
//        result.put("data",db1TableService.getById(id));
//        return result.setSuccess();
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "save",method = {RequestMethod.POST})
//    public Map save(Db1Table db1Table) {
//        ResultMap result = new ResultMap();
//        boolean flag = false;
//        if( db1Table.getId() != null ){
//            flag = db1TableService.updateById(db1Table);
//        } else{
//            flag = db1TableService.save(db1Table);
//        }
//        return result.setAction(flag);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "delete",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map del(Integer id) {
//        ResultMap result = new ResultMap();
//        if(id != null){
//            result.setAction(db1TableService.removeById(id));
//        }
//        return result;
//    }
//
//}