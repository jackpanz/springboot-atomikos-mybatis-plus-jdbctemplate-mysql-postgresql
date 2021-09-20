package com.softfabrique.test.controller;

import com.github.jackpanz.spring.util.ResultMap;
import com.softfabrique.test.service.jta.impl.JTAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestJTAController {

    @Autowired
    private JTAServiceImpl jtaService;

    @ResponseBody
    @RequestMapping(value = "/some_exception")
    public Map some_exception() {
        ResultMap result = new ResultMap(true);
        jtaService.insertSomeException("some_exception");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/some")
    public Map some() {
        ResultMap result = new ResultMap(true);
        jtaService.insertSome("some");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertMybatisPlus")
    public Map insertMybatisPlus() {
        ResultMap result = new ResultMap(true);
        jtaService.insertMybatisPlus("insertMybatisPlus");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertMybatisPlusException")
    public Map insertMybatisPlusException() {
        ResultMap result = new ResultMap(true);
        jtaService.insertMybatisPlusException("insertMybatisPlusException");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertMix")
    public Map insertMix() {
        ResultMap result = new ResultMap(true);
        jtaService.insertMix("insertMix");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertMixException")
    public Map insertMixException() {
        ResultMap result = new ResultMap(true);
        jtaService.insertMixException("insertMixException");
        return result;
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
