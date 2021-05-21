package guru.springframework.sfgdi;

import guru.springframework.sfgdi.configuration.SfgConfiguration;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:datasource.properties")
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		MyController myController = (MyController) ctx.getBean("myController");


		System.out.println("--------- Active Profile");
		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		System.out.println("--------- Primary bean");
		System.out.println(myController.sayHello());

		System.out.println("--------- Property Injected Controller");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreetings());

		System.out.println("--------- Setter Injected Controller");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreetings());

		System.out.println("--------- Constructor Injected Controller");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreetings());

		System.out.println("--------- Fake Data Source");
		FakeDataSource fakeDataSource = (FakeDataSource) ctx.getBean("fakeDataSource");
		System.out.println(fakeDataSource.getUserName());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getJdbcurl());


		System.out.println("--------- Config Props Bean");
		SfgConfiguration sfgConfiguration = (SfgConfiguration) ctx.getBean("sfgConfiguration");
		System.out.println(sfgConfiguration.getUserName());
		System.out.println(sfgConfiguration.getPassword());
		System.out.println(sfgConfiguration.getJdbcurl());



	}

}
