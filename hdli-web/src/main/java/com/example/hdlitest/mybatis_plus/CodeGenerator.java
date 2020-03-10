package com.example.hdlitest.mybatis_plus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR 李会东
 * @version 1.0
 * @date 2019-8-26 16:56
 */
public class CodeGenerator {

    //作者
    private static final String AUTHOR = "lihuidong";

    //数据库
    private static final String URL = "jdbc:mysql://localhost:3306/shriotest?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "123456";

    private static final String table_prefix = "pc_";

    //生成在该目录下
    private static final String package_name = "com.example.hdlitest";
    //模块名
    private static final String module_name = "mybatis_plus";



    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 选择 freemarker 引擎，默认 Veloctiy
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        /* -----------------------------全局设置----------------------------*/
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        String projectPath = projectPath1+"/micro-service/product-svc";
        //// 输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        //AR模式  不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        gc.setSwagger2(true);
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);

        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
//        gc.setEntityName(entityName);
        gc.setMapperName("%sMapper");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        /* -----------------------------数据源配置----------------------------*/
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        /* -----------------------------包设置----------------------------*/
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module_name);
        pc.setParent(package_name);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" /*+ pc.getModuleName()*/
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        /* -----------------------------策略配置----------------------------*/
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.jxhmall.product.entity.BaseEntity");
//        // 自定义实体，公共字段
//        strategy.setSuperEntityColumns("create_time","update_time");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");

        // 需要生成的表   不设置生成全部的表信息
//        strategy.setInclude("pc_order_repertory");
        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});


        strategy.setControllerMappingHyphenStyle(true);
        // 此处可以修改为您的表前缀
//        strategy.setTablePrefix(table_prefix);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
