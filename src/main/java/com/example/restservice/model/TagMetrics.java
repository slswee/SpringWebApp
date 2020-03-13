package com.example.restservice.model;

public class TagMetrics {
    int median;
    double mean;
    double stdDeviation;

    public TagMetrics(int median, double mean, double stdDeviation) {
        this.median = median;
        this.mean = mean;
        this.stdDeviation = stdDeviation;
    }

    public int getMedian() {
        return median;
    }

    public double getMean() {
        return mean;
    }

    public double getStdDeviation() {
        return stdDeviation;
    }
}