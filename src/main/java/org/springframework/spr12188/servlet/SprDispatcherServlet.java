package org.springframework.spr12188.servlet;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        value = "/",
        initParams = {
                @WebInitParam(
                        name = "contextClass",
                        value = "org.springframework.web.context.support.AnnotationConfigWebApplicationContext"
                ),
                @WebInitParam(
                        name = "contextConfigLocation",
                        value = "org.springframework.spr12188.spring.SprConfiguration"
                )},
        asyncSupported = true
)
public class SprDispatcherServlet extends DispatcherServlet {
}
