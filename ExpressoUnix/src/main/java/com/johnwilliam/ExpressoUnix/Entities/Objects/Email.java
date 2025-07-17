package com.johnwilliam.ExpressoUnix.Entities.Objects;

public class Email {
    private String email;
    public Email(String email){
        this.email=email;
    }
    public String getValue(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
