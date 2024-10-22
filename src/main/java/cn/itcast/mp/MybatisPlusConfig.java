//package cn.itcast.mp;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@MapperScan("cn.itcast.mp.mapper") //设置mapper接口的扫描包
//public class MybatisPlusConfig {
//
//
////    @Bean //配置分页插件
////    public PaginationInterceptor paginationInterceptor(){
////        return new PaginationInterceptor();
////    }
//
////    @Bean //Oracle的序列生成器
////    public OracleKeyGenerator oracleKeyGenerator(){
////        return new OracleKeyGenerator();
////    }
//
////    @Bean //注入自定义的拦截器（插件）
////    public MyInterceptor myInterceptor(){
////        return new MyInterceptor();
////    }
//
////    @Bean //SQL分析插件
////    public SqlExplainInterceptor sqlExplainInterceptor(){
////
////        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
////
////        List<ISqlParser> list = new ArrayList<>();
////        list.add(new BlockAttackSqlParser()); //全表更新、删除的阻断器
////
////        sqlExplainInterceptor.setSqlParserList(list);
////
////        return sqlExplainInterceptor;
////    }
//
//    /**
//     * 注入自定义的SQL注入器
//     * @return
//     */
////    @Bean
////    public MySqlInjector mySqlInjector(){
////        return new MySqlInjector();
////    }
//
//}
