package com.hfx.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类
 */

public class ConnectionUtils {
    private ThreadLocal<Connection> t1 = new ThreadLocal<>();

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     */

    public Connection getThreadConnection(){
        try{
            Connection conn = t1.get();
            if(conn == null){
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void removeConnection(){
        t1.remove();
    }

}
