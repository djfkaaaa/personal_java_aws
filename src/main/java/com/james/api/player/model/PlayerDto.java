package com.james.api.player.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2

public class PlayerDto {
    private Long id;
    private String playerName;
    private String ePlayerName;
    private String nikeName;
    private String joinYyyy;
    private String nation;
    private String birthDate;
    private String backNo;
    private String position;
    private String solar;
    private String height;
    private String weight;
    private Long teamId;
    private LocalDateTime redDate;
    private LocalDateTime modDate;
}