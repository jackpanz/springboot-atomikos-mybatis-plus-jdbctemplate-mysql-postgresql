package generator.mmsql;

import generator.MybatisPlusGenerator;

import static generator.MybatisPlusGenerator.DEF_CONF;

public class MmsqlGenerator {

    public static void main(String[] args) {

        DEF_CONF.put("controller", "controller");
        DEF_CONF.put("entity", "entity.mmsql");
        DEF_CONF.put("mapper", "mapper.atomikos.mmsql");
        DEF_CONF.put("xml", "mapper.atomikos.mmsql");
        DEF_CONF.put("serviceImpl", "service.atomikos.mmsql.impl");

        DEF_CONF.put("packageName", "com.softfabrique.test");
        DEF_CONF.put("superEntityClass", "com.github.jackpanz.spring.basic.BasicEntity");
        DEF_CONF.put("entityTemp", "/plus/3.5.0/postgresql/entity.java");
        DEF_CONF.put("pagePath", "");

        DEF_CONF.put("jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        DEF_CONF.put("jdbc.url", "jdbc:sqlserver://192.168.0.10:1433;databasename=atomikos_test");
        DEF_CONF.put("jdbc.user", "sa");
        DEF_CONF.put("jdbc.pass", "sa");

        DEF_CONF.put("tablePrefix", "t_");
        DEF_CONF.put("tables", "t_mmsql_table");


        MybatisPlusGenerator.generator(DEF_CONF,MybatisPlusGenerator.getPostgreSqlTypeConvert());

    }





}