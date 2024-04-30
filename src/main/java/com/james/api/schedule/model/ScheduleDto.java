package com.james.api.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ScheduleDto {
    private Long id;
    private String scheDate;
    private String gubun;
    private Long hometeamId;
    private Long awayteamId;
    private String homeScore;
    private String awayScore;

    private Long stadiumId;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

}