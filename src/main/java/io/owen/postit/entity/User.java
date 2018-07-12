package io.owen.postit.entity;

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
}
