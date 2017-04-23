package com.example.processor;

import com.example.config.MovieConfig;
import com.example.rest.model.FinalResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieProcessorTest {
    @Mock
    MovieConfig config;
    private MovieProcessor movieProcessor;
    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        when(config.getUrl()).thenReturn("http://www.omdbapi.com/");
        this.movieProcessor = new MovieProcessor(config);
        this.mockServer = MockRestServiceServer.createServer(movieProcessor.getRestTemplate());
    }

    @Test
    public void getMoviesTest() throws Exception {
        mockServer.expect(MockRestRequestMatchers.requestTo("http://www.omdbapi.com/?s=lord"))
                .andRespond(MockRestResponseCreators.withSuccess(getJSON("/movies.json"),
                        MediaType.TEXT_PLAIN));
        List<FinalResponse> finalResponsesList = movieProcessor.getMovie("lord");
        Assert.assertThat(finalResponsesList.get(0).getImdbID(), Matchers.equalTo("tt0120737"));
        Assert.assertThat(finalResponsesList.get(0).getTitle(), Matchers.equalTo("The Lord of the Rings: The Fellowship of the Ring"));
        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));


    }
}
