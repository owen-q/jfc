package io.owen.jfc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by owen_q on 2018. 7. 17..
 */
@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private long id;

    @Column(name = "created_time")
    private ZonedDateTime createdTime;

    @OneToMany(mappedBy = "match")
    private List<User> attendList;

    public Match() {

    }

}
