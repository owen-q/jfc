package io.owen.jfc.entity;

import javax.persistence.*;

/**
 * Created by owen_q on 2018. 7. 9..
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_key")
    private String userKey;

    @Column(name = "is_authored")
    private boolean isAuthored = false;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    public User() {
    }

    public User(String userKey) {
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public boolean isAuthored() {
        return isAuthored;
    }

    public void setAuthored(boolean authored) {
        isAuthored = authored;
    }
}
