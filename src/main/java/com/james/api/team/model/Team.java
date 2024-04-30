package com.james.api.team.model;

import com.james.api.common.BaseEntitiy;
import com.james.api.player.model.Player;
import com.james.api.stadium.model.Stadium;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString(exclude = {"id"})
@Builder
@Entity(name = "teams")
@Log4j2
public class Team extends BaseEntitiy {
    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "e_team_name")
    private String eTeamName;
    @Column(name = "origin_year")
    private String originYear;
    @Column(name = "zip_code1")
    private String zipCode1;
    @Column(name = "zip_code2")
    private String zipCode2;
    private String address;
    private String ddd;
    private String tel;
    private String fax;
    private String homepage;
    private String owner;

    @OneToMany(mappedBy = "player")
    private List<Player> player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = true,
            referencedColumnName = "stadium_id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Stadium stadium;
}