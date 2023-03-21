package org.top.diplom_project_shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


    @Configuration
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/registration").setViewName("registration");
            registry.addViewController("/").setViewName("index");
            registry.addViewController("/client/client-list").setViewName("client/client-list");
            registry.addViewController("/client/client-form").setViewName("client/client-form");
            registry.addViewController("/catalog/catalog").setViewName("catalog/catalog");
            registry.addViewController("/client/client-detail").setViewName("client/client-detail");
            registry.addViewController("/orderProduct/orderProduct-detail").setViewName("orderProduct/orderProduct-detail");



        }

    }


