package com.softfabrique.test.controller;

import com.github.jackpanz.spring.util.ResultMap;
import com.softfabrique.test.service.atomikos.jta.impl.JtaServiceImpl;
import com.softfabrique.test.service.standAlone.mysql.impl.MysqlTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TopController {

    @Autowired
    MysqlTableServiceImpl mysqlTableService;

    @Autowired
    JtaServiceImpl jtaService;

    @ResponseBody
    @RequestMapping(value = "saveStandAlone")
    public Map standAloneSave() {
        ResultMap result = new ResultMap(true);
        mysqlTableService.saveStandAlone();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveStandAloneEx")
    public Map saveStandAloneEx() {
        ResultMap result = new ResultMap(true);
        mysqlTableService.saveStandAloneEx();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveAtomikos")
    public Map saveAtomikos() {
        ResultMap result = new ResultMap(true);
        jtaService.saveAtomikos();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveAtomikosEx")
    public Map saveAtomikosEx() {
        ResultMap result = new ResultMap(true);
        jtaService.saveAtomikosEx();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveMix0")
    public Map saveMix0() {
        ResultMap result = new ResultMap(true);
        jtaService.saveMix0();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveMix0Ex")
    public Map saveMix0Ex() {
        ResultMap result = new ResultMap(true);
        jtaService.saveMix0Ex();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveMix1")
    public Map saveMix1() {
        ResultMap result = new ResultMap(true);
        jtaService.saveMix1();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "saveMix1Ex")
    public Map saveMix1Ex() {
        ResultMap result = new ResultMap(true);
        jtaService.saveMix1Ex();
        return result;
    }


    @RequestMapping(value = "/")
    public String index() {
        return "/index";
    }

}
