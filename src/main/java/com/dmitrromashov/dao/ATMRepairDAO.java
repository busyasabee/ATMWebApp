package com.dmitrromashov.dao;

import com.dmitrromashov.model.ATMRepair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ATMRepairDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public int saveATMRepairs(List<ATMRepair> atmRepairList){
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
}
