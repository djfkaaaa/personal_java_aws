package com.james.api.article.model;

import com.sun.jdi.request.StepRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

public class ArticleDto {
    private Long id;
    private String title;
    private String content;
//    private String registerDate;
    private Long writerId;
    private Long boardId;
    private String regDate;
    private String modDate;
}
