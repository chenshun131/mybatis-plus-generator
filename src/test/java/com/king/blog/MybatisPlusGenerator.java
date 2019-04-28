package com.king.blog;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.king.mybatisplus.generator.AutoGenerator;
import com.king.mybatisplus.generator.config.*;
import com.king.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.king.mybatisplus.generator.config.rules.DbColumnType;
import com.king.mybatisplus.generator.config.rules.IColumnType;
import com.king.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

/**
 * User: chenshun131 <p />
 * Time: 19/1/20 21:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MybatisPlusGenerator {

    /** 基本包名 */
    private static final String basePackage = "com.king.blog";

    /** 作者 */
    private static final String authorName = "chenshun131";

    /** 要生成的表名 */
    private static final String[] tables = {"recruit"};

    /** table前缀 */
    private static final String prefix = "";

    /** 数据库配置四要素 */
    private static final String driverName = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://ci-server:3306/blog2?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false";

    private static final String username = "root";

    private static final String password = "123456";

    public static void main(String[] args) {
        AutoGenerator gen = new AutoGenerator();

        // 数据库配置
        gen.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        if (fieldType.toLowerCase().contains("date") || fieldType.toLowerCase().contains("time")) {
                            return DbColumnType.DATE;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                }));

        // 全局配置
        String baseProjectPath = new File("src").getAbsolutePath();
        gen.setGlobalConfig(new GlobalConfig()
                .setOutputDir(baseProjectPath + "/test/java") // 输出目录
                .setFileOverride(true) // 是否覆盖文件
                .setActiveRecord(true) // 开启 activeRecord 模式
                .setEnableCache(false) // XML 二级缓存
                .setSwagger2(false) // 开启 swagger2 模式
                .setBaseResultMap(true) // XML ResultMap
                .setBaseColumnList(true) // XML columList
                .setOpen(false) // 生成后打开文件夹
                .setAuthor(authorName)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setIdType(IdType.ID_WORKER_STR));
        System.out.println("输出目录 : " + gen.getGlobalConfig().getOutputDir());

        // 策略配置
        gen.setStrategy(new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(tables) // 需要生成的表
                        .setRestControllerStyle(true)
                        .setLogicDeleteFieldName("del_flag") // 设置逻辑删除字段
                        .entityTableFieldAnnotationEnable(true) // 是否生成实体时，生成字段注解
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        //.setTableFillList(tableFillList)
                        // 自定义 mapper 父类 默认BaseMapper
                        //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
                        // 自定义 service 父类 默认IService
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类 默认ServiceImpl
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        // .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        );

        // 包配置
        gen.setPackageInfo(new PackageConfig()
                //.setModuleName("User")
                .setParent(basePackage) // 自定义包路径
                .setController("controller") // 这里是控制器包名，默认 web
                .setEntity("model.domain")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper")
        );

        // 模板配置
        gen.setTemplate(new TemplateConfig()
                .setEntity(ConstVal.TEMPLATE_ENTITY_JAVA)
                .setEntityKt(ConstVal.TEMPLATE_ENTITY_KT)
                .setService(ConstVal.TEMPLATE_SERVICE)
                .setServiceImpl(ConstVal.TEMPLATE_SERVICE_IMPL)
                .setMapper(ConstVal.TEMPLATE_MAPPER)
                .setXml(ConstVal.TEMPLATE_XML)
                .setController(ConstVal.TEMPLATE_CONTROLLER));

        // 执行生成
        gen.execute();
    }

}
