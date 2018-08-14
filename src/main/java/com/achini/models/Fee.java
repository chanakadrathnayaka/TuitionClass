package com.achini.models;

import com.achini.models.types.ClassType;

import java.util.Objects;

/**
 * @author Chanaka Rathnayaka
 */
public class Fee {
    private int feeId;
    private double amount;
    private ClassType classType;

    public Fee(double amount, ClassType classType) {
        this.amount = amount;
        this.classType = classType;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public double getAmount() {
        return amount;
    }

    public ClassType getClassType() {
        return classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fee)) return false;
        Fee fee = (Fee) o;
        return feeId == fee.feeId &&
                classType == fee.classType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(feeId, classType);
    }
}
