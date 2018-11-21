package subbotin.spring.microservices.testassignment.upday.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository for Article data implemented usidng Spring Data JPA
 *
 * @author Oleksandr Subbotin
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    /**
     * Find articles with specified author id.
     *
     * @param authorId id of author ot search
     * @return Iterable containing articles with given author id.
     */
    Iterable<Article> findArticlesByAuthorId(Long authorId);

    /**
     * Find articles from given period.
     *
     * @param startDate start date of period
     * @param endDate end date of period
     * @return Iterable with articles from given period
     */
    @Query("select a from Article a where a.publishDate >= :startDate and a.publishDate <= :endDate")
    Iterable<Article> findArticlesByPublishDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
