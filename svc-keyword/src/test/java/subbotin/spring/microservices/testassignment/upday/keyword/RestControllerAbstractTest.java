package subbotin.spring.microservices.testassignment.upday.keyword;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import subbotin.spring.microservices.testassignment.upday.KeywordServiceApp;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KeywordServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestControllerAbstractTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    final MediaType expectedContentType = MediaType.APPLICATION_JSON_UTF8;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUpMVC() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
}
