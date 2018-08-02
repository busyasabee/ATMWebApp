package com.dmitrromashov.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"atm", "repair_begin"}))
public class ATMRepair {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "atm")
    private String ATM;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "repair_begin")
    private Date repairBeginDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "repair_end")
    private Date repairEndDate;

    private String workingStatus;

    private int workCost;

    public String getATM() {
        return ATM;
    }

    public void setATM(String ATM) {
        this.ATM = ATM;
    }

    public Date getRepairBeginDate() {
        return repairBeginDate;
    }

    public void setRepairBeginDate(Date repairBeginDate) {
        this.repairBeginDate = repairBeginDate;
    }

    public Date getRepairEndDate() {
        return repairEndDate;
    }

    public void setRepairEndDate(Date repairEndDate) {
        this.repairEndDate = repairEndDate;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public int getWorkCost() {
        return workCost;
    }

    public void setWorkCost(int workCost) {
        this.workCost = workCost;
    }
}
