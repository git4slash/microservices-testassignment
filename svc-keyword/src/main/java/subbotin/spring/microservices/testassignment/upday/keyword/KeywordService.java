package subbotin.spring.microservices.testassignment.upday.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class KeywordService {

    private final Logger logger = Logger.getLogger(KeywordService.class.getName());

    private KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
        logger.info("KeywordService created");
    }

    public Iterable<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    public Optional<Keyword> getById(Long keywordID) {
        return keywordRepository.findById(keywordID);
    }

    public Long createNewKeyword(Keyword newKeyword) {
        return keywordRepository.findKeywordByNameEquals(newKeyword.getName()).orElse(keywordRepository.save(newKeyword)).getId();
    }

    /**
     * If given keyword id is correct then method delete articleID and delete a keyword if articleIDs set is empty
     *
     * @param keywordId keyword to delete
     */
    public void delete(Long keywordId) {
        keywordRepository.deleteById(keywordId);
    }


    public Iterable<Keyword> findKeywordsByArticleId(Long articleId) {
        logger.info("findKeywordsByArticleId with articleID=" + articleId);
        return keywordRepository.findKeywordsByArticleIDsContains(articleId);
    }

    public void patchKeyword(Long keywordID, Keyword updatedKeyword) {
        logger.info("patch with id=" + keywordID + " keyword:" + updatedKeyword);
        this.getById(keywordID).ifPresent(
                keywordToUpdate -> {
                    keywordToUpdate.setName(updatedKeyword.getName());
                    keywordToUpdate.setArticleIDs(updatedKeyword.getArticleIDs());
                    keywordRepository.save(keywordToUpdate);
                });
    }
}
