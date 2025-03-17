package com.hiberus.hiring.domain.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  private String code;
  private String message;
  @Builder.Default
  private List<String> messages = new ArrayList<>();

}
