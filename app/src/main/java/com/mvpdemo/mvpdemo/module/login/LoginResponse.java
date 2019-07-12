package com.mvpdemo.mvpdemo.module.login;

public class LoginResponse {
    private String session_mid;
    private String session_mtoken;
    private String mobile;
    private String name;
    private String cardno;

    public String getSession_mid() {
        return session_mid;
    }

    public void setSession_mid(String session_mid) {
        this.session_mid = session_mid;
    }

    public String getSession_mtoken() {
        return session_mtoken;
    }

    public void setSession_mtoken(String session_mtoken) {
        this.session_mtoken = session_mtoken;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}
