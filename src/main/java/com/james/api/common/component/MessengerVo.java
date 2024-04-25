package com.james.api.common.component;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessengerVo {
    private String message;
    private int status;
    private String accessToken;
    private String refreshToken;
    private Long id;
}