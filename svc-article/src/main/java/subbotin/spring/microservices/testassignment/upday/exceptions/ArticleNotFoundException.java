package subbotin.spring.microservices.testassignment.upday.exceptions;

/**
 * This exception is thrown when Entity can't be found in repository
 */
public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("Could not found keyword" + id);
    }
}
