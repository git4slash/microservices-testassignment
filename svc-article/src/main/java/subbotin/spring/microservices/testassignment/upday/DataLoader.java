package subbotin.spring.microservices.testassignment.upday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import subbotin.spring.microservices.testassignment.upday.article.Article;
import subbotin.spring.microservices.testassignment.upday.article.ArticleRepository;

import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {
    final
    ArticleRepository articleRepository;

    @Autowired
    public DataLoader(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Add some data to repository. Use for demo purposes.
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Stream.of(
                new Article("Header 1", "Description 1", "Some text", 111L),
                new Article("Header 2", "Description 2", "Another text", 2L ),
                new Article("Header 3", "Description 3", "Just text", 111L))
                .forEach(articleRepository::save);
    }
}
