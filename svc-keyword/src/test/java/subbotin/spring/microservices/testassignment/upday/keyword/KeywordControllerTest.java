package subbotin.spring.microservices.testassignment.upday.keyword;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class KeywordControllerTest extends KeywordControllerAbstractTest {

    // GET all
    @Test
    public void givenUrlToWebRestApiHome_whenRequestIsExecuted_then200IsReceivedAndResponseContentTypeIsJsonAndSizeIsCorrect() throws Exception {

        // Given
        String apiUrl = apiHomeAddress;
        final int expectedSize = (int) keywordRepository.count();

        // When
        mvc.perform(get(apiUrl))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(expectedContentType))
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    // GET all by articleID
    @Test
    public void givenUrlToWebRestApiHomeAndArticleId_whenRequestIsExecuted_thenSizeIsCorrect() throws Exception {

        // Given
        String apiUrl = apiHomeAddress;
        final Keyword keywordWithTwoArticleIDs = new Keyword("keywordWithTwoArticleIDs");
        final Keyword keywordWithOneArticleId = new Keyword("keywordWithOneArticleId");
        keywordWithTwoArticleIDs.addArticleId(1L);
        keywordWithTwoArticleIDs.addArticleId(2L);
        keywordWithOneArticleId.addArticleId(1L);
        keywordRepository.save(keywordWithTwoArticleIDs);
        keywordRepository.save(keywordWithOneArticleId);

        // When
        mvc.perform(get(apiUrl).param("articleID", "1"))

                // Then
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void givenKeywordNotExists_whenKeywordInfoIsRetrieved_thenOkIsReceived() throws Exception {

        // Given
        Long notExistingIndex = Long.MAX_VALUE;
        String nonExistEntityUrl = apiHomeAddress + "/" + notExistingIndex;

        //When
        mvc.perform(get(nonExistEntityUrl))

                //Then
                .andExpect(status().isOk());
    }

    // GET get one keyword by existing id
    @Test
    public void givenEntityExistsUrl_whenEntityInformationIsRetrieved_thenRetrievedInformationIsCorrect() throws Exception {

        // Given
        Keyword entityThatExistsInRepo = testEntitiesSavedInRepoList.get(0);
        String existingEntityUrl = apiHomeAddress + "/" + entityThatExistsInRepo.getId().intValue();

        // When
        mvc.perform(get(existingEntityUrl))

                //Then
                .andExpect(jsonPath("$.name", is(entityThatExistsInRepo.getName())));
    }

    // POST create new keyword
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenNewEntity_whenNewEntityPostRequestIsPerformed_thenStatusIsCreated() throws Exception {

        // Given
        final String jsonOfEntityToSave = mapper.writeValueAsString(new Keyword("NewTagToSave"));
        final long entitiesInRepoBefore = keywordRepository.count();

        // When
        mvc.perform(post(apiHomeAddress)
                .contentType(expectedContentType)
                .content(jsonOfEntityToSave))

                // Then
                .andExpect(status().isCreated());
        Assert.assertThat(keywordRepository.count(), is(entitiesInRepoBefore + 1));
    }

    // DELETE delete entity by id
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenApiUrlAndKeywordId_whenDeleteKeywordRequestIsPerformed_thenStatusIsOkAndRepoSizeIsChanged() throws Exception {
        //Given
        Keyword entityToDelete = keywordRepository.save(new Keyword("Must be deleted"));
        final long entitiesInRepoBefore = keywordRepository.count();

        // When
        mvc.perform(delete(apiHomeAddress)
                .param("keywordID", String.valueOf(entityToDelete.getId().intValue())))

                // Then
                .andExpect(status().isOk());
        Assert.assertThat(keywordRepository.count(), is(entitiesInRepoBefore - 1));
    }

    // PATCH
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenKeywordIdExistAndKeywordToUpdate_whenRequestIsPerformed_thenStatusIsOkAndRetrievedSizeIsAndInfoIsCorrect() throws Exception {
        // Given
        final int idOfKeywordToUpdate = 0;
        final Keyword updatedKeyword = new Keyword("Updated");
        updatedKeyword.addArticleId(1234L);
        final long indexesBeforePatch = keywordRepository.count();
        final String patchUrl = apiHomeAddress + "/" + idOfKeywordToUpdate;

        // When
        mvc.perform(patch(patchUrl)
                .contentType(expectedContentType)
                .content(mapper.writeValueAsString(updatedKeyword)))
        // Then
                .andExpect(status().isOk());

        Assert.assertThat(keywordRepository.count(), is(indexesBeforePatch));
    }
}