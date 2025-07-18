package com.naukri.database_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryDto {
    List<String> locations;
    List<String> titles;
    List<String> companies;
    List<String> skills;
}
