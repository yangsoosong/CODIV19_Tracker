package com.example.coronavirustracker.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
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

  // After creating service, execute this
  @PostConstruct
  // Run off method on regular basis
  @Scheduled(cron = "* * * 1 * *")
  public void fetchVirusData() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(CoronavirusURL))
            .build();
    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    StringReader csvBodyReader = new StringReader(httpResponse.body());
    //System.out.println(httpResponse.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    for (CSVRecord record : records) {
      String state = record.get("Province/State");
      System.out.println(state);
//      String customerNo = record.get("CustomerNo");
//      String name = record.get("Name");
    }
  }
}
