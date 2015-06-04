package com.honeycombee.web.artdental.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by gizmo4u on 15. 6. 1..
 */
public class Member {

    @Id
    public String id;

    public String email;
    public String password;
    public String name;

    public Member() {
    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
