package com.hiberus.hiring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
  private String fileName;
  private String method;
  private String url;
  private String status;
  private int httpStatusCode;
  private long elapsedTime;
}
