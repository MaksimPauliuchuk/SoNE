package com.pavlyuchuk.SoNE.model;

/**
 * Created by Никита on 24.05.2016.
 */
public class Answer {

    public double accuracy; // Точность решения (невязка)
    public double time; // Время исполнения
    public int runge; // Число иттераций для Рунге
    public double[] points; // Значения на слое

    public Answer(double accuracy, double time, int runge, double[] points) {
        this.accuracy = accuracy;
        this.runge = runge;
        this.time = time;
        this.points = points.clone();
    }

    public Answer() {

    }
}
