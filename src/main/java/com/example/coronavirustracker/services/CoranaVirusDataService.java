package com.example.coronavirustracker.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.PostConstruct;

/**
 * Created by yangsoo on 5/23/20.
 * Make a call to url and fetch the data.
 */
@Service
public class CoranaVirusDataService {

  private static String CoronavirusURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

  @PostConstruct
  // after creating service, execute this
  public void fetchVirusData() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(CoronavirusURL))
            .build();
    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(httpResponse.body());
  }
}
