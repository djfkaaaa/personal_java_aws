package com.james.api.team.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Log4j2
public class TeamDto {
    private Long id;
    private String regionName;
    private String teamName;
    private String eTeamName;
    private String originYear;
    private Long stadiumId;
    private String zipCode1;
    private String zipCode2;
    private String address;
    private String ddd;
    private String tel;
    private String fax;
    private String homepage;
    private String owner;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}