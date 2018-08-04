package com.dmitrromashov.dao;

import com.dmitrromashov.model.ATMRepair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Repository
@Transactional
public class ATMRepairDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public int saveATMRepairs(List<ATMRepair> atmRepairList) throws RuntimeException{
        int savedEntitiesCount = 0;

        for (ATMRepair atmRepair: atmRepairList){
            entityManager.persist(atmRepair);
            savedEntitiesCount += 1;
        }

        return savedEntitiesCount;
    }

    public void clearTable(){
        Query deleteQuery = entityManager.createQuery("DELETE from ATMRepair");
        deleteQuery.executeUpdate();
    }

    public List<ATMRepair> getAllATMRepairs(){
        Query selectQuery = entityManager.createQuery("from ATMRepair");
        ArrayList<ATMRepair> atmRepairs = new ArrayList<ATMRepair>(selectQuery.getResultList());
        return atmRepairs;
    }
}
