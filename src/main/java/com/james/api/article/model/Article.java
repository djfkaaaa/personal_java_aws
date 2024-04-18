package com.james.api.article.model;

import com.james.api.board.model.Board;
import com.james.api.common.BaseEntitiy;
import com.james.api.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
@ToString(exclude = {"id"})
public class Article extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

//
//    @Builder(builderMethodName = "builder")
//    public Article(Long id,String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
}