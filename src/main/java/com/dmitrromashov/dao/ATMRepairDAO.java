package com.dmitrromashov.dao;

import com.dmitrromashov.model.ATMRepair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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

    public void updateATMName(int id, String atm) {
        ATMRepair atmRepair = entityManager.find(ATMRepair.class, id);
        atmRepair.setATM(atm);

    }

    public void updateRepairBeginDate(int id, Date date) {
        ATMRepair atmRepair = entityManager.find(ATMRepair.class, id);
        atmRepair.setRepairBeginDate(date);
    }

    public void updateRepairEndDate(int id, Date date) {
        ATMRepair atmRepair = entityManager.find(ATMRepair.class, id);
        atmRepair.setRepairEndDate(date);
    }

    public void updateWorkingStatus(int id, String status) {
        ATMRepair atmRepair = entityManager.find(ATMRepair.class, id);
        atmRepair.setWorkingStatus(status);
    }

    public void updateWorkCost(int id, int workCost) {
        ATMRepair atmRepair = entityManager.find(ATMRepair.class, id);
        atmRepair.setWorkCost(workCost);
    }
}
