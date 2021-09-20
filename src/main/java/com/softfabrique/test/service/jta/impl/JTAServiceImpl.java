package com.softfabrique.test.service.jta.impl;

import com.github.jackpanz.spring.util.MException;
import com.softfabrique.test.entity.database1.Db1Table;
import com.softfabrique.test.entity.database2.Db2Table;
import com.softfabrique.test.mapper.database1.Db1TableMapper;
import com.softfabrique.test.mapper.database2.Db2TableMapper;
import com.softfabrique.test.service.database1.impl.Db1TableServiceImpl;
import com.softfabrique.test.service.database2.impl.Db2TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class JTAServiceImpl {

    @Resource(name = "primaryJdbcTemplate")
    JdbcTemplate primaryJdbcTemplate;

    @Resource(name = "jdbcTemplateSecond")
    JdbcTemplate jdbcTemplateSecond;

    @Autowired
    Db1TableServiceImpl db1TableService;

    @Autowired
    Db2TableServiceImpl db2TableService;

    @Transactional
    public void insertSomeException(String name) {
        String sql1 = " insert into t_db1_demo (name) values ('" + name + "')  ";
        String sql2 = " insert into t_db2_demo (name) values ('" + name + "')  ";
        primaryJdbcTemplate.update(sql1);
        jdbcTemplateSecond.update(sql2);
        throw new MException();
    }

    @Transactional
    public void insertSome(String name) {
        String sql1 = " insert into t_db1_demo (name) values ('" + name + "')  ";
        String sql2 = " insert into t_db2_demo (name) values ('" + name + "')  ";
        primaryJdbcTemplate.update(sql1);
        jdbcTemplateSecond.update(sql2);
    }

    @Transactional
    public void insertMix(String name) {

        String sql1 = " insert into t_db1_demo (name) values ('" + name + "')  ";
        String sql2 = " insert into t_db2_demo (name) values ('" + name + "')  ";
        primaryJdbcTemplate.update(sql1);
        jdbcTemplateSecond.update(sql2);

        Db1Table db1Table = new Db1Table();
        db1Table.setTitle(name);
        db1Table.setCreate_date(new Date());
        db1Table.setStatus(99);
        db1TableService.save(db1Table);

        Db2Table db2Table = new Db2Table();
        db2Table.setTitle(name);
        db2Table.setCreate_date(new Date());
        db2Table.setStatus(99);
        db2TableService.save(db2Table);

    }

    @Transactional
    public void insertMixException(String name) {

        String sql1 = " insert into t_db1_demo (name) values ('" + name + "')  ";
        String sql2 = " insert into t_db2_demo (name) values ('" + name + "')  ";
        primaryJdbcTemplate.update(sql1);
        jdbcTemplateSecond.update(sql2);

        Db1Table db1Table = new Db1Table();
        db1Table.setTitle(name);
        db1Table.setCreate_date(new Date());
        db1Table.setStatus(99);
        db1TableService.save(db1Table);

        Db2Table db2Table = new Db2Table();
        db2Table.setTitle(name);
        db2Table.setCreate_date(new Date());
        db2Table.setStatus(99);
        db2TableService.save(db2Table);
        throw new MException();

    }


    @Transactional
    public void insertMybatisPlus(String name) {
        Db1Table db1Table = new Db1Table();
        db1Table.setTitle(name);
        db1Table.setCreate_date(new Date());
        db1Table.setStatus(99);
        db1TableService.save(db1Table);

        Db2Table db2Table = new Db2Table();
        db2Table.setTitle(name);
        db2Table.setCreate_date(new Date());
        db2Table.setStatus(99);
        db2TableService.save(db2Table);
    }

    @Transactional
    public void insertMybatisPlusException(String name) {
        Db1Table db1Table = new Db1Table();
        db1Table.setTitle(name);
        db1Table.setCreate_date(new Date());
        db1Table.setStatus(99);
        db1TableService.save(db1Table);

        Db2Table db2Table = new Db2Table();
        db2Table.setTitle(name);
        db2Table.setCreate_date(new Date());
        db2Table.setStatus(99);
        db2TableService.save(db2Table);
        throw new MException();
    }

}
