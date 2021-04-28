package com.hfx.service.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.service.IAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户业务层实现类
 *
 * XMl配置
 * <bean id="accountService" class="" scope="" init-method="" destory-method="">
 *     <property name="" value="" | ref=""></>
 * </bean>
 *
 * 注解配置：
 * 用于创建对象：
 *     作用和在XML文件中编写一个<bean>标签实现的功能是一样的
 *     Component：
 *         用于把当前类对象存入spring容器中
 *         属性:
 *             value: 用于指定bean的id，当我们不写时默认是当前的类名，不过首字母需要改成小写
 *     Controller:一般用在表现层
 *     Service:一般用在业务层
 *     Repository:一般用在持久层
 *     以上三个注解的作用和Component一摸一样。
 *
 * 用于注入数据的
 *     他们的作用就喝在xml配置文件中的bean标签中写一个<property></property>标签作用是一样的
 *     Autowired:
 *         作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *              如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *              如果Ioc容器中有多个类型匹配时：
 *         出现位置：可以是变量上，也可以是方法上
 *         细节：在使用注解注入时，set方法就不是必须的了
 *    Qualifier:
 *         作用：在按照类中注入的基础之上再按照名称注入。它再给类成员注入时不能单独使用，但是再给方法参数注入时可以
 *         属性：
 *             value:用于指定bean的id
 *    Resource:
 *         作用：直接按照bean的id注入。他可以独立使用
 *         属性：
 *             name:用于指定bean 的id
 *    以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现
 *    另外：集合类型的注入只能通过XMl来实现。
 *
 *    Value:
 *         作用：用于基本类型和String类型的注入
 *         属性：
 *             value：用于指定数据的值。他可以使用spring中SpEL（也就是Spring中的EL表达式）
 *
 * 用于改变作用范围的：
 *     作用就和bean标签中的scope属性是一样的
 *     Scope:
 *         作用：用于指定bean的范围
 *         属性：
 *             value:指定范围的取值。常用取值：singleton prototype
 *
 * 和生命周期相关  了解
 *     他们的作用就和在bean标签中使用init-method和destory-method的作用是一样的
 *     PreDestory:
 *          作用：用于指定销毁方法
 *     PostConstruct
 *          作用：用于指定初始化方法
 *
 */

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

//    @Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private IAccountDao accountDao = null;

    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destory(){
        System.out.println("销毁方法执行了");
    }

    public void saveAccount(){
        accountDao.saveAccount();
    }
}
