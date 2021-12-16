package com.example.gbspringlesson7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private Long id;
    private String title;
    private Integer cost;
    private Integer count;
}
