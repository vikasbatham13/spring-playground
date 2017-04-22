package com.example.processor;

import com.example.rest.model.FinalResponse;
import com.example.rest.model.Internal;
import com.example.rest.model.OMDBResponse;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieProcessor {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<FinalResponse> getMovie(String key) throws IOException {
        List<FinalResponse> finalResponse = new ArrayList<FinalResponse>();
        String response = this.restTemplate.getForObject("http://www.omdbapi.com/?s={s}", String.class, key);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        OMDBResponse omdbResponse = mapper.readValue(response, OMDBResponse.class);
        FinalResponse res;
        for (Internal search : omdbResponse.getSearch()) {
            res = new FinalResponse();
            res.setImdbID(search.getImdbID());
            res.setPoster(search.getPoster());
            res.setTitle(search.getTitle());
            res.setYear(search.getYear());
            finalResponse.add(res);
        }
        return finalResponse;
    }
}
