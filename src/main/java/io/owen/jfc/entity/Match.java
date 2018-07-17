package io.owen.jfc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * Created by owen_q on 2018. 7. 17..
 */
@Entity
@Table(name = "matches")
public class Match {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ZonedDateTime createdTime;

}
