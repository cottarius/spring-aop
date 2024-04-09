package ru.cotarius.springcourse.springaop;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.cotarius.springcourse.springaop.interfaces.IntegerToListInterface;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);

		IntegerToListInterface injectIntegersToList = context.getBean(IntegerToListInterface.class);
		injectIntegersToList.addIntegers();
		System.out.println("Размер рандомного массива интов: " + injectIntegersToList.getSize());

		context.close();

	}

}
