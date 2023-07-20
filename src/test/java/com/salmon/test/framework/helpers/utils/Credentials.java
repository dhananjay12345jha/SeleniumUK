package com.salmon.test.framework.helpers.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Credentials {
    private String email;
    private String password;
    private Logger log = LoggerFactory.getLogger(Credentials.class);

    public Credentials() {
        setEmail(RandomGenerator.randomEmailAddress(10));
        setPassword(RandomGenerator.randomAlphabetic(8) + "1");
	    log.info("password returned -- " + getPassword());
    }

    public String getEmail() {
        log.info("email returned -- " + email);
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

}
