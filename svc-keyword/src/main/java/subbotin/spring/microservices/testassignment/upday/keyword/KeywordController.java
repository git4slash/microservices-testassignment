package subbotin.spring.microservices.testassignment.upday.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("${api.home}")
public class KeywordController {
    private final Logger logger = Logger.getLogger(KeywordController.class.getName());
    private KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
        logger.info("Keyword controller created.");
    }

    @GetMapping
    @ResponseBody
    public Iterable<Keyword> getAllKeywordsByArticleId(@RequestParam(required = false) Optional<Long> articleID) {
        logger.info("getAllKeywordsByArticleId with articleID=" + articleID);
        return articleID.map(keywordService::findKeywordsByArticleId)
                .orElseGet(keywordService::getAllKeywords);
    }

    @GetMapping("/{keywordID}")
    @ResponseBody
    public Optional<Keyword> getOneById(@PathVariable Long keywordID) {
        logger.info("getOneById with keywordID=" + keywordID);
        return keywordService.getById(keywordID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long createNew(@RequestBody Keyword keyword) {
        logger.info("createNew with keyword=" + keyword);
        return keywordService.createNewKeyword(keyword);
    }

    @PatchMapping("/{keywordID}")
    @ResponseStatus(HttpStatus.OK)
    public void patchKeyword(@PathVariable Long keywordID, @RequestBody Keyword keyword) {
        keywordService.patchKeyword(keywordID, keyword);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam(name = "keywordID") Long keywordID) {
        keywordService.delete(keywordID);
    }
}
