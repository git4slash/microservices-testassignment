package subbotin.spring.microservices.testassignment.upday.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import subbotin.spring.microservices.testassignment.upday.exceptions.ArticleNotFoundException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

/**
 * A REST controller with implementation of basic CRUD (create, read, update, delete) functions for working with articles.
 * Also controller gives extra functionality: find all articles by author articleId and all articles for a period of time.
 *
 * @author Oleksandr Subbotin
 */
@Controller
@RequestMapping("${api.home}")
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Create an instance plugging in the repository of Articles
     *
     * @param articleService
     */
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Returns information about service.
     */
    @GetMapping("/aboutService")
    @ResponseBody
    public Optional<String>  aboutService() {
        return articleService.aboutService();
    }

    /**
     * Returns all articles
     * @return Iterable containing all articles.
     */
    @GetMapping
    @ResponseBody
    public Iterable<Article> findAll() {
        return articleService.findAll();
    }

    /**
     * Fetch the article with the specified article articleId
     *
     * @param articleId The identification number of article to search.
     * @return the article with the given id or Optional#empty() if none found.
     */
    @GetMapping("/{articleId}")
    @ResponseBody
    public Optional<Article> findOne(@PathVariable Long articleId) {
        return articleService.findOne(articleId);
    }

    /**
     * Create and save the new article instance from given article
     *
     * @param article Article to save.
     * @return Id of created article in repository.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long create(@Valid @RequestBody Article article) {
        return articleService.create(article).getId();
    }

    /**
     * Update existing article only if repository contains article with given id.
     * Otherwise throws ArticleNotFoundException
     *
     * @param article Source of the new information to update
     * @param articleId Id of article to update
     * @throws ArticleNotFoundException if given id is incorrect
     */
    @PutMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody Article article, @PathVariable long articleId) {
        articleService.update(article, articleId);
    }

    /**
     * Delete existing article from repository if article with given id exist in repo.
     *
     * @param articleId Id of the article to delete
     */
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long articleId) {
        articleService.deleteById(articleId);
    }

    /**
     * Find all articles by given author id (if any).
     *
     * @param authorId Identification number of the author
     * @return Iterable of found articles*/
    @GetMapping("${api.author}/{authorId}")
    @ResponseBody
    public Iterable<Article> findByAuthor(@PathVariable long authorId) {
        return articleService.findByAuthor(authorId);
    }

    /**
     * Find all articles with publish date in given period.
     *
     * @param startDate Start date of the period
     * @param endDate End date of the period
     * @return Iterable of found articles
     */
    @GetMapping("${api.period}")
    @ResponseBody
    public Iterable<Article> findAllInPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return articleService.findAllInPeriod(startDate, endDate);
    }
}
