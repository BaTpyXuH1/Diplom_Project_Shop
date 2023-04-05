package org.top.diplom_project_shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/registration").setViewName("registration");
            registry.addViewController("/").setViewName("index");
            registry.addViewController("/client").setViewName("client/*");
            registry.addViewController("/goods").setViewName("goods/**");
            registry.addViewController("/client/client-list").setViewName("client/client-list");
            registry.addViewController("/client/client-form").setViewName("client/client-form");
            registry.addViewController("/client/client-detail").setViewName("client/client-detail");
            registry.addViewController("/client/client-update").setViewName("client/client-detail");
            registry.addViewController("/product").setViewName("product/*");
            registry.addViewController("/product/product-list").setViewName("product/product-list");
            registry.addViewController("/product/product-form").setViewName("product/product-form");
            registry.addViewController("/product/product-detail").setViewName("product/product-detail");
            registry.addViewController("/product/product-update").setViewName("product/product-update");
            registry.addViewController("/order").setViewName("order/*");
            registry.addViewController("/basket").setViewName("basket");
            registry.addViewController("/order/order-update").setViewName("order/order-update");
            registry.addViewController("/order/order-detail").setViewName("order/order-detail");
            registry.addViewController("/order/order-list").setViewName("order/order-list");
            registry.addViewController("/order/order-form").setViewName("order/order-form");
            registry.addViewController("/orderProduct").setViewName("orderProduct/*");
            registry.addViewController("/orderProduct/orderProduct-list").setViewName("orderProduct/orderProduct-list");
            registry.addViewController("/orderProduct/orderProduct-detail").setViewName("orderProduct/orderProduct-detail");
            registry.addViewController("/orderProduct/orderProduct-form").setViewName("orderProduct/orderProduct-form");
            registry.addViewController("/orderProduct/orderProduct-update").setViewName("orderProduct/orderProduct-update");






        }

    }


