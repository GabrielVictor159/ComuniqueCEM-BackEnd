package com.comunique.comunique.model;

import java.io.Serializable;

public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    private String to;
    private String subject;
    private String body;
    private String username;
    private String password;

    public Email(String to, String subject, String body, String username, String password) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.username = username;
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

	@Override
	public String toString() {
		return "Email [to=" + to + ", subject=" + subject + ", body=" + body + ", username=" + username + ", password="
				+ password + "]";
	}
    
}

