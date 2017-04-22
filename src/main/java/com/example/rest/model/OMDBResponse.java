package com.example.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class OMDBResponse {

    private Internal[] Search;
    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String Response;

    public Internal[] getSearch() {
        return Search;
    }
    @JsonProperty("Search")
    public void setSearch(Internal[] search) {
        Search = search;
    }

    public void setResponse(String response) {
        Response = response;
    }



    public String getTotalResults ()
    {
        return totalResults;
    }


       public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }


}
