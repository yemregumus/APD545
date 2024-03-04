package org.example.carloanapplication;

public class Loan {
    private int periodMonths;
    private double interestRate;
    private String paymentFrequency;

    public Loan(int periodMonths, double interestRate, String paymentFrequency) {
        this.periodMonths = periodMonths;
        this.interestRate = interestRate;
        this.paymentFrequency = paymentFrequency;
    }

    // Getters and setters
    public int getPeriodMonths() {
        return periodMonths;
    }

    public void setPeriodMonths(int periodMonths) {
        this.periodMonths = periodMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }
}