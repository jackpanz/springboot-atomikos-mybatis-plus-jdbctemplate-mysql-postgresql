package generator;


import java.io.IOException;
import java.sql.SQLException;

import static generator.MybatisPlusGenerator.DEF_CONF;

/*
•
• 　       　┏┓　　　┏┓
• 　       ┏┛┻━━━┛┻┓
• 　       ┃　　　　　　　┃
• 　       ┃　　　━　　　┃
• 　       ┃　┳┛　┗┳　┃
• 　       ┃　　　　　　　┃
• 　       ┃　　　┻　　　┃
• 　       ┃　　　　　　　┃
• 　       ┗━┓　　　┏━┛
• 　       　　┃　　　┃
• 　       　　┃　　　┃
• 　       　　┃　　　┗━━━┓
• 　       　　┃　　　　　　　┣┓
• 　       　　┃　　　　　　　┏┛
• 　       　　┗┓┓┏━┳┓┏┛
• 　       　　　┃┫┫　┃┫┫
• 　       　　　┗┻┛　┗┻┛
• ━━━━━━ 神兽保佑,代码无BUG ━━━━━━ 
*/

/**
 * <p>
 * 代码生成器演示
 * </p>
 *
 * @author hubin
 * @date 2016-12-01
 */
public class MysqlGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        DEF_CONF.put("controller", "controller");
        DEF_CONF.put("entity", "entity.database2");
        DEF_CONF.put("mapper", "mapper.database2");
        DEF_CONF.put("xml", "mapper.database2");
        DEF_CONF.put("serviceImpl", "service.database2.impl");

        DEF_CONF.put("packageName", "com.softfabrique.test");
        DEF_CONF.put("superEntityClass", "com.github.jackpanz.spring.basic.BasicEntity");
        DEF_CONF.put("pagePath", "");

        DEF_CONF.put("jdbc.driver", "com.mysql.cj.jdbc.Driver");
        DEF_CONF.put("jdbc.url", "jdbc:mysql://127.0.0.1:3306/demo_db?characterEncoding=UTF-8&serverTimezone=GMT%2B8");
        DEF_CONF.put("jdbc.user", "root");
        DEF_CONF.put("jdbc.pass", "root");

        DEF_CONF.put("tablePrefix", "t_");
        DEF_CONF.put("tables", "t_db2_table");

        MybatisPlusGenerator.generator(DEF_CONF,MybatisPlusGenerator.getMySqlTypeConvert());
    }

}


