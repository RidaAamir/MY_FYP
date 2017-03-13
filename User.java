package com.example.emad.splashscreen;

/**
 * Created by عماد شیخ on 10/12/2016.
 */
public class User {
    private int id;
    private String _username;
    private String _useremail;
    private String _password;
    public String Session;

    public User(String _username, String _useremail, String _password) {
        this._username = _username;
        this._useremail = _useremail;
        this._password = _password;
        Session="Online";
    }

    public String get_useremail() {
        return _useremail;
    }

    public void set_useremail(String _useremail) {
        this._useremail = _useremail;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {

    }
    public User(int id, String _username, String _password) {
        this.id = id;
        this._username = _username;
        this._password = _password;
    }

    public User(String _username) {
        this._username = _username;
    }

    public User(String _username, String _password) {
        this._username = _username;
        this._password = _password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
