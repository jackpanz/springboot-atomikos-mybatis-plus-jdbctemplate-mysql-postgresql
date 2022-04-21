package generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus-generator v3.5.0
 */
public class MybatisPlusGenerator {

    public static final Map<String, String> DEF_CONF = new HashMap() {
        {

            //jdbc
            put("jdbc.driver", "org.postgresql.Driver");
            put("jdbc.url", "jdbc:postgresql://127.0.0.1:5432/db1");
            put("jdbc.user", "postgres");
            put("jdbc.pass", "123456");

            //config
            put("packageName", "com");
            put("superEntityClass", "");
            put("pagePath", "");

            put("controller", "controller");
            put("entity", "entity");
            put("mapper", "mapper");
            put("xml", "mapper");
            put("serviceImpl", "service.impl");

            put("mapperFileName", "%sMapper");
            put("xmlFileName", "%sMapper");

            put("controllerTemp", "/plus/controller");
            put("serviceImplTemp", "/plus/serviceImpl");
            put("mapperTemp", "/plus/mapper.java");
            put("entityTemp", "/templates/entity.java");
            put("xmlTemp", "/plus/mapper.xml.btl");

            put("editTemp", "/layui/edit.btl");
            put("listTemp", "/layui/list.btl");

            //tables
            put("tables", "");

            put("tablePrefix", "");

        }
    };

    public static MySqlTypeConvert getMySqlTypeConvert() {
        return new MySqlTypeConvert() {
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
    }

    public static PostgreSqlTypeConvert getPostgreSqlTypeConvert() {
        return new PostgreSqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                if (fieldType.toLowerCase().contains("timestamp")) {
                    return DbColumnType.DATE;
                } else if (fieldType.toLowerCase().contains("date")) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        };
    }

    public static void generator(Map<String, String> conf, ITypeConvert typeConvert) {

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
                new DataSourceConfig.Builder(conf.get("jdbc.url"), conf.get("jdbc.user"), conf.get("jdbc.pass"))
                        .typeConvert(typeConvert)
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
                        .idType(IdType.INPUT)
                        .serviceBuilder()
                        .formatServiceImplFileName("%sServiceImpl")
                        .build()
                        .mapperBuilder().formatMapperFileName(conf.get("mapperFileName"))
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
                        .controller(conf.get("controllerTemp"))
//                .service(null, conf.get("serviceImplTemp"))
                        .service(null)
                        .serviceImpl(conf.get("serviceImplTemp"))
                        .mapper(conf.get("mapperTemp"))
                        .xml(null)
                        .entity(conf.get("entityTemp"))
                        .build()
        ).injection(new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, stringObjectMap) -> {

                    String objectName = tableInfo.getEntityName().substring(0, 1).toLowerCase() + tableInfo.getEntityName().substring(1);
                    String sequence_name = "seq_" + tableInfo.getName().replaceFirst(conf.get("tablePrefix"), "");

                    Map<String, String> cfgMap = new HashMap();
                    cfgMap.put("pagePath", finalPagePath);
                    cfgMap.put("entityName", objectName);
                    cfgMap.put("sequence_name", sequence_name);
                    cfgMap.put("titleName", StringUtils.isBlank(tableInfo.getComment()) ? objectName : tableInfo.getComment().trim());
                    stringObjectMap.put("cfg", cfgMap);

                    String filePath = projectRoot + "/generator/pages" + conf.get("pagePath") + "/" + objectName + "/edit.btl";
                    buildFile(conf.get("editTemp"), new File(filePath), stringObjectMap);

                    filePath = projectRoot + "/generator/pages" + conf.get("pagePath") + "/" + objectName + "/list.btl";
                    buildFile(conf.get("listTemp"), new File(filePath), stringObjectMap);

                    String packageName = conf.get("packageName") + "." + conf.get("xml");
                    packageName = packageName.replaceAll("\\.", "/");


                    String xmlFileName = conf.get("xmlFileName");
                    String xmlName = String.format(xmlFileName, tableInfo.getEntityName());

                    filePath = projectRoot + "/generator/mapper/" + packageName + "/" + xmlName + "Mapper.xml";
                    buildFile(conf.get("xmlTemp"), new File(filePath), stringObjectMap);

                })
                .build()
        );

        mpg.execute(new BeetlTemplateEngine());

    }

    public static void buildFile(String template, File file, Map<String, Object> stringObjectMap) {
        try {
            String templateString = readFile(template);
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate(templateString);
            t.binding(stringObjectMap);
            String value = t.render();
            FileUtils.write(file, value, "UTF-8", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String resourceFile) throws IOException {
        InputStream is = MybatisPlusGenerator.class.getResourceAsStream(resourceFile);
        byte[] byteArr = new byte[is.available()];
        is.read(byteArr);
        String string = new String(byteArr);
        return string;
    }
}
