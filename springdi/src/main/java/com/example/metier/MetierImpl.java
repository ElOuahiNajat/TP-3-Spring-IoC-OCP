package com.example.metier;

import com.example.dao.IDao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;                 // pas de @Qualifier ici
    @Override
    public double calcul() { return dao.getValue() * 2; }

    @PostConstruct
    private void init() {
        System.out.println("[TRACE] DAO injecté = " + dao.getClass().getSimpleName());
    }
}