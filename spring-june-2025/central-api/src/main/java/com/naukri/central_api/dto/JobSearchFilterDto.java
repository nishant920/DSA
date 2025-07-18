package com.naukri.central_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobSearchFilterDto {
    List<String> jobTitle;
    List<String> jobLocation;
    List<String> jobSkill;
    List<String> companyName;
}
