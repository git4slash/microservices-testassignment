package subbotin.spring.microservices.testassignment.upday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import subbotin.spring.microservices.testassignment.upday.keyword.Keyword;
import subbotin.spring.microservices.testassignment.upday.keyword.KeywordRepository;

import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {
    final KeywordRepository keywordRepository;

    @Autowired
    public DataLoader(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }
    

    /**
     * Add some data to repository. Use for demo purposes.
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Stream.of(
                new Keyword("Keyword1"),
                new Keyword("Keyword2"),
                new Keyword("Keyword3"))
                .forEach(keywordRepository::save);
    }
}
