package subbotin.spring.microservices.testassignment.upday.keyword;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

public abstract class KeywordControllerAbstractTest extends RestControllerAbstractTest {

    @Value("${api.home}")
    String apiHomeAddress;

    static final List<Keyword> TEST_ENTITIES_LIST = List.of(
            new Keyword("TestTag"),
            new Keyword("TestTag2"),
            new Keyword("TestTag3")
    );

    @Autowired
    KeywordRepository keywordRepository;

    List<Keyword> testEntitiesSavedInRepoList;

    @Before
    public void setUp() throws Exception {
        testEntitiesSavedInRepoList = TEST_ENTITIES_LIST.stream().map(keywordRepository::save).collect(Collectors.toList());

    }

}
