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
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            exposeDirectory("profile-images", registry);
        }
        private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
            Path uploadDir = Paths.get(dirName);
            String uploadPath = uploadDir.toFile().getAbsolutePath();
            if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
            registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
        }
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/registration").setViewName("registration");
            registry.addViewController("/").setViewName("index");
            registry.addViewController("/client/client-list").setViewName("client/client-list");
            registry.addViewController("/client/client-form").setViewName("client/client-form");
            registry.addViewController("/client/client-detail").setViewName("client/client-detail");
            registry.addViewController("/product").setViewName("product/*");
            registry.addViewController("/product/product-list").setViewName("product/product-list");
            registry.addViewController("/product/product-form").setViewName("product/product-form");



        }

    }


