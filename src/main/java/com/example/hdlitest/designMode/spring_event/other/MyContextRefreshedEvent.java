package com.example.hdlitest.designMode.spring_event.other;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 在IOC的容器的启动过程，当所有的bean都已经处理完成之后，spring ioc容器会有一个发布事件的动作
 * ContextRefreshedEvent的事件发布 在AbstractApplicationContext.finishRefresh()的方法中
 * 当ioc容器加载处理完相应的bean之后，也给我们提供了一个机会（先有InitializingBean，后有ApplicationListener<ContextRefreshedEvent>）
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 15:48
 */
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //系统会存在两个容器，一个是root application context ,
        // 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）
        //这种情况下，就会造成onApplicationEvent方法被执行两次。
        // 为了避免上面提到的问题，我们可以只在root application context初始化完成后调用逻辑代码，
        // 其他的容器的初始化完成，则不做任何处理
        if(event.getApplicationContext().getParent() == null){
            //TODO 自己的业务代码
        }

    }
}
