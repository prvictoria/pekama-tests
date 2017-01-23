package com.pekama.app.draft;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Viachaslau_Balashevi on 1/23/2017.
 */
class LoginPageTestRobotImpl implements LoginPageTestRobot<Object> {

    private String name;
    private String password;

    public LoginPageTestRobot login(String name) {
        this.name = name;
        return this;
    }

    public LoginPageTestRobot password(String password) {
        this.password = password;
        return this;
    }

    public Object submit() {
        if (password.length() < 8) {
            throw new RuntimeException("Password < 8 length");
        }
        return null;
    }


    @Test
    void test() {
        Assert.assertNotNull(new LoginPageTestRobotImpl()
                .login("Vasia")
                .password("1111")
                .submit());

        Assert.assertNotNull(new LoginPageTestRobotImpl()
                .login("Vasia")
                .password("1111")
                .submit());

        Assert.assertNotNull(new LoginPageTestRobotImpl()
                .login("Vasia")
                .password("1111")
                .submit());

        Assert.assertNotNull(new LoginPageTestRobotImpl()
                .login("Vasia")
                .password("1111")
                .submit());

        Assert.assertNotNull(new LoginPageTestRobotImpl()
                .login("Vasia")
                .password("1111")
                .submit());

    }
}