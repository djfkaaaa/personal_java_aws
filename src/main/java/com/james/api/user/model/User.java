package com.james.api.user.model;


import com.james.api.article.model.Article;
import com.james.api.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"articles","id"})

public class User extends BaseEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String job;
    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Article> articles;
    private String token;

}
