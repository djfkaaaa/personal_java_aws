package com.james.api.stadium.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class DTO {
    private Long stadium_id;
    private String  stadium_name;
    private Long hometeam_id;
    private String seat_count;
    private String address;
    private String ddd;
    private String tel;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}