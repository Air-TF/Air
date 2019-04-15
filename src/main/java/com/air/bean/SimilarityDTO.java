package com.air.bean;

public class SimilarityDTO<T> {
    private T t1;
    private T t2;
    private double similarity;

    public SimilarityDTO(T t1, T t2, double similarity) {
        this.t1 = t1;
        this.t2 = t2;
        this.similarity = similarity;
    }

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "SimilarityDTO{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                ", similarity=" + similarity +
                '}';
    }
}
