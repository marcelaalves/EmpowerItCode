package com.example.empowerit.com.example.network;

import com.example.empowerit.utils.Constants;

;

public class Authentication {

    public boolean authentWob(String email, String password) {
        return email.equals(Constants.emailWobTest)
                && password.equals(Constants.password);


    }

    public boolean authentCoorp(String email, String password) {
        return email.equals(Constants.emailCoorpTest) &&
                password.equals(Constants.password);


    }

}
