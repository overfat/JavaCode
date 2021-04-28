package com.hfx.ui;

import com.hfx.dao.IAccountDao;
import com.hfx.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.rowset.spi.XmlReader;

/**
 * 模拟客户端
 */
public class Client {
    /**
     * 获取Spring的Ioc核心容器，并根据id获取bean对象
     *
     * ApplicationContext的三个常用的实现类：
     *      ClassPathXmlApplicationContext: 它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了
     *      FileSystemXmlApplicationContext: 它可以加载磁盘任意路径下的配置文件(必须有访问权限)
     *
     *      AnnotationConfigApplicationContext：用于读取注解创建容器的
     * 核心容器的两个接口引发的问题:
     * ApplicationContext   单例对象适用
     *      它创建核心容器的方式为立即加载的方式，也就是说，一读取完配置文件，马上就创建配置文件中配置的文件
     * BeanFactory      多例对象适用
     *      它创建核心容器的方式为延迟加载，什么时候根据id获取容器对象了，什么时候才真正的创建对象
     *
     */
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        IAccountDao dao = ac.getBean("accountDao",IAccountDao.class);
        System.out.println(accountService);
        System.out.println(dao);

        // 还可以使用BeanFactory来完成操作
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        IAccountService as = (IAccountService) factory.getBean("accountService");
//        System.out.println(as);
    }
}
