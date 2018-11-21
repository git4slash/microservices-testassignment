package subbotin.spring.microservices.testassignment.upday.article;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ArticleControllerAbstractTest extends RestControllerAbstractTest {

    @Value("${api.home}")
    String apiHomeAddress;

    static final List<Article> TEST_ARTICLE_LIST = List.of(
            new Article("Header", "Desc", "Text", 99L),
            new Article("Header", "Desc", "Text", 3L),
            new Article("Header", "Desc", "Text", 99L)
    );

    @Autowired
    ArticleRepository articleRepository;

    List<Article> testEntitiesSavedInRepoList;

    @Before
    public void setUp() throws Exception {
        testEntitiesSavedInRepoList = TEST_ARTICLE_LIST.stream().map(articleRepository::save).collect(Collectors.toList());
        mapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

}
