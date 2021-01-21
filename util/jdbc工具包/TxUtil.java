package cn.tx.demo;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * Administrator
 * 2020/9/22
 */
public class TxUtil {
    private static DruidDataSource ds = null;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    static {
        try{
            ds = new DruidDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
            ds.setUsername("root");
            ds.setPassword("root");
        }catch(Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }
    /*
    获取数据库
     */
    public static Connection getConnection()throws SQLException{
        Connection con = threadLocal.get();
        if(con==null){
           con = getDataSource().getConnection();
            threadLocal.set(con);
        }
        return con;
    }

    /*
    获取数据库连接池
     */
    public static DataSource getDataSource(){
        if(ds==null){
            System.out.println("数据库连接池创建错误");
        }
        return ds;
    }

    /*
    开启事务
     */
    public static void startTransaction(){
        try{
            Connection con = threadLocal.get() ;
            if(con==null){
                    con= getConnection();
                    threadLocal.set(con);
            }
            con.setAutoCommit(false);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /*
    回滚事务
     */
    public static void rollback(){
        try{
            Connection con = threadLocal.get();
            if(con!=null){
                con.rollback();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /*
    提交事务
     */

    public static void commit() {
        try {
            Connection con = threadLocal.get();
            if(con!=null){
                con.commit();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭数据库连接，还给数据库
     */
    public static void close(){
        try{
        Connection con = threadLocal.get();
        if(con!=null) {
            con.close();
        }
            threadLocal.remove();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
