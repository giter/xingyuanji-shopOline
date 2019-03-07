package com.shopoline.xingyuanji.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ������������ʾ
 * </p>
 */

public class MpGenenator {

    final static String  dirPath = "D://xingyuanji//src//main//java";
    /**
     * <p>
     * MySQL ������ʾ
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // ѡ�� freemarker ���棬Ĭ�� Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // ȫ������
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(dirPath);
        gc.setAuthor("wuty");
        gc.setFileOverride(true); //�Ƿ񸲸�
        gc.setActiveRecord(true);// ����ҪActiveRecord���Ե����Ϊfalse
        gc.setEnableCache(false);// XML ��������
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        // �Զ����ļ�������ע�� %s ���Զ�����ʵ�����ԣ�
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sMapper");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);



        // ����Դ����
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // �Զ������ݿ���ֶ�����ת������ѡ��
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("ת�����ͣ�" + fieldType);
                // ע�⣡��processTypeConvert ����Ĭ������ת�������������Ҫ��Ч�����Զ��巵�ء�������ֱ�ӷ��ء�
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://47.98.44.227:3306/wxp?serverTimezone=Hongkong&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&useSSL=true");
        mpg.setDataSource(dsc);

        // ��������
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// ȫ�ִ�д���� ORACLE ע��
        //strategy.setTablePrefix(new String[] { "t_" });// �˴������޸�Ϊ���ı�ǰ׺
        strategy.setNaming(NamingStrategy.underline_to_camel);// �������ɲ���
        strategy.setInclude(new String[] { "address","buyer","permission","prize","prize_code","prize_log","role","role_permission","user","user_role"});
        // ��Ҫ���ɵı�
        // strategy.setExclude(new String[]{"test"}); // �ų����ɵı�
        // �Զ���ʵ�常��
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // �Զ���ʵ�壬�����ֶ�
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // �Զ��� mapper ����
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // �Զ��� service ����
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // �Զ��� service ʵ���ุ��
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // �Զ��� controller ����
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // ��ʵ�塿�Ƿ������ֶγ�����Ĭ�� false��
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // ��ʵ�塿�Ƿ�Ϊ������ģ�ͣ�Ĭ�� false��
        // public User setName(String name) {this.name = name; return this;}
        strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // ������
        PackageConfig pc = new PackageConfig();
        pc.setParent("com");
        pc.setParent("com.shopoline");
        pc.setModuleName("xingyuanji");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service.db2");
        mpg.setPackageInfo(pc);

        // ע���Զ������ã������� VM ��ʹ�� cfg.abc �����ޡ�
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // �Զ��� xxList.jsp ����
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
/*        focList.add(new FileOutConfig("/template/list.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // �Զ��������ļ�����
                return "D://my_" + tableInfo.getEntityName() + ".jsp";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/

        // ���� xml ����Ŀ¼��ʾ
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "D://xingyuanji/src/main/resources/mybatis/demo/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        // �ر�Ĭ�� xml ���ɣ��������� �� ��Ŀ¼
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        // �Զ���ģ�����ã����� copy Դ�� mybatis-plus/src/main/resources/templates ���������޸ģ�
        // �����Լ���Ŀ�� src/main/resources/templates Ŀ¼��, Ĭ������һ�¿��Բ����ã�Ҳ�����Զ���ģ������
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // �����κ�һ��ģ��������� �� OR Null �������ɸ�ģ�顣
        // mpg.setTemplate(tc);
        // ִ������
        mpg.execute();
        // ��ӡע�����á����ޡ�
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }



}

