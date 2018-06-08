package com.feon.springboot.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGeneratorCode {

	// XXX 数据库设置
	private static String dbURL = "jdbc:mysql://localhost:3306/school?characterEncoding=utf8";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "hezihao";

	public static void main(String[] args) {
		AutoGenerator generator = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		// 生成文件存放目录
		gc.setOutputDir("d:\\generatorCode\\");
		gc.setFileOverride(true);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setAuthor("蒙飞鸿");
		generator.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setUrl(dbURL);
		dsc.setDriverName(driverName);
		dsc.setUsername(userName);
		dsc.setPassword(password);
		/*
		dsc.setTypeConvert(new MySqlTypeConvert(){
			// 自定义数据库表字段类型转换【可选】
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				return super.processTypeConvert(fieldType);
			}
		});
		*/
		generator.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setTablePrefix(new String[]{"cec_","op_"});// XXX 此处可以修改为您的表前缀
		// strategy.setInclude(new String[] { "user" }); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表

//		strategy.setTablePrefix(new String[]{"b_"});// XXX 此处可以修改为您的表前缀
		strategy.setInclude(new String[] {"book"}); // 需要生成的表
		strategy.setNaming(NamingStrategy.underline_to_camel); // 表名生成策略
		generator.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(""); // XXX
		/*pc.setEntity("com.ejoyst.common.model.entity");
		pc.setMapper("com.ejoyst.course.restservice.course.mapper");
		pc.setXml("resources.mapper.course");
		pc.setService("com.ejoyst.course.restservice.course.service");
		pc.setServiceImpl("com.ejoyst.course.restservice.course.service.impl");*/
		pc.setEntity("com.feon.model");
		pc.setMapper("com.feon.model");
		pc.setXml("com.feon.model");
		pc.setService("com.feon.service");
		pc.setServiceImpl("com.feon.service.impl");

//		pc.setController("com.do1.gw.api.service.impl");
		pc.setController("com.feon.controller");
		generator.setPackageInfo(pc);

		// XXX 自定义模板，放在类路径下
		// 执行生成
		generator.execute();
	}
}
