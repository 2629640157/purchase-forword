package com.ydgk.forword.entity;

/**
 * @author 游斌
 * @create 2020-02-20  21:51
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int rule;
    private String email;
    private String qq;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int rule, String email, String qq) {
        this.username = username;
        this.password = password;
        this.rule = rule;
        this.email = email;
        this.qq = qq;
    }

    public User(int id, String username, String password, int rule, String email, String qq) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.rule = rule;
        this.email = email;
        this.qq = qq;
    }
}
