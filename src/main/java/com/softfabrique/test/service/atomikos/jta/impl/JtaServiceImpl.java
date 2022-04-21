package com.softfabrique.test.service.atomikos.jta.impl;

import com.github.jackpanz.spring.util.MException;
import com.softfabrique.test.entity.mmsql.MmsqlTable;
import com.softfabrique.test.entity.mysql.MysqlTable;
import com.softfabrique.test.entity.postgresql.PostgresqlTable;
import com.softfabrique.test.service.atomikos.mmsql.impl.MmsqlTableAtomikosServiceImpl;
import com.softfabrique.test.service.atomikos.mysql.impl.MysqlTableAtomikosServiceImpl;
import com.softfabrique.test.service.atomikos.postgresql.impl.PostgresqlTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Date;

@Service
public class JtaServiceImpl {

    @Autowired
    MmsqlTableAtomikosServiceImpl mmsqlTableAtomikosServiceImpl;

    @Autowired
    MysqlTableAtomikosServiceImpl mysqlTableAtomikosServiceImpl;

    @Autowired
    PostgresqlTableServiceImpl postgresqlTableService;

    @Resource(name = "mysqlAtomikosTemplate")
    JdbcTemplate mysqlAtomikosTemplate;

    @Resource(name = "mmsqlAtomikosTemplate")
    JdbcTemplate mmsqlAtomikosTemplate;

    @Resource(name = "postgresqlJdbcTemplate")
    JdbcTemplate postgresqlJdbcTemplate;

    public void savePostgresqlObject(String name){
        PostgresqlTable mmsqlTable = new PostgresqlTable();
        mmsqlTable.setName(name);
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Date());
        postgresqlTableService.save(mmsqlTable);
    }

    public void saveMmsqlObject(String name){
        MmsqlTable mmsqlTable = new MmsqlTable();
        mmsqlTable.setName(name);
        mmsqlTable.setAge(1);
        mmsqlTable.setCreate_date(new Date());
        mmsqlTableAtomikosServiceImpl.save(mmsqlTable);
    }

    public void saveMysqlObject(String name){
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName(name);
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        mysqlTableAtomikosServiceImpl.save(mysqlTable);
    }

    public void savePostgresqlTable(String name){
        postgresqlJdbcTemplate.update("insert into t_postgresql_table (id,name,age,create_date) values (nextval('seq_postgresql_table') ,?,?,?)"
                , new Object[]{
                        name, 1, new Date()
                }, new int[]{
                        Types.VARCHAR, Types.INTEGER, Types.DATE
                });
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
                                Types.VARCHAR, Types.INTEGER, Types.DATE
                        });
    }

    @Transactional("atomikosTransaction")
    public void saveMix1() {
        saveMysqlTable("Mix_JdbcTemplate_Mybatis");
        saveMmsqlObject("Mix_JdbcTemplate_Mybatis");
        savePostgresqlTable("Mix_Mybatis_JdbcTemplate");
    }

    @Transactional("atomikosTransaction")
    public void saveMix1Ex() {
        saveMysqlTable("Mix_JdbcTemplate_Mybatis_Ex");
        if (true) {
            throw new MException("异常");
        }
        saveMmsqlObject("Mix_JdbcTemplate_Mybatis_Ex");
        savePostgresqlTable("Mix_Mybatis_JdbcTemplate");
    }

    @Transactional("atomikosTransaction")
    public void saveMix0() {
        saveMysqlObject("Mix_Mybatis_JdbcTemplate");
        saveMmsqlTable("Mix_Mybatis_JdbcTemplate");
        savePostgresqlObject("Mix_Mybatis_JdbcTemplate");
    }

    @Transactional("atomikosTransaction")
    public void saveMix0Ex() {
        saveMysqlObject("Mix_Mybatis_JdbcTemplate_Ex");
        saveMmsqlTable("Mix_Mybatis_JdbcTemplate_Ex");
        if (true) {
            throw new MException("异常");
        }
        savePostgresqlObject("Mix_Mybatis_JdbcTemplate_Ex");
    }

    @Transactional("atomikosTransaction")
    public void saveAtomikos() {
        saveMysqlObject("Atomikos");
        saveMmsqlObject("Atomikos");
        savePostgresqlObject("Atomikos");
    }

    @Transactional("atomikosTransaction")
    public void saveAtomikosEx() {
        saveMysqlObject("AtomikosEx");
        if (true) {
            throw new MException("异常");
        }
        saveMmsqlObject("AtomikosEx");
        savePostgresqlObject("AtomikosEx");
    }

}
