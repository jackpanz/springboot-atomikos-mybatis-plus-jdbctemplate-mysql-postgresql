package generator;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, String> conf = new HashMap() {
        {

            //jdbc
            put("jdbc.driver", "com.mysql.cj.jdbc.Driver");
            put("jdbc.url", "jdbc:mysql://127.0.0.1:3306/demo_db?characterEncoding=UTF-8&serverTimezone=GMT%2B8");
            put("jdbc.user", "root");
            put("jdbc.pass", "root");

            //config
            put("packageName", "com.softfabrique.test");
            put("superEntityClass", "com.github.jackpanz.spring.basic.BasicEntity");
            put("pagePath", "");
            put("controller", "controller");
            put("entity", "entity.database2");
            put("mapper", "mapper.database2");
            put("xml", "mapper.database2");
            put("serviceImpl", "service.database2.impl");

            //tables
            put("tables",
//                    ",t_admin_user_test" +
//                            ",t_jobs" +
//                            "t_test" +
                    "t_db2_table"
            );

            put("tablePrefix", "t_");
            put("pagePath", "");

        }
    };

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        generator();
    }


    public static void generator() {


        MySqlTypeConvert mySqlTypeConvert = new MySqlTypeConvert(){
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                if (fieldType.toLowerCase().contains("point")) {
                    return DbColumnType.OBJECT;
                }
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().contains("blob")) {
                    return DbColumnType.BYTE_ARRAY;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        };

        String pagePath = conf.get("pagePath");
        if (StringUtils.isNotBlank(pagePath)) {
            if (!pagePath.startsWith("/")) {
                pagePath = "/" + pagePath;
            }
            if (pagePath.endsWith("/")) {
                pagePath = pagePath.substring(0, pagePath.length() - 1);
            }
            conf.put("pagePath", pagePath);
        }

        // 自定义需要填充的字段
        final String projectRoot = System.getProperty("user.dir");

        String finalPagePath = pagePath;
        AutoGenerator mpg = new AutoGenerator(
                new DataSourceConfig.Builder(conf.get("jdbc.url"),conf.get("jdbc.user"),conf.get("jdbc.pass"))
                        .typeConvert(mySqlTypeConvert)
                        .build()
        ).global(
                new GlobalConfig.Builder()
                        .outputDir(projectRoot + "/generator/java/")
                        .fileOverride()
                        .build()
        ).strategy(
                new StrategyConfig.Builder()
                        .addTablePrefix(StringUtils.split(conf.get("tablePrefix"), ","))
                        .addInclude(StringUtils.split(conf.get("tables"), ","))
                        .build()
                        .entityBuilder()
                        .enableLombok()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.no_change)
                        .versionColumnName("version")
                        .serviceBuilder()
                        .formatServiceImplFileName("%sServiceImpl")
                        .build()
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .build()
        ).packageInfo(
                new PackageConfig.Builder()
                        .parent(conf.get("packageName"))
                        .controller(conf.get("controller"))
                        .entity(conf.get("entity"))
                        .serviceImpl(conf.get("serviceImpl"))
                        .mapper(conf.get("mapper"))
                        .build()

        ).template(new TemplateConfig.Builder()
                .controller("/plus/controller")
                .service(null, "/plus/serviceImpl")
                .mapper("/plus/mapper.java")
                .mapperXml(null)
                .build()
        ).injection(new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, stringObjectMap) -> {

                    String objectName = tableInfo.getEntityName().substring(0, 1).toLowerCase() + tableInfo.getEntityName().substring(1);
                    Map<String, String> cfgMap = new HashMap();
                    cfgMap.put("pagePath", finalPagePath);
                    cfgMap.put("entityName", objectName);
                    cfgMap.put("titleName", StringUtils.isBlank(tableInfo.getComment()) ? objectName : tableInfo.getComment().trim());
                    stringObjectMap.put("cfg", cfgMap);

                    String filePath = projectRoot + "/generator/pages" + conf.get("pagePath") + "/" + objectName + "/edit.btl";
                    buildFile("/layui/edit.btl", new File(filePath), tableInfo, stringObjectMap,cfgMap);

                    filePath = projectRoot + "/generator/pages" + conf.get("pagePath") + "/" + objectName + "/list.btl";
                    buildFile("/layui/list.btl", new File(filePath), tableInfo, stringObjectMap,cfgMap);

                    String packageName = conf.get("packageName") + "." + conf.get("xml");
                    packageName = packageName.replaceAll("\\.", "/");
                    filePath = projectRoot + "/generator/mapper/" + packageName + "/" + tableInfo.getEntityName() + "Mapper.xml";
                    buildFile("/plus/mapper.xml.btl", new File(filePath), tableInfo, stringObjectMap,cfgMap);

                })

                .build()
        );

        mpg.execute(new BeetlTemplateEngine());

    }

    public static void buildFile(String template, File file, TableInfo tableInfo, Map<String, Object> stringObjectMap,Map<String, String> cfgMap) {
        try {
            String templateString = readFile(template);
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate(templateString);
            t.binding(stringObjectMap);
//            t.binding("cfg", cfgMap);
//            t.binding("table", tableInfo);
            String value = t.render();
            FileUtils.write(file, value, "UTF-8", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String resourceFile) throws IOException {
        InputStream is = MysqlGenerator.class.getResourceAsStream(resourceFile);
        byte[] byteArr = new byte[is.available()];
        is.read(byteArr);
        String string = new String(byteArr);
        return string;
    }

}


