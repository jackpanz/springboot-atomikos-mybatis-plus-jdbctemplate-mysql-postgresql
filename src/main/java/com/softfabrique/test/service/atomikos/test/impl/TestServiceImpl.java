package com.softfabrique.test.service.atomikos.test.impl;

import com.github.jackpanz.spring.util.MException;
import com.softfabrique.test.entity.mmsql.MmsqlTable;
import com.softfabrique.test.entity.mysql.MysqlTable;
import com.softfabrique.test.mapper.atomikos.mmsql.MmsqlTableMapper;
import com.softfabrique.test.mapper.atomikos.mysql.MysqlTableAtomikosMapper;
import com.softfabrique.test.service.atomikos.mmsql.impl.MmsqlTableAtomikosServiceImpl;
import com.softfabrique.test.service.atomikos.mysql.impl.MysqlTableAtomikosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

@Service
public class TestServiceImpl {

    @Autowired
    MmsqlTableAtomikosServiceImpl mmsqlTableAtomikosServiceImpl;

    @Autowired
    MysqlTableAtomikosServiceImpl mysqlTableAtomikosServiceImpl;

    @Resource(name = "mysqlAtomikosTemplate")
    JdbcTemplate mysqlAtomikosTemplate;

    @Resource(name = "mmsqlAtomikosTemplate")
    JdbcTemplate mmsqlAtomikosTemplate;

    public void saveMmsqlTableObject(String name){
        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName(name);
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Timestamp(new Date().getTime()));
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);
    }

    public void saveMysqlTableObject(String name){
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName(name);
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);
    }

    public void saveMysqlTable(String name){
        mysqlAtomikosTemplate.update("insert into t_mysql_table (name,age,create_date) values (?,?,?)"
                , new Object[]{
                        name, 1, new Date()
                }, new int[]{
                        Types.NVARCHAR, Types.INTEGER, Types.DATE
                });
    }

    public void saveMmsqlTable(String name){
        mmsqlAtomikosTemplate
                .update("insert into t_mmsql_table (name,age,create_date) values (?,?,?)"
                        , new Object[]{
                                name, 1, new Date()
                        }, new int[]{
                                Types.NVARCHAR, Types.INTEGER, Types.DATE
                        });
    }

    @Transactional("atomikosTransaction")
    public void saveMix1() {
        saveMysqlTable("Mix_JdbcTemplate_Mybatis");
        saveMmsqlTableObject("Mix_JdbcTemplate_Mybatis");
    }

    @Transactional("atomikosTransaction")
    public void saveMix1Ex() {
        saveMysqlTable("Mix_JdbcTemplate_Mybatis_Ex");
        if (true) {
            throw new MException("异常");
        }
        saveMmsqlTableObject("Mix_JdbcTemplate_Mybatis_Ex");
    }

    @Transactional("atomikosTransaction")
    public void saveMix0() {
        saveMysqlTableObject("Mix_Mybatis_JdbcTemplate");
        saveMmsqlTable("Mix_Mybatis_JdbcTemplate");
    }

    @Transactional("atomikosTransaction")
    public void saveMix0Ex() {
        saveMysqlTableObject("Mix_Mybatis_JdbcTemplate_Ex");
        saveMmsqlTable("Mix_Mybatis_JdbcTemplate_Ex");
        if (true) {
            throw new MException("异常");
        }
    }

    @Transactional("atomikosTransaction")
    public void saveAtomikos() {
        saveMysqlTableObject("Atomikos");
        saveMmsqlTableObject("Atomikos");
    }

    @Transactional("atomikosTransaction")
    public void saveAtomikosEx() {
        saveMysqlTableObject("AtomikosEx");
        if (true) {
            throw new MException("异常");
        }
        saveMmsqlTableObject("AtomikosEx");
    }

}
