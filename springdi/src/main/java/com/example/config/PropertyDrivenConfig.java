package com.example.config;

import com.example.dao.DaoImpl;
import com.example.dao.DaoImpl2;
import com.example.dao.IDao;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")  // lire le fichier properties
public class PropertyDrivenConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public IDao dao1() {
        return new DaoImpl();
    }

    @Bean
    public IDao dao2() {
        return new DaoImpl2();
    }

    @Bean(name = "dao")
    public IDao selectedDao(@Value("${dao.target}") String target, ApplicationContext ctx) {
        IDao dao;
        try {
            dao = (IDao) ctx.getBean(target);
        } catch (Exception e) {
            throw new IllegalArgumentException("Implémentation inconnue: " + target);
        }
        System.out.println("[TRACE] DAO injecté = " + dao.getClass().getSimpleName());
        return dao;
    }
}
