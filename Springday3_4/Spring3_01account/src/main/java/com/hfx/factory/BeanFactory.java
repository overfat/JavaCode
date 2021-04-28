package com.hfx.factory;

import com.hfx.service.IAccountService;
import com.hfx.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;
    public void setTxManager(TransactionManager txManager){
        this.txManager = txManager;
    }

    public final void setAccountService(IAccountService accountService){
        this.accountService = accountService;
    }

    /**
     * 获取Service代理对象
     */
    public IAccountService getAccountService(){
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if("test".equals(method.getName())){
                            return method.invoke(accountService,args);
                        }
                        Object rtValue = null;
                        try{
                            //1 开启事务
                            txManager.beginTransaction();
                            //2 执行操作
                            rtValue = method.invoke(accountService, args);
                            //3 提交事务
                            txManager.commit();
                            //4 返回结果
                            return rtValue;
                        }catch (Exception e){
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            txManager.release();
                        }
                    }
                });
    }
}
