package subbotin.spring.microservices.testassignment.upday.keyword;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    Optional<Keyword> findKeywordByNameEquals(String name);

    Iterable<Keyword> findKeywordsByArticleIDsContains(Long articleId);
}
