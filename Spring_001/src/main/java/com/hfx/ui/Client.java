package com.hfx.ui;

import com.hfx.dao.IAccountDao;
import com.hfx.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */

public class Client {
    /**
     * Spring获取核心容器的方法
     * 1. ApplicationContext的三个常见实现类
     *     ClassPathXmlApplicationContext:它可以加载类路径下的配置文件，要求配置文件一定在类路径下，不在加载不了（常用）
     *     FileSystemXmlApplicationContext:它可以加载磁盘目录下的任何配置文件
     *
     *     AnnotationConfigApplicationContext:用于读取注解创建容器
     *
     * 核心容器两个接口引发的问题：
     * 1 ApplicationContext     单例对象    立即创建
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     *2. BeanFactory:   多例对象    延迟加载
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     */
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
//        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(accountService);

//        System.out.println(accountDao);

        // 使用BeanFactory创建对象
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        IAccountService service = (IAccountService) factory.getBean("accountService");
//        System.out.println(service);
    }
}
