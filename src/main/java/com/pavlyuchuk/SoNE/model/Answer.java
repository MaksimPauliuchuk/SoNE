package com.pavlyuchuk.SoNE.model;

/**
 * Created by Никита on 24.05.2016.
 */
public class Answer
{

	public double accuracy; // Точность решения (невязка)
	public long time; // Время исполнения
	public int runge; // Число иттераций для Рунге
	public double[] points; // Значения на слое (y координаты точек)
	public double[] x; // x координаты точек

	public Answer(double accuracy, long time, int runge, double[] points)
	{
		this.accuracy = accuracy;
		this.runge = runge;
		this.time = time;
		this.points = points.clone();
	}

	public Answer()
	{

	}
}
