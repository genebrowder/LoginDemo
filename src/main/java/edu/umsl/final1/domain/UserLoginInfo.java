package edu.umsl.final1.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by genebrowder on 5/1/16.
 */
@Entity
@Table(name="USER_LOGIN_INFO")
public class UserLoginInfo implements Serializable {


    private static final long serialVersionUID = 336542947443965225L;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="USER_LOGIN_INFO_ID")
    @Id
    private long id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    public UserLoginInfo(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
