package subbotin.spring.microservices.testassignment.upday.exceptions;

/**
 * This exception is thrown when Entity can't be found in repository
 */
public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException(Long id) {
        super("Could not found keyword with id: " + id);
    }
}
