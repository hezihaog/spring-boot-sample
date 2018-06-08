//package com.feon.springboot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringMongoConfig {
//    /**
//     * 设置MongoDB映射转换器属性（用于MongoDB入库时，拔掉额外添加的_class字段）
//     * @param factory MongoDB工厂
//     * @param context MongoDB映射上下文
//     * @return 返回MongoDB映射转换器
//     */
//    @Bean
//    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context) {
//        MappingMongoConverter mappingConverter = new MappingMongoConverter(new DefaultDbRefResolver(factory), context);
//        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null)); //Don't save _class to mongo
//        return mappingConverter;
//    }
//}
