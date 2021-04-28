package com.hfx.utils;

import java.sql.SQLException;

public class TransactionManager {
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils){
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit()  {
        try{
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void rollback(){
        try{
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void release(){
        try{
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
