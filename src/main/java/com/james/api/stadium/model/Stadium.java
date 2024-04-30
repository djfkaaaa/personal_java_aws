package com.james.api.stadium.model;

import com.james.api.schedule.model.Schedule;
import com.james.api.team.model.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "stadium")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stadium_id")
    private Long stadiumId;
    @Column(name = "stadium_name")
    private String  stadiumName;
    @Column(name = "hometeam_id")
    private Long hometeamId;
    @Column(name = "seat_count")
    private String seatCount;
    private String address;
    private String ddd;
    private String tel;
    @OneToMany(mappedBy = "stadium")
    private List<Team> team;
    @OneToMany(mappedBy = "stadium")
    private List<Schedule> schedule;
}