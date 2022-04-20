package generator.postgresql;

import generator.MybatisPlusGenerator;

import static generator.MybatisPlusGenerator.DEF_CONF;

public class PostgresqlGenerator {

    public static void main(String[] args) {

        DEF_CONF.put("controller", "controller");
        DEF_CONF.put("entity", "entity.database1");
        DEF_CONF.put("mapper", "mapper.database1");
        DEF_CONF.put("xml", "mapper.database1");
        DEF_CONF.put("serviceImpl", "service.database1.impl");

        DEF_CONF.put("packageName", "com.softfabrique.test");
        DEF_CONF.put("superEntityClass", "com.github.jackpanz.spring.basic.BasicEntity");
        DEF_CONF.put("entityTemp", "/plus/3.5.0/postgresql/entity.java");
        DEF_CONF.put("pagePath", "");

        DEF_CONF.put("jdbc.driver", "org.postgresql.Driver");
        DEF_CONF.put("jdbc.url", "jdbc:postgresql://127.0.0.1:5432/db1");
        DEF_CONF.put("jdbc.user", "postgres");
        DEF_CONF.put("jdbc.pass", "123456");

        DEF_CONF.put("tablePrefix", "t_");
        DEF_CONF.put("tables", "t_db1_table");


        MybatisPlusGenerator.generator(DEF_CONF,MybatisPlusGenerator.getPostgreSqlTypeConvert());

    }





}