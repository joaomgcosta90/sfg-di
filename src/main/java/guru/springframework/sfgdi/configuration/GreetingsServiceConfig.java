package guru.springframework.sfgdi.configuration;

import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repository.EnglishGreetingRepository;
import guru.springframework.sfgdi.repository.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@EnableConfigurationProperties(SfgConstructorConfiguration.class)
@Configuration
public class GreetingsServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(SfgConstructorConfiguration sfgConstructorConfiguration){
//    FakeDataSource fakeDataSource(@Value("${guru.username}") String userName,@Value("${guru.password}") String password ,@Value("${guru.jdbcurl}") String jdbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUserName(sfgConstructorConfiguration.getUserName());
        fakeDataSource.setPassword(sfgConstructorConfiguration.getPassword());
        fakeDataSource.setJdbcurl(sfgConstructorConfiguration.getJdbcurl());
        return fakeDataSource;
    }

    @Profile({"PT", "default"})
    @Bean("i18nService")
    I18nPortugueseGreeting i18nPortugueseGreeting(){
        return new I18nPortugueseGreeting();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }

    @Bean
    SetterGreetingService setterGreetingService(){
        return new SetterGreetingService();
    }

    @Bean
    PropertyGreetingService propertyGreetingService(){
        return new PropertyGreetingService();
    }
}
