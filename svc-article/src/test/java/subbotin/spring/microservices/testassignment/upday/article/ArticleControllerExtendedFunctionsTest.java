package subbotin.spring.microservices.testassignment.upday.article;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticleControllerExtendedFunctionsTest extends ArticleControllerAbstractTest {

    @Value("${api.author}")
    private String authorPrefix;

    @Value("${api.period}")
    String apiPeriodPrefix;

    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenExistAuthorId_whenGetAllArticlesByAuthorIdRequestIsPerformed_thenResponseStatusIsOkAndResponseContentTypeIsCorrect() throws Exception {
        // Given
        final String getUrl = apiHomeAddress + authorPrefix + "/" + testEntitiesSavedInRepoList.get(1).getAuthorId();

        // When
        mvc.perform(get(getUrl))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(expectedContentType));
    }

    @Test
    @WithMockUser(username = "admin", password = "secret", roles = "ADMIN")
    public void givenExistAuthorIdAndExpectedArticlesQuantity_whenGetAllArticlesByAuthorIdRequestIsPerformed_thenRetrievedInformationHasCorrectSize() throws Exception {

        // Given
        final long givenAuthorId = 99L;
        final String getUrl = apiHomeAddress + authorPrefix + "/" + (int) givenAuthorId;
        final long expectedQuantity = StreamSupport.stream(articleRepository.findArticlesByAuthorId(givenAuthorId).spliterator(), false).count();

        // When
        mvc.perform(get(getUrl))

                // Then
                .andExpect(jsonPath("$", hasSize((int) expectedQuantity)));
    }

    @Test
    public void givenCorrectUrlAndTimePeriod_whenGetArticlesByPeriodRequestIsPerformed_thenStatusIsOkAndContentTypeIsCorrect() throws Exception {
        // Prepare
        final LocalDate startDate = LocalDate.now().minusMonths(3);
        List<Article> expectedArticlesList = TEST_ARTICLE_LIST.stream()
                .peek(article -> article.setPublishDate(startDate))
                .map(articleRepository::save)
                .collect(Collectors.toList());

        // Given
        final String periodUrl = apiHomeAddress + apiPeriodPrefix;

        final String formattedStartDate = startDate.format(DateTimeFormatter.ISO_DATE);
        final String formattedEndDate = startDate.plusDays(1).format(DateTimeFormatter.ISO_DATE);

        // When
        mvc.perform(get(periodUrl)
                .param("startDate", formattedStartDate)
                .param("endDate", formattedEndDate))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(expectedContentType))
                .andExpect(jsonPath("$", hasSize(expectedArticlesList.size())));
    }
}
