package com.james.api.schedule.model;

import com.james.api.common.BaseEntitiy;
import com.james.api.stadium.model.Stadium;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name="schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@ToString(exclude = {"id"})
@AllArgsConstructor
@Builder
public class Schedule extends BaseEntitiy {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sche_date")
    private String scheDate;

    private String gubun;

    @Column(name = "hometeam_id")
    private Long hometeamId;

    @Column(name = "awayteam_id")
    private Long awayteamId;

    @Column(name = "home_score")
    private String homeScore;

    @Column(name = "away_score")
    private String awayScore;

    @ManyToOne
    @JoinColumn(name="stadium_id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Stadium stadium;
}