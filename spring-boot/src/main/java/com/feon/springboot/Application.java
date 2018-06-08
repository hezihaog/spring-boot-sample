package com.feon.springboot;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableCaching //打开SpringBoot缓存功能
@EnableTransactionManagement
public class Application {
    /**
     * 声明FastJson转换器
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //配置FastJson
		/*FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(config);*/
        return new HttpMessageConverters(converter);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public RedisTemplate<Object, Object> jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setDefaultSerializer(new GenericFastJsonRedisSerializer());
//        return template;
//    }

    /**
     * 声明一个配置类，获取server开头的配置属性
     * @return
     */
//    @Bean
//    @ConfigurationProperties("server")
//    public ServerConfig serverConfig() {
//        return new ServerConfig();
//    }
//
////  @Profile({"dev", "test"}) //profile配置，开发和测试环境有效
////	@Profile("!prod") //profile配置，非生产环境有效
//    @Bean
//    @ConditionalOnBean(ServerConfig.class) //存在bean，配置生效（不加参数代表方法返回值的bean）
//    @ConditionalOnMissingBean(IStudentDAO.class) //不存在bean，配置生效（不加参数代表方法返回值的bean，用于存在性校验）
//    @ConditionalOnClass(IStudentDAO.class) //classpath中存在指定的class，配置生效
//    //classpath中不存在指定的class，配置生效
//    @ConditionalOnMissingClass({"com.feon.springboot.dao.IUserDAO", "com.feon.springboot.service.IUserService"})
//    //配置中存在指定属性的值，配置生效
//    //（如果没有指定havingValue，只要属性不为false，配置都能生效；matchIfMissing为true指配置中不存在该属性，配置都能生效）
//    @ConditionalOnProperty(name = "server.port", havingValue = "80", matchIfMissing = true)
//    @ConditionalOnExpression("'${server.port}' == '80'") //满足SpEL表达式，配置生效
//    @ConditionalOnJava(range = ConditionalOnJava.Range.EQUAL_OR_NEWER, value = JavaVersion.EIGHT) //满足Java环境，配置生效
//    @Conditional(MyCondition.class) //自定义条件，创建一个实现了Condition接口的类，matches方法返回true时配置生效
//    public Student student(ServerConfig serverConfig) {
//        System.out.println("构造学生对象，依赖系统配置：" + serverConfig); //依赖注入
//        return new Student();
//    }

    /**
     * swagger配置
     * @return
     */
//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
////                .enable(false) //禁用swagger（用于生产环境）
//                .groupName("REST接口")
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/") //API测试请求地址
//                .select()
////                .paths(PathSelectors.regex("/.*")) //过滤的接口
////                .paths(PathSelectors.none())// 屏蔽接口描述（用于生产环境）
//                .paths(or(regex("/rest"), regex("/rest/.*")))//过滤的接口
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //只允许配置了ApiOperation注解的方法
//                .build()
//                .apiInfo(new ApiInfo("飞鸿项目", "项目描述", "1.0.0", "www.feon.com",
//                         new Contact("飞鸿", "http://www.feon.com/feihong", "418840454@qq.com"),
//                        "证书", "证书url", Collections.emptyList()));
//    }

//    @Bean
//    public Docket testApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
////                .enable(false) //禁用swagger（用于生产环境）
//                .groupName("测试接口")
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/") //API测试请求地址
//                .select()
////                .paths(PathSelectors.regex("/.*")) //过滤的接口
////                .paths(PathSelectors.none())// 屏蔽接口描述（用于生产环境）
//                .paths(or(regex("/test"), regex("/test/.*")))//过滤的接口
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //只允许配置了ApiOperation注解的方法
//                .build()
//                .apiInfo(new ApiInfo("飞鸿项目", "项目描述", "1.0.0", "www.feon.com",
//                         new Contact("飞鸿", "http://www.feon.com/feihong", "418840454@qq.com"),
//                        "证书", "证书url", Collections.emptyList()));
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
