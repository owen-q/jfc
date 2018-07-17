package io.owen.jfc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by owen_q on 2018. 7. 9..
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userKey;

    @Column(name = "is_authored")
    private boolean isAuthored = false;

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
