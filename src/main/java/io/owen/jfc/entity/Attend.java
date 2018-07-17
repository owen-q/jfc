package io.owen.jfc.entity;

import javax.persistence.*;

/**
 * Created by owen_q on 2018. 7. 17..
 */

@Entity
@Table(name = "attend")
public class Attend {
    @Id
    @GeneratedValue
    @Column(name = "attend_id")
    private long id;
}
