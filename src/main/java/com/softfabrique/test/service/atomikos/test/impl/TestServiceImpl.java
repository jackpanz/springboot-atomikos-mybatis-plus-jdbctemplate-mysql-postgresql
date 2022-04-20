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

    @Transactional("atomikosTransaction")
    public void saveMix1() {
        mysqlAtomikosTemplate.update("insert into t_mysql_table (name,age,create_date) values (?,?,?)"
                , new Object[]{
                        "Mix_JdbcTemplate_Mybatis", 1, new Date()
                }, new int[]{
                        Types.NVARCHAR, Types.INTEGER, Types.DATE
                });
        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName("Mix_JdbcTemplate_Mybatis");
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Timestamp(new Date().getTime()));
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);
    }

    @Transactional("atomikosTransaction")
    public void saveMix1Ex() {
        mysqlAtomikosTemplate.update("insert into t_mysql_table (name,age,create_date) values (?,?,?)"
                , new Object[]{
                        "Mix_JdbcTemplate_Mybatis", 1, new Date()
                }, new int[]{
                        Types.NVARCHAR, Types.INTEGER, Types.DATE
                });
        if (true) {
            throw new MException("异常");
        }
        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName("Mix_JdbcTemplate_Mybatis");
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Timestamp(new Date().getTime()));
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);

    }

    @Transactional("atomikosTransaction")
    public void saveMix0() {
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("Mix_Mybatis_JdbcTemplate");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);
        mmsqlAtomikosTemplate
                .update("insert into t_mmsql_table (name,age,create_date) values (?,?,?)"
                        , new Object[]{
                                "Mix_Mybatis_JdbcTemplate", 1, new Date()
                        }, new int[]{
                                Types.NVARCHAR, Types.INTEGER, Types.DATE
                        });
    }

    @Transactional("atomikosTransaction")
    public void saveMix0Ex() {
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("Mix_Mybatis_JdbcTemplate_Ex");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);
        mmsqlAtomikosTemplate
                .update("insert into t_mmsql_table (name,age,create_date) values (?,?,?)"
                        , new Object[]{
                                "Mix_Mybatis_JdbcTemplate_Ex", 1, new Date()
                        }, new int[]{
                                Types.NVARCHAR, Types.INTEGER, Types.DATE
                        });
        if (true) {
            throw new MException("异常");
        }
    }


    @Transactional("atomikosTransaction")
    public void saveAtomikos() {

        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("Atomikos");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);

        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName("Atomikos");
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Timestamp(new Date().getTime()));
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);

    }

    @Transactional("atomikosTransaction")
    public void saveAtomikosEx() {

        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("AtomikosEx");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);

        if (true) {
            throw new MException("异常");
        }

        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName("AtomikosEx");
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Date());
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);

    }

}
