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
    private long id;
}
