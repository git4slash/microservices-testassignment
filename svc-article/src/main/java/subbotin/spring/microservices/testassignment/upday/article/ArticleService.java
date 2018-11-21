package subbotin.spring.microservices.testassignment.upday.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import subbotin.spring.microservices.testassignment.upday.exceptions.ArticleNotFoundException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ArticleService {
    private Logger logger = Logger.getLogger(ArticleService.class.getName());

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        logger.info("ArticleService created");
    }

    public Optional<String> aboutService() {
        logger.info("aboutService invoked");
        return Optional.of("Article service with basic CRUD (create, read, update, delete) functions for working with articles.");
    }

    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public Article create(Article article) {
        logger.info("Create method invoked with. Given article was: " + article);
        return articleRepository.save(article);
    }

    public void update(Article article, long articleId) {
        logger.info("update invoked with articleId:" + articleId + " and info to update: " + article);
        articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
        article.setId(articleId);
        articleRepository.save(article);
    }

    public Iterable<Article> findByAuthor(long authorId) {
        logger.info("findByAuthor invoked with authorId: " + authorId);
        return articleRepository.findArticlesByAuthorId(authorId);
    }

    public Iterable<Article> findAllInPeriod(LocalDate startDate, LocalDate endDate) {
        logger.info("findAllInPeriod involved. Start Date: " + startDate + " end Date: " + endDate);
        return articleRepository.findArticlesByPublishDateBetween(startDate, endDate);
    }

    public void deleteById(long articleId) {
        logger.info("deleteById invoked for articleId:" + articleId);
        articleRepository.deleteById(articleId);
    }

    public Iterable<Article> findAll() {
        logger.info("findAll invoked");
        return articleRepository.findAll();
    }
}
