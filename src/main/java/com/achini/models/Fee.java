package com.achini.models;

import com.achini.models.types.ClassType;

/**
 * @author Chanaka Rathnayaka
 */
public class Fee {
    private double amount;
    private ClassType classType;

    public Fee(double amount, ClassType classType) {
        this.amount = amount;
        this.classType = classType;
    }

    public double getAmount() {
        return amount;
    }

    public ClassType getClassType() {
        return classType;
    }

}
