package subbotin.spring.microservices.testassignment.upday.article;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticleControllerCRUDTest extends ArticleControllerAbstractTest {

    @Test
    public void givenUrlToWebRestApiHome_whenRequestIsExecuted_then200IsReceived_and_ResponseContentTypeIsJson() throws Exception {

        // Given
        String apiUrl = apiHomeAddress;

        // When
        mvc.perform(get(apiUrl))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(expectedContentType));
    }

    @Test
    public void givenArticleNotExists_whenArticleInfoIsRetrieved_thenOkIsReceived() throws Exception {

        // Given
        Long notExistingIndex = Long.MAX_VALUE;
        String nonExistEntityUrl = apiHomeAddress + "/" + notExistingIndex;

        //When
        mvc.perform(get(nonExistEntityUrl))

                //Then
                .andExpect(status().isOk());
    }

    //    GET get one keyword by existing id
    @Test
    public void givenEntityExistsUrl_whenEntityInformationIsRetrieved_thenRetrievedInformationIsCorrect() throws Exception {

        // Given
        Article entityThatExistsInRepo = testEntitiesSavedInRepoList.get(0);
        String existingEntityUrl = apiHomeAddress + "/" + entityThatExistsInRepo.getId().intValue();

        // When
        mvc.perform(get(existingEntityUrl))

                //Then
                .andExpect(jsonPath("$.text", is(entityThatExistsInRepo.getText())))
                .andExpect(jsonPath("$.authorId", is(entityThatExistsInRepo.getAuthorId().intValue())));
    }

    // POST create new keyword
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenNewEntity_whenNewEntityPostRequestIsPerformed_thenStatusIsCreated() throws Exception {

        // Given
        final Article entityToSave =
                new Article("Header", "Description", "Text", 1234L);

        final String postUrl = apiHomeAddress;

        final String jsonOfEntityToSave = mapper.writeValueAsString(entityToSave);

        // When
        mvc.perform(post(postUrl)
                .contentType(expectedContentType)
                .content(jsonOfEntityToSave))

                // Then
                .andExpect(status().isCreated());
    }

    // PUT update one entity
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenArticleExistsUrl_whenUpdateArticleInfoRequestIsPerformed_thenStatusIsOk() throws Exception {

        //Given
        final Article articleToUpdate = testEntitiesSavedInRepoList.get(0);
        articleToUpdate.setHeader("Updated header");
        articleToUpdate.setShortDescription("New description");
        final String jsonOfUpdatedEntity = mapper.writeValueAsString(articleToUpdate);

        final String putUrl = apiHomeAddress + "/" + articleToUpdate.getId().intValue();

        // When
        mvc.perform(put(putUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonOfUpdatedEntity))

                // Then
                .andExpect(status().isOk());
    }

    // DELETE delete entity by id
    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenArticleExistsUrl_whenDeleteArticleRequestIsPerformed_thenStatusIsOk() throws Exception {
        //Given
        final String deleteUrl = apiHomeAddress + "/" + testEntitiesSavedInRepoList.get(0).getId().intValue();

        // When
        mvc.perform(delete(deleteUrl))

                // Then
                .andExpect(status().isOk());
    }
}