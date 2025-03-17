package com.hiberus.hiring;

import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnabledIfSystemProperty(named = "suiteTesting", matches = "true")
public class GenericUseCaseSuiteTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void genericTestCasesByJsonFile() throws IOException {

    File folder = new File("src/test/resources/testcases");
    File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
    String baseUrl = "http://localhost:" + port;

    if (files != null) {

      List<TestResult> testResults = new ArrayList<>();

      for (File file : files) {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;

        ObjectMapper objectMapper = new ObjectMapper();

        while ((line = reader.readLine()) != null) {

          JsonNode caseNode = objectMapper.readTree(line);

          JsonNode requestNode = caseNode.get("request");
          String url = requestNode.get("url").asText();
          JsonNode requestBody = requestNode.get("body");
          JsonNode headersNode = requestNode.get("headers");
          String contentType =
              headersNode.has("Content-Type") ? headersNode.get("Content-Type").asText()
                  : "application/json";
          String method = requestNode.get("method").asText();

          HttpHeaders headers = new HttpHeaders();
          headers.set("Content-Type", contentType);
          HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

          long startTime = System.currentTimeMillis();
          ResponseEntity<String> response;
          long elapsedTime = 0;
          String status = "SUCCESS";
          int httpStatusCode = 200;

          try {
            response = switch (method.toUpperCase()) {
              case "POST" -> restTemplate.exchange(baseUrl + url, HttpMethod.POST, entity,
                  String.class);
              case "GET" -> restTemplate.exchange(baseUrl + url, HttpMethod.GET, entity,
                  String.class);
              case "PUT" -> restTemplate.exchange(baseUrl + url, HttpMethod.PUT, entity,
                  String.class);
              case "DELETE" -> restTemplate.exchange(baseUrl + url, HttpMethod.DELETE, entity,
                  String.class);
              default ->
                  throw new UnsupportedOperationException("Método HTTP no soportado: " + method);
            };
            elapsedTime = System.currentTimeMillis() - startTime;

            int expectedStatusCode = caseNode.get("response").get("status_code").asInt();
            httpStatusCode = response.getStatusCode().value();
            if (httpStatusCode != expectedStatusCode) {
              status = "FAILURE FROM API STATUS " + response.getStatusCode().value();
            }

          } catch (Exception e) {
            elapsedTime = System.currentTimeMillis() - startTime;
            status = "FAILURE: " + e.getMessage();
            httpStatusCode = 500;
          }

          testResults.add(TestResult.builder()
              .fileName(file.getName())
              .method(method)
              .status(status)
              .httpStatusCode(httpStatusCode)
              .url(url)
              .elapsedTime(elapsedTime).build());

        }
        reader.close();
      }
      generateReport(testResults);

    } else {
      fail("Not exist test cases");
    }
  }

  private void generateReport(List<TestResult> testResults) throws IOException {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = now.format(formatter);
    FileWriter writer = new FileWriter("target/suite-test-report.html");
    writer.write("<html><head><title>Test Report</title></head><body>");
    writer.write("<h1>Generic Test Cases Suite: Test Results</h1>");
    writer.write("<p>Generated on: " + formattedDate + "</p>");
    writer.write(
        "<table border='1'><tr><th>File</th><th>URL</th><th>Method</th><th>Status</th><th>Status Code</th><th>Time (ms)</th></tr>");

    for (TestResult result : testResults) {
      writer.write("<tr>");
      writer.write("<td>" + result.getFileName() + "</td>");
      writer.write("<td>" + result.getUrl() + "</td>");
      writer.write("<td>" + result.getMethod() + "</td>");
      writer.write("<td>" + result.getStatus() + "</td>");
      writer.write("<td>" + result.getHttpStatusCode() + "</td>");
      writer.write("<td>" + result.getElapsedTime() + "</td>");
      writer.write("</tr>");
    }
    writer.write("</table></body></html>");
    writer.close();
  }

}
