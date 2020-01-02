package com.example.demo;

import com.example.demo.interfaces.IMessageService;
import com.example.demo.market.Apple;
import com.example.demo.market.Milk;
import com.example.demo.market.SuperMarket;
import com.example.demo.services.MessagePrinterService;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class DemoApplication {

	@Bean
	IMessageService mockMessageService(){
		return new IMessageService(){
		
			@Override
			public String getMessage() {
				return "Hello World!";
			}
		};
	}
	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class)){
			MessagePrinterService printer = context.getBean(MessagePrinterService.class);
			printer.printMessage();
		}

		// Bean Method 1
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory beanFactory = bindViaCode(beanRegistry);
		SuperMarket superMarket = beanFactory.getBean(SuperMarket.class);
		System.out.println(superMarket);

		// Bean Method 2
		// try(AnnotationConfigApplicationContext beanFactory2 = new AnnotationConfigApplicationContext(DemoApplication.class)){
		// 	com.example.demo.market2.SuperMarket superMarket2 = beanFactory2.getBean(com.example.demo.market2.SuperMarket.class);
		// 	System.out.println(superMarket2);
		// }

		SpringApplication.run(DemoApplication.class, args);
	}

	private static BeanFactory bindViaCode(BeanDefinitionRegistry beanRegistry){
		AbstractBeanDefinition fruit = new RootBeanDefinition(Apple.class);
		AbstractBeanDefinition drink = new RootBeanDefinition(Milk.class);
		AbstractBeanDefinition superMarket = new RootBeanDefinition(SuperMarket.class);

		beanRegistry.registerBeanDefinition("fruit", fruit);
		beanRegistry.registerBeanDefinition("drink", drink);
		beanRegistry.registerBeanDefinition("superMarket", superMarket);

		ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
		argumentValues.addIndexedArgumentValue(0, drink);
		argumentValues.addIndexedArgumentValue(1, fruit);
		superMarket.setConstructorArgumentValues(argumentValues);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("fruit", fruit);
		propertyValues.addPropertyValue("drink", drink);
		superMarket.setPropertyValues(propertyValues);

		return (BeanFactory) beanRegistry;
	}
}
