package com.def.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
@SpringBootApplication

public class AccountsApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
   
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccountsApplication.class);
    }
    
}
