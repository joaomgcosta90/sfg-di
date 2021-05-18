package guru.springframework.sfgdi.services;

public class I18nPortugueseGreeting implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Olá Mundo - PT";
    }
}
