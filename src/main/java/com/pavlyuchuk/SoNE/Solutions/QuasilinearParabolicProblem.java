package com.pavlyuchuk.SoNE.Solutions;

import com.pavlyuchuk.SoNE.MathParser.MatchParser;
import com.pavlyuchuk.SoNE.model.Answer;
import com.pavlyuchuk.SoNE.model.Model;

public class QuasilinearParabolicProblem
{
	double vector[], vectorTao1[], vectorTao2[], h, tao;
	private String mu1;
	private String mu2;
	private String mu3;
	private String K;
	private String Ku;
	private String g;
	private Double xFrom;
	private Double xTo;
	private Double tFrom;
	private Double tTo;
	private Integer N;
	private Integer M;
	private Double eSystem;
	private Double eRunge;
	private String exactSolution;
	private Integer RungeNumber;
	private int numberBeta;
	private int iterRunge = 0;

	public QuasilinearParabolicProblem(Model model)
	{
		mu1 = model.getMu1_Text();
		mu2 = model.getMu2_Text();
		mu3 = model.getMu3_Text();
		K = model.getK_Text();
		Ku = model.getKu_Text();
		g = model.getG_Text();
		xFrom = Double.parseDouble(model.getxFrom_Text());
		xTo = Double.parseDouble(model.getxTo_Text());
		tFrom = Double.parseDouble(model.gettFrom_Text());
		tTo = Double.parseDouble(model.gettTo_Text());
		N = Integer.parseInt(model.getN_Text());
		M = Integer.parseInt(model.getM_Text());
		eSystem = Double.parseDouble(model.geteSystem_Text());
		eRunge = Double.parseDouble(model.geteRunge_Text());
		exactSolution = model.getExactSolution_Text();
		RungeNumber = Integer.parseInt(model.getUseRunge_Text());
		numberBeta = Integer.parseInt(model.getBeta_Text());
	}

	public void initialization()
	{
		h = (xTo - xFrom) / N;
		tao = (tTo - tFrom) / M;
		vector = new double[N + 1];
		vectorTao1 = new double[N + 1];
		vectorTao2 = new double[N + 1];
	}

	public void conditions()
	{
		for (int i = 0; i < vector.length; i++)
		{
			vector[i] = solveMu1(tFrom, xFrom + i * h);
		}
	}

	public Answer getReal() // без рунге
	{
		Answer answer = new Answer();
		answer.x = new double[N + 1];
		answer.points = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
			answer.points[j] = solveReal(tTo, xFrom + j * h);
		}
		return answer;
	}

	public Answer getAnswerTwoLayerWithoutRunge() // без рунге
	{
		Answer answer = new Answer();
		long start = System.currentTimeMillis();
		vectorTao1 = findMLayer(M, vector, tao);
		long finish = System.currentTimeMillis();
		answer.time = finish - start;
		answer.accuracy = realFunctionNeviazka(vectorTao1);
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		return answer;
	}

	public Answer getAnswerThreeLayerWithoutRunge() // без рунге
	{
		Answer answer = new Answer();
		long start = System.currentTimeMillis();
		if (M % 2 == 0)
		{
			vectorTao1 = findMLayer(M / 2, vector, 2 * tao);
		}
		else
		{
			vectorTao2 = findMLayer(1, vector, tao);
			vectorTao1 = findMLayer((M - 1) / 2, vectorTao2, 2 * tao);
		}
		long finish = System.currentTimeMillis();
		answer.time = finish - start;
		answer.accuracy = realFunctionNeviazka(vectorTao1);
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		return answer;
	}

	public Answer getAnswerTwoLayerWithRunge()
	{
		Answer answer = new Answer();
		double norma = 0;
		int i = 1;
		long start = System.currentTimeMillis();
		vectorTao1 = findMLayer(1, vector, tao);
		while (iterRunge++ < RungeNumber)
		{
			vectorTao2 = findMLayer((int) Math.pow(2, i), vector, tao * Math.pow(2, -i));
			norma = norma(vectorTao1, vectorTao2);
			if (norma < eRunge)
			{
				break;
			}
			else
			{
				vectorTao1 = vectorTao2.clone();
				M *= 2;
				tao /= 2;
				i++;
			}
		}
		vectorTao1 = findMLayer(M, vector, tao);
		long finish = System.currentTimeMillis();
		vectorTao2 = findMLayer(M * 2, vector, tao / 2);
		answer.accuracy = norma(vectorTao1, vectorTao2);
		answer.time = finish - start;
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		answer.runge = iterRunge;
		return answer;
	}

	public Answer getAnswerThreeLayerWithRunge()
	{
		Answer answer = new Answer();
		double norma = 0;
		int i = 1;
		long start = System.currentTimeMillis();
		vectorTao1 = findMLayer(1, vector, tao);
		while (iterRunge++ < RungeNumber)
		{
			vectorTao2 = findMLayer((int) Math.pow(2, i), vector, tao * Math.pow(2, -i));
			norma = norma(vectorTao1, vectorTao2);
			if (norma < eRunge)
			{
				break;
			}
			else
			{
				vectorTao1 = vectorTao2.clone();
				M *= 2;
				tao /= 2;
				i++;
			}
		}

		long finish;
		if (M % 2 == 0)
		{
			vectorTao1 = findMLayer(M / 2, vector, 2 * tao);
			finish = System.currentTimeMillis();
			vectorTao2 = findMLayer(M, vector, tao);
		}
		else
		{
			vectorTao2 = findMLayer(1, vector, tao);
			vectorTao1 = findMLayer((M - 1) / 2, vectorTao2, 2 * tao);
			finish = System.currentTimeMillis();
			vectorTao2 = findMLayer(M, vector, tao);
		}

		answer.accuracy = norma(vectorTao1, vectorTao2);
		answer.time = finish - start;
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		answer.runge = iterRunge;
		return answer;
	}

	public Answer getAnswerKrankNikWithoutRunge()// Без рунге
	{
		Answer answer = new Answer();
		long start = System.currentTimeMillis();
		vectorTao1 = findMLayerKN(M, vector, tao);
		long finish = System.currentTimeMillis();
		answer.time = finish - start;
		answer.accuracy = realFunctionNeviazka(vectorTao1);
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		return answer;
	}

	public Answer getAnswerKrankNikWithRunge()
	{
		Answer answer = new Answer();
		double norma = 0;
		int i = 1;
		long start = System.currentTimeMillis();
		vectorTao1 = findMLayerKN(1, vector, tao);
		while (iterRunge++ < RungeNumber)
		{
			vectorTao2 = findMLayerKN((int) Math.pow(2, i), vector, tao * Math.pow(2, -i));
			norma = norma(vectorTao1, vectorTao2);
			if (norma < eRunge)
			{
				break;
			}
			else
			{
				vectorTao1 = vectorTao2.clone();
				M *= 2;
				tao /= 2;
				i++;
			}
		}
		vectorTao1 = findMLayerKN(M, vector, tao);
		long finish = System.currentTimeMillis();
		vectorTao2 = findMLayerKN(M * 2, vector, tao / 2);
		answer.accuracy = norma(vectorTao1, vectorTao2);
		answer.time = finish - start;
		answer.points = vectorTao1.clone();
		answer.x = new double[N + 1];

		for (int j = 0; j <= N; j++)
		{
			answer.x[j] = xFrom + j * h;
		}
		answer.runge = iterRunge;
		return answer;
	}

	private double[] findMLayer(int M, double[] stroka, double tao)
	{
		double[] strokaout = stroka.clone();
		for (int m = 1; m <= M; m++)
		{
			strokaout = progonka(m, strokaout, tao);
			TridiagonalMatrixSolution.Print(strokaout);
			System.out.println();
		}
		return strokaout;
	}

	private double[] findMLayerKN(int M, double[] stroka, double tao)
	{
		double[] strokaout = stroka.clone();
		for (int m = 1; m <= M; m++)
		{
			strokaout = progonkaKN(m, strokaout, tao);
			TridiagonalMatrixSolution.Print(strokaout);
			System.out.println();
		}
		return strokaout;
	}

	public double[] progonka(int m, double[] stroka, double tao)
	{
		double beta, gamma = 0.01, betaminus, vectorDelta_Xn[], vectorF_Xn[], vectorY_M_iter[];
		double[] left = new double[N], center = new double[N + 1], right = new double[N];
		vectorF_Xn = new double[N + 1];
		vectorY_M_iter = stroka.clone();
		int numberBeta = 1;
		switch (numberBeta)
		{
			case 1:
			{
				beta = 1;
				break;
			}
			case 2:
			{
				beta = 0.1;
				break;
			}
			case 3:
			{
				beta = 0.1;
				gamma = beta * beta;
				break;
			}
			default:
				beta = 0.1;
				break;
		}
		while (true)
		{
			center[0] = center[N] = 1;
			vectorF_Xn[0] = vectorY_M_iter[0] - solveMu2(tFrom + tao * m, xFrom);
			vectorF_Xn[N] = vectorY_M_iter[N] - solveMu3(tFrom + tao * m, xTo);
			for (int n = 1; n < center.length - 1; n++)
			{
				left[n - 1] = ((solveKu(tFrom + tao * m, xFrom + n * h, stroka[n])) / (2 * h * h))
						* (vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1])
						- solveK(tFrom + tao * m, xFrom + n * h, stroka[n]) / (h * h);
				center[n] = 1.0 / tao + (2 * solveK(tFrom + tao * m, xFrom + n * h, stroka[n])) / (h * h);
				right[n] = -(solveKu(tFrom + tao * m, xFrom + n * h, stroka[n]) / (2 * h * h))
						* (vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1])
						- (solveK(tFrom + tao * m, xFrom + n * h, stroka[n])) / (h * h);
				vectorF_Xn[n] = (vectorY_M_iter[n] - stroka[n]) / tao
						- (solveKu(tFrom + tao * m, xFrom + n * h, stroka[n]))
								* Math.pow((vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1]) / (2 * h), 2)
						- solveK(tFrom + tao * m, xFrom + n * h, stroka[n]) / (h * h)
								* ((vectorY_M_iter[n + 1] - 2 * vectorY_M_iter[n] + vectorY_M_iter[n - 1]))
						- solveG(tFrom + tao * m, xFrom + n * h, stroka[n]);
			}
			double norma_Xn = 0.0;
			for (int n = 0; n < vectorF_Xn.length; n++)
			{
				norma_Xn += Math.pow(vectorF_Xn[n], 2);
				vectorF_Xn[n] *= -beta;
			}
			norma_Xn = Math.sqrt(norma_Xn);
			vectorDelta_Xn = TridiagonalMatrixSolution.Solve(left, center, right, vectorF_Xn);

			for (int n = 0; n < vectorY_M_iter.length; n++)
			{
				vectorY_M_iter[n] += vectorDelta_Xn[n];
			}
			double norma_XnPlus = 0.0;
			norma_XnPlus += Math.pow(vectorY_M_iter[0] - solveMu2(tFrom + tao * m, xFrom), 2);
			norma_XnPlus += Math.pow(vectorY_M_iter[N] - solveMu3(tFrom + tao * m, xTo), 2);
			for (int n = 1; n < vectorY_M_iter.length - 1; n++)
			{
				norma_XnPlus += Math.pow((vectorY_M_iter[n] - stroka[n]) / tao
						- (solveKu(tFrom + tao * m, xFrom + n * h, stroka[n]))
								* Math.pow((vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1]) / (2 * h), 2)
						- solveK(tFrom + tao * m, xFrom + n * h, stroka[n]) / (h * h)
								* ((vectorY_M_iter[n + 1] - 2 * vectorY_M_iter[n] + vectorY_M_iter[n - 1]))
						- solveG(tFrom + tao * m, xFrom + n * h, stroka[n]), 2);
			}

			norma_XnPlus = Math.sqrt(norma_XnPlus);
			System.out.println(norma_XnPlus);
			if (norma_XnPlus <= eSystem)
			{
				return vectorY_M_iter;
			}
			else
			{
				switch (numberBeta)
				{
					case 1:
					{
						beta = 1;
						break;
					}
					case 2:
					{
						beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
						break;
					}
					case 3:
					{
						betaminus = beta;
						beta = Math.min(1.0, (gamma * norma_Xn) / (norma_XnPlus * beta));
						gamma = gamma * (norma_Xn / norma_XnPlus) * (beta / betaminus);
						break;
					}
					default:
						beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
						break;
				}
			}
		}
	}

	public double[] progonkaKN(int m, double[] stroka, double tao)
	{
		double beta, gamma = 0.01, betaminus, vectorDelta_Xn[], vectorF_Xn[], vectorY_M_iter[];
		double[] left = new double[N], center = new double[N + 1], right = new double[N];
		vectorF_Xn = new double[N + 1];
		vectorY_M_iter = stroka.clone();
		switch (numberBeta)
		{
			case 1:
			{
				beta = 1;
				break;
			}
			case 2:
			{
				beta = 0.1;
				break;
			}
			case 3:
			{
				beta = 0.1;
				gamma = beta * beta;
				break;
			}
			default:
				beta = 0.1;
				break;
		}
		while (true)
		{
			center[0] = center[N] = 1;
			vectorF_Xn[0] = vectorY_M_iter[0] - solveMu2(tFrom + tao * m, xFrom);
			vectorF_Xn[N] = vectorY_M_iter[N] - solveMu3(tFrom + tao * m, xTo);
			for (int n = 1; n < center.length - 1; n++)
			{
				left[n - 1] = (solveKu(tFrom + tao * m, xFrom + h * n, stroka[n]) / (8 * h * h))
						* (vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1] + stroka[n + 1] - stroka[n - 1])
						- solveK(tFrom + tao * m, xFrom + h * n, stroka[n]) / (2 * h * h);
				right[n] = -(solveKu(tFrom + tao * m, xFrom + h * n, stroka[n]) / (8 * h * h))
						* (vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1] + stroka[n + 1] - stroka[n - 1])
						- solveK(tFrom + tao * m, xFrom + h * n, stroka[n]) / (2 * h * h);
				center[n] = 1.0 / tao + solveK(tFrom + tao * m, xFrom + h * n, stroka[n]) / (h * h);
				vectorF_Xn[n] = (vectorY_M_iter[n] - stroka[n]) / tao
						- (solveKu(tFrom + tao * m, xFrom + n * h, stroka[n])) * Math
								.pow((vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1] + stroka[n + 1] - stroka[n - 1])
										/ (4 * h), 2)
						- solveK(tFrom + tao * m, xFrom + n * h, stroka[n])
								* (vectorY_M_iter[n + 1] - 2 * vectorY_M_iter[n] + vectorY_M_iter[n - 1]
										+ stroka[n + 1] - 2 * stroka[n] + stroka[n - 1])
								/ (2 * h * h)
						- solveG(tFrom + tao * m, xFrom + n * h, stroka[n]);
			}
			double norma_Xn = 0.0;
			for (int n = 0; n < vectorF_Xn.length; n++)
			{
				norma_Xn += Math.pow(vectorF_Xn[n], 2);
				vectorF_Xn[n] *= -beta;
			}
			norma_Xn = Math.sqrt(norma_Xn);
			vectorDelta_Xn = TridiagonalMatrixSolution.Solve(left, center, right, vectorF_Xn);

			for (int n = 0; n < vectorY_M_iter.length; n++)
			{
				vectorY_M_iter[n] += vectorDelta_Xn[n];
			}
			double norma_XnPlus = 0.0;
			norma_XnPlus += Math.pow(vectorY_M_iter[0] - solveMu2(tFrom + tao * m, xFrom), 2);
			norma_XnPlus += Math.pow(vectorY_M_iter[N] - solveMu3(tFrom + tao * m, xTo), 2);
			for (int n = 1; n < vectorY_M_iter.length - 1; n++)
			{
				norma_XnPlus += Math.pow((vectorY_M_iter[n] - stroka[n]) / tao
						- (solveKu(tFrom + tao * m, xFrom + n * h, stroka[n])) * Math
								.pow((vectorY_M_iter[n + 1] - vectorY_M_iter[n - 1] + stroka[n + 1] - stroka[n - 1])
										/ (4 * h), 2)
						- solveK(tFrom + tao * m, xFrom + n * h, stroka[n])
								* (vectorY_M_iter[n + 1] - 2 * vectorY_M_iter[n] + vectorY_M_iter[n - 1]
										+ stroka[n + 1] - 2 * stroka[n] + stroka[n - 1])
								/ (2 * h * h)
						- solveG(tFrom + tao * m, xFrom + n * h, stroka[n]), 2);
			}

			norma_XnPlus = Math.sqrt(norma_XnPlus);
			System.out.println(norma_XnPlus);
			if (norma_XnPlus <= eSystem)
			{
				return vectorY_M_iter;
			}
			else
			{
				switch (numberBeta)
				{
					case 1:
					{
						beta = 1;
						break;
					}
					case 2:
					{
						beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
						break;
					}
					case 3:
					{
						betaminus = beta;
						beta = Math.min(1.0, (gamma * norma_Xn) / (norma_XnPlus * beta));
						gamma = gamma * (norma_Xn / norma_XnPlus) * (beta / betaminus);
						break;
					}
					default:
						beta = Math.min(1.0, beta * norma_Xn / norma_XnPlus);
						break;
				}
			}
		}
	}

	private double realFunctionNeviazka(double[] vect)
	{
		double nev = 0;
		for (int j = 0; j <= N; j++)
		{
			nev += Math.pow(solveReal(tTo, xFrom + j * h) - vect[j], 2);
		}
		nev = Math.sqrt(nev);
		return nev;
	}

	private double solveMu1(double t, double x)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(mu1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private double solveMu2(double t, double x)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(mu2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private double solveMu3(double t, double x)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(mu3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private double solveK(double t, double x, double u)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		matchParser.setVariable("u", u);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(K);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}

	private double solveKu(double t, double x, double u)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		matchParser.setVariable("u", u);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(Ku);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}

	private double solveG(double t, double x, double u)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		matchParser.setVariable("u", u);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(g);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private double solveReal(double t, double x)
	{
		MatchParser matchParser = new MatchParser();
		matchParser.setVariable("t", t);
		matchParser.setVariable("x", x);
		double result = 0.0;
		try
		{
			result = matchParser.Parse(exactSolution);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}

	private double norma(double[] stroka1, double[] stroka2)
	{
		double norma = 0.0;
		for (int i = 0; i < stroka1.length; i++)
		{
			norma += Math.pow(stroka1[i] - stroka2[i], 2);
		}
		norma = Math.sqrt(norma);
		return norma;
	}
}
