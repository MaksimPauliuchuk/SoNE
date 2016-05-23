package com.pavlyuchuk.SoNE.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.pavlyuchuk.SoNE.model.Model;

/**
 * @author Maksim Pauliuchuk
 */
@SuppressWarnings("serial")
public class Gui extends JFrame {
	private int width = 1000;
	private int height = 600;

	private int leftColumnWidth;

	private JLabel logo;
	private JLabel mu1_Label;
	private JLabel mu2_Label;
	private JLabel mu3_Label;
	private JLabel K_Label;
	private JLabel Ku_Label;
	private JLabel g_Label;
	private JLabel xFrom_Label;
	private JLabel xTo_Label;
	private JLabel tFrom_Label;
	private JLabel tTo_Label;
	private JLabel N_Label;
	private JLabel M_Label;
	private JLabel eSystem_Label;
	private JLabel eRunge_Label;
	private JLabel exactSolution_Label;
	private JLabel useRunge_Label;
	private JLabel openFileName_Label;

	private JTextField mu1_Text;
	private JTextField mu2_Text;
	private JTextField mu3_Text;
	private JTextField K_Text;
	private JTextField Ku_Text;
	private JTextField g_Text;
	private JTextField xFrom_Text;
	private JTextField xTo_Text;
	private JTextField tFrom_Text;
	private JTextField tTo_Text;
	private JTextField N_Text;
	private JTextField M_Text;
	private JTextField eSystem_Text;
	private JTextField eRunge_Text;
	private JTextField exactSolution_Text;
	private JTextField useRunge_Text;

	private List<JTextField> textList = new ArrayList<>();

	private JCheckBox exactSolution_Box;
	private JCheckBox useRunge_Box;

	private JLabel accuracyTable_Label;
	private JLabel timeTable_Label;
	private JLabel itterationsTable_Label;
	private JLabel twoLayerMethoTable_Label;
	private JLabel threeLayerMethoTable_Label;
	private JLabel KNMethoTable_Label;

	private JTextField accuracyTwoTable_Text;
	private JTextField timeTwoTable_Text;
	private JTextField ittarationsTwoTable_Text;
	private JTextField accuracyThreeTable_Text;
	private JTextField timeThreeTable_Text;
	private JTextField ittarationsThreeTable_Text;
	private JTextField accuracyKNTable_Text;
	private JTextField timeKNTable_Text;
	private JTextField ittarationsKNTable_Text;

	private JCheckBox twoLayer_Box;
	private JCheckBox threeLayer_Box;
	private JCheckBox KNLayer_Box;

	private XYSeriesCollection dataset;
	private XYSeries seriesReal;
	private XYSeries seriesTwo;
	private XYSeries seriesThree;
	private XYSeries seriesKN;

	private ChartPanel chartPanel;

	private JFileChooser fc;

	private JButton openButton;
	private JButton startButton;

	public Gui() {
		super("Solution of Nonlinear Equations");
		initialize();

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		createBackground();
	}

	private void initialize() {
		Font font = new Font("Times", Font.BOLD, 16);
		Character mu = new Character((char) 956);
		Character eps = new Character((char) 949);

		ImageIcon icon = new ImageIcon(Gui.class.getClassLoader().getResource("pict/logo.png"));
		logo = new JLabel(icon);
		logo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		logo.setBorder(null);
		add(logo);

		leftColumnWidth = icon.getIconWidth();

		int marginTop = icon.getIconHeight();
		int marginLeft = 10;
		final int defaultHeight = 30;
		final int tableHeight = 195;

		// --------------------------------------------------------------

		// Left Menu

		// --------------------------------------------------------------

		mu1_Label = new JLabel(mu.toString() + "1(x,t)=");
		mu1_Label.setBounds(marginLeft, marginTop, 60, defaultHeight);
		mu1_Label.setFont(font);
		mu1_Label.setBorder(null);
		add(mu1_Label);

		mu1_Text = new JTextField();
		mu1_Text.setBounds(marginLeft + mu1_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + mu1_Label.getWidth()), defaultHeight);
		mu1_Text.setFont(font);
		mu1_Text.setBorder(null);
		marginTop += defaultHeight;
		add(mu1_Text);
		textList.add(mu1_Text);

		// --------------------------------------------------------------

		mu2_Label = new JLabel(mu.toString() + "2(x,t)=");
		mu2_Label.setBounds(marginLeft, marginTop, 60, defaultHeight);
		mu2_Label.setFont(font);
		mu2_Label.setBorder(null);
		add(mu2_Label);

		mu2_Text = new JTextField();
		mu2_Text.setBounds(marginLeft + mu2_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + mu2_Label.getWidth()), defaultHeight);
		mu2_Text.setFont(font);
		mu2_Text.setBorder(null);
		marginTop += defaultHeight;
		add(mu2_Text);
		textList.add(mu2_Text);

		// --------------------------------------------------------------

		mu3_Label = new JLabel(mu.toString() + "3(x,t)=");
		mu3_Label.setBounds(marginLeft, marginTop, 60, defaultHeight);
		mu3_Label.setFont(font);
		mu3_Label.setBorder(null);
		add(mu3_Label);

		mu3_Text = new JTextField();
		mu3_Text.setBounds(marginLeft + mu3_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + mu3_Label.getWidth()), defaultHeight);
		mu3_Text.setFont(font);
		mu3_Text.setBorder(null);
		marginTop += defaultHeight;
		add(mu3_Text);
		textList.add(mu3_Text);

		// --------------------------------------------------------------

		K_Label = new JLabel("K(u,x,t)=");
		K_Label.setBounds(marginLeft, marginTop, 85, defaultHeight);
		K_Label.setFont(font);
		K_Label.setBorder(null);
		add(K_Label);

		K_Text = new JTextField();
		K_Text.setBounds(marginLeft + K_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + K_Label.getWidth()), defaultHeight);
		K_Text.setFont(font);
		K_Text.setBorder(null);
		marginTop += defaultHeight;
		add(K_Text);
		textList.add(K_Text);

		// --------------------------------------------------------------

		Ku_Label = new JLabel("K'u(u,x,t)=");
		Ku_Label.setBounds(marginLeft, marginTop, 85, defaultHeight);
		Ku_Label.setFont(font);
		Ku_Label.setBorder(null);
		add(Ku_Label);

		Ku_Text = new JTextField();
		Ku_Text.setBounds(marginLeft + Ku_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + Ku_Label.getWidth()), defaultHeight);
		Ku_Text.setFont(font);
		Ku_Text.setBorder(null);
		marginTop += defaultHeight;
		add(Ku_Text);
		textList.add(Ku_Text);

		// --------------------------------------------------------------

		g_Label = new JLabel("g(u,x,t)=");
		g_Label.setBounds(marginLeft, marginTop, 85, defaultHeight);
		g_Label.setFont(font);
		g_Label.setBorder(null);
		add(g_Label);

		g_Text = new JTextField();
		g_Text.setBounds(marginLeft + g_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + g_Label.getWidth()), defaultHeight);
		g_Text.setFont(font);
		g_Text.setBorder(null);
		marginTop += defaultHeight;
		add(g_Text);
		textList.add(g_Text);

		// --------------------------------------------------------------

		xFrom_Label = new JLabel("X от =");
		xFrom_Label.setBounds(marginLeft, marginTop, (leftColumnWidth - marginLeft) / 4, defaultHeight);
		xFrom_Label.setFont(font);
		xFrom_Label.setBorder(null);
		add(xFrom_Label);

		xFrom_Text = new JTextField();
		xFrom_Text.setBounds(marginLeft + xFrom_Label.getWidth(), marginTop, (leftColumnWidth - marginLeft) / 4,
				defaultHeight);
		xFrom_Text.setFont(font);
		xFrom_Text.setBorder(null);
		add(xFrom_Text);
		textList.add(xFrom_Text);

		xTo_Label = new JLabel(" до =");
		xTo_Label.setBounds(marginLeft + xFrom_Label.getWidth() + xFrom_Text.getWidth(), marginTop,
				(leftColumnWidth - marginLeft) / 4, defaultHeight);
		xTo_Label.setFont(font);
		xTo_Label.setBorder(null);
		add(xTo_Label);

		xTo_Text = new JTextField();
		xTo_Text.setBounds(marginLeft + xFrom_Label.getWidth() + xFrom_Text.getWidth() + xTo_Label.getWidth(),
				marginTop, (leftColumnWidth - marginLeft) / 4 + 2, defaultHeight);
		xTo_Text.setFont(font);
		xTo_Text.setBorder(null);
		marginTop += defaultHeight;
		add(xTo_Text);
		textList.add(xTo_Text);

		// --------------------------------------------------------------

		tFrom_Label = new JLabel("T от =");
		tFrom_Label.setBounds(marginLeft, marginTop, (leftColumnWidth - marginLeft) / 4, defaultHeight);
		tFrom_Label.setFont(font);
		tFrom_Label.setBorder(null);
		add(tFrom_Label);

		tFrom_Text = new JTextField();
		tFrom_Text.setBounds(marginLeft + tFrom_Label.getWidth(), marginTop, (leftColumnWidth - marginLeft) / 4,
				defaultHeight);
		tFrom_Text.setFont(font);
		tFrom_Text.setBorder(null);
		add(tFrom_Text);
		textList.add(tFrom_Text);

		tTo_Label = new JLabel(" до =");
		tTo_Label.setBounds(marginLeft + tFrom_Label.getWidth() + tFrom_Text.getWidth(), marginTop,
				(leftColumnWidth - marginLeft) / 4, defaultHeight);
		tTo_Label.setFont(font);
		tTo_Label.setBorder(null);
		add(tTo_Label);

		tTo_Text = new JTextField();
		tTo_Text.setBounds(marginLeft + tFrom_Label.getWidth() + tFrom_Text.getWidth() + tTo_Label.getWidth(),
				marginTop, (leftColumnWidth - marginLeft) / 4 + 2, defaultHeight);
		tTo_Text.setFont(font);
		tTo_Text.setBorder(null);
		marginTop += defaultHeight;
		add(tTo_Text);
		textList.add(tTo_Text);

		// --------------------------------------------------------------

		N_Label = new JLabel("Разбиение X=");
		N_Label.setBounds(marginLeft, marginTop, 115, defaultHeight);
		N_Label.setFont(font);
		N_Label.setBorder(null);
		add(N_Label);

		N_Text = new JTextField();
		N_Text.setBounds(marginLeft + N_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + N_Label.getWidth()), defaultHeight);
		N_Text.setFont(font);
		N_Text.setBorder(null);
		marginTop += defaultHeight;
		add(N_Text);
		textList.add(N_Text);

		// --------------------------------------------------------------

		M_Label = new JLabel("Разбиение T=");
		M_Label.setBounds(marginLeft, marginTop, 115, defaultHeight);
		M_Label.setFont(font);
		M_Label.setBorder(null);
		add(M_Label);

		M_Text = new JTextField();
		M_Text.setBounds(marginLeft + M_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + M_Label.getWidth()), defaultHeight);
		M_Text.setFont(font);
		M_Text.setBorder(null);
		marginTop += defaultHeight;
		add(M_Text);
		textList.add(M_Text);

		// --------------------------------------------------------------

		eSystem_Label = new JLabel(eps.toString() + " системы=");
		eSystem_Label.setBounds(marginLeft, marginTop, 95, defaultHeight);
		eSystem_Label.setFont(font);
		eSystem_Label.setBorder(null);
		add(eSystem_Label);

		eSystem_Text = new JTextField();
		eSystem_Text.setBounds(marginLeft + eSystem_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + eSystem_Label.getWidth()), defaultHeight);
		eSystem_Text.setFont(font);
		eSystem_Text.setBorder(null);
		marginTop += defaultHeight;
		add(eSystem_Text);
		textList.add(eSystem_Text);

		// --------------------------------------------------------------

		eRunge_Label = new JLabel(eps.toString() + " Runge=");
		eRunge_Label.setBounds(marginLeft, marginTop, 95, defaultHeight);
		eRunge_Label.setFont(font);
		eRunge_Label.setBorder(null);
		add(eRunge_Label);

		eRunge_Text = new JTextField();
		eRunge_Text.setBounds(marginLeft + eRunge_Label.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + eRunge_Label.getWidth()), defaultHeight);
		eRunge_Text.setFont(font);
		eRunge_Text.setBorder(null);
		marginTop += defaultHeight;
		add(eRunge_Text);
		textList.add(eRunge_Text);

		// --------------------------------------------------------------

		exactSolution_Box = new JCheckBox();
		exactSolution_Box.setBounds(marginLeft + 2, marginTop + 5, 20, 20);
		exactSolution_Box.setBorder(null);
		exactSolution_Box.setBackground(Color.WHITE);
		add(exactSolution_Box);

		exactSolution_Label = new JLabel("Точное решение=");
		exactSolution_Label.setBounds(marginLeft + 20, marginTop, 150, defaultHeight);
		exactSolution_Label.setFont(font);
		exactSolution_Label.setBorder(null);
		add(exactSolution_Label);

		exactSolution_Text = new JTextField();
		exactSolution_Text.setBounds(marginLeft + exactSolution_Label.getWidth() + 20, marginTop,
				leftColumnWidth - (marginLeft + exactSolution_Label.getWidth()) - 20, defaultHeight);
		exactSolution_Text.setFont(font);
		exactSolution_Text.setBorder(null);
		marginTop += defaultHeight;
		add(exactSolution_Text);
		textList.add(exactSolution_Text);

		// --------------------------------------------------------------

		useRunge_Box = new JCheckBox();
		useRunge_Box.setBounds(marginLeft + 2, marginTop + 5, 20, 20);
		useRunge_Box.setBorder(null);
		useRunge_Box.setBackground(Color.WHITE);
		add(useRunge_Box);

		useRunge_Label = new JLabel("Итерации Runge=");
		useRunge_Label.setBounds(marginLeft + 20, marginTop, 150, defaultHeight);
		useRunge_Label.setFont(font);
		useRunge_Label.setBorder(null);
		add(useRunge_Label);

		useRunge_Text = new JTextField();
		useRunge_Text.setBounds(marginLeft + useRunge_Label.getWidth() + 20, marginTop,
				leftColumnWidth - (marginLeft + useRunge_Label.getWidth()) - 20, defaultHeight);
		useRunge_Text.setFont(font);
		useRunge_Text.setBorder(null);
		marginTop += defaultHeight;
		add(useRunge_Text);
		textList.add(useRunge_Text);

		// --------------------------------------------------------------

		// File input

		// --------------------------------------------------------------

		fc = new JFileChooser();
		openButton = new JButton("Загрузить условие");
		openButton.setBounds(marginLeft, marginTop, 190, defaultHeight);
		openButton.setFont(font);
		openButton.setBorder(null);
		openButton.setBackground(Color.LIGHT_GRAY);
		openFileName_Label = new JLabel();
		openFileName_Label.setBounds(marginLeft + openButton.getWidth(), marginTop,
				leftColumnWidth - (marginLeft + openButton.getWidth()), defaultHeight);
		openFileName_Label.setFont(font);
		openFileName_Label.setBorder(null);
		add(openFileName_Label);

		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(Gui.this);
				File file = fc.getSelectedFile();

				try (Scanner scanner = new Scanner(file)) {
					String line = "";
					String[] split;
					while (scanner.hasNext()) {
						line = scanner.nextLine();
						split = line.split("=");

						switch (split[0]) {
						case "mu1":
							mu1_Text.setText(split[1]);
							break;
						case "mu2":
							mu2_Text.setText(split[1]);
							break;
						case "mu3":
							mu3_Text.setText(split[1]);
							break;
						case "K":
							K_Text.setText(split[1]);
							break;
						case "Ku":
							Ku_Text.setText(split[1]);
							break;
						case "g":
							g_Text.setText(split[1]);
							break;
						case "xFrom":
							xFrom_Text.setText(split[1]);
							break;
						case "xTo":
							xTo_Text.setText(split[1]);
							break;
						case "tFrom":
							tFrom_Text.setText(split[1]);
							break;
						case "tTo":
							tTo_Text.setText(split[1]);
							break;
						case "N":
							N_Text.setText(split[1]);
							break;
						case "M":
							M_Text.setText(split[1]);
							break;
						case "eSystem":
							eSystem_Text.setText(split[1]);
							break;
						case "eRunge":
							eRunge_Text.setText(split[1]);
							break;
						case "exactSolution":
							exactSolution_Text.setText(split[1]);
							break;
						case "rungeCount":
							useRunge_Text.setText(split[1]);
							break;
						default: {
							textList.forEach((k) -> k.setText(""));
							String message = "Неизвестный элемент \"" + split[0]
									+ "\".\nИсправьте файл и попробуйте еще раз.\nФайл должен иметь следующий формат: \nmu1=\nmu2=\nmu3=\nK=\nKu=\ng=\nxFrom=\nxTo=\ntFrom=\ntTo=\nN=\nM=\neSystem=\neRunge=\nexactSolution=\nrungeCount=";
							JOptionPane.showMessageDialog(new JFrame(), message, "Неверный формат файла",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						}
					}
					openFileName_Label.setText(file.getName());
				} catch (FileNotFoundException e1) {
					e1.getMessage();
				} catch (Exception e2) {
					e2.getMessage();
				}
			}
		});
		marginTop += defaultHeight;
		add(openButton);

		// --------------------------------------------------------------

		// Start button

		// --------------------------------------------------------------

		marginTop += 5;
		startButton = new JButton("Решить");
		startButton.setBounds(marginLeft, marginTop, 190, defaultHeight);
		startButton.setFont(font);
		startButton.setBorder(null);
		startButton.setBackground(Color.LIGHT_GRAY);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seriesKN.clear();
				seriesReal.clear();
				seriesThree.clear();
				seriesTwo.clear();

				Model model = new Model(mu1_Text.getText(), mu2_Text.getText(), mu3_Text.getText(), K_Text.getText(),
						Ku_Text.getText(), g_Text.getText(), xFrom_Text.getText(), xTo_Text.getText(),
						tFrom_Text.getText(), tTo_Text.getText(), N_Text.getText(), M_Text.getText(),
						eSystem_Text.getText(), eRunge_Text.getText(), exactSolution_Text.getText(),
						useRunge_Text.getText());

			}
		});

		add(startButton);

		// --------------------------------------------------------------

		// Table

		// --------------------------------------------------------------

		marginTop = height - tableHeight;
		marginLeft = leftColumnWidth + marginLeft;

		// --------------------------------------------------------------

		accuracyTable_Label = new JLabel();
		accuracyTable_Label.setText("Точность решения");
		accuracyTable_Label.setBounds(marginLeft + 220, marginTop, 200, defaultHeight);
		accuracyTable_Label.setFont(font);
		accuracyTable_Label.setBorder(null);
		add(accuracyTable_Label);

		timeTable_Label = new JLabel();
		timeTable_Label.setText("Время (мс)");
		timeTable_Label.setBounds(marginLeft + accuracyTable_Label.getWidth() + 220, marginTop, 180, defaultHeight);
		timeTable_Label.setFont(font);
		timeTable_Label.setBorder(null);
		add(timeTable_Label);

		itterationsTable_Label = new JLabel();
		itterationsTable_Label.setText("Runge");
		itterationsTable_Label.setBounds(marginLeft + accuracyTable_Label.getWidth() + timeTable_Label.getWidth() + 220,
				marginTop, 60, defaultHeight);
		itterationsTable_Label.setFont(font);
		itterationsTable_Label.setBorder(null);
		marginTop += defaultHeight;
		add(itterationsTable_Label);

		// --------------------------------------------------------------

		twoLayer_Box = new JCheckBox();
		twoLayer_Box.setBounds(marginLeft, marginTop + 5, 20, 20);
		twoLayer_Box.setBorder(null);
		twoLayer_Box.setBackground(Color.WHITE);
		add(twoLayer_Box);

		twoLayerMethoTable_Label = new JLabel("Двуслойный неявный");
		twoLayerMethoTable_Label.setBounds(marginLeft + twoLayer_Box.getWidth(), marginTop, 200, defaultHeight);
		twoLayerMethoTable_Label.setFont(font);
		twoLayerMethoTable_Label.setBorder(null);
		add(twoLayerMethoTable_Label);

		accuracyTwoTable_Text = new JTextField();
		accuracyTwoTable_Text.setBounds(marginLeft + twoLayer_Box.getWidth() + twoLayerMethoTable_Label.getWidth(),
				marginTop, 200, defaultHeight);
		accuracyTwoTable_Text.setFont(font);
		accuracyTwoTable_Text.setBorder(null);
		add(accuracyTwoTable_Text);

		timeTwoTable_Text = new JTextField();
		timeTwoTable_Text.setBounds(marginLeft + twoLayer_Box.getWidth() + twoLayerMethoTable_Label.getWidth()
				+ accuracyTwoTable_Text.getWidth(), marginTop, 180, defaultHeight);
		timeTwoTable_Text.setFont(font);
		timeTwoTable_Text.setBorder(null);
		add(timeTwoTable_Text);

		ittarationsTwoTable_Text = new JTextField();
		ittarationsTwoTable_Text.setBounds(
				marginLeft + twoLayer_Box.getWidth() + twoLayerMethoTable_Label.getWidth()
						+ accuracyTwoTable_Text.getWidth() + timeTwoTable_Text.getWidth(),
				marginTop, 60, defaultHeight);
		ittarationsTwoTable_Text.setFont(font);
		ittarationsTwoTable_Text.setBorder(null);
		marginTop += defaultHeight;
		add(ittarationsTwoTable_Text);

		// --------------------------------------------------------------

		threeLayer_Box = new JCheckBox();
		threeLayer_Box.setBounds(marginLeft, marginTop + 5, 20, 20);
		threeLayer_Box.setBorder(null);
		threeLayer_Box.setBackground(Color.WHITE);
		add(threeLayer_Box);

		threeLayerMethoTable_Label = new JLabel("Трехслойный неявный");
		threeLayerMethoTable_Label.setBounds(marginLeft + threeLayer_Box.getWidth(), marginTop, 200, defaultHeight);
		threeLayerMethoTable_Label.setFont(font);
		threeLayerMethoTable_Label.setBorder(null);
		add(threeLayerMethoTable_Label);

		accuracyThreeTable_Text = new JTextField();
		accuracyThreeTable_Text.setBounds(
				marginLeft + threeLayer_Box.getWidth() + threeLayerMethoTable_Label.getWidth(), marginTop, 200,
				defaultHeight);
		accuracyThreeTable_Text.setFont(font);
		accuracyThreeTable_Text.setBorder(null);
		add(accuracyThreeTable_Text);

		timeThreeTable_Text = new JTextField();
		timeThreeTable_Text.setBounds(marginLeft + threeLayer_Box.getWidth() + threeLayerMethoTable_Label.getWidth()
				+ accuracyThreeTable_Text.getWidth(), marginTop, 180, defaultHeight);
		timeThreeTable_Text.setFont(font);
		timeThreeTable_Text.setBorder(null);
		add(timeThreeTable_Text);

		ittarationsThreeTable_Text = new JTextField();
		ittarationsThreeTable_Text.setBounds(
				marginLeft + threeLayer_Box.getWidth() + threeLayerMethoTable_Label.getWidth()
						+ accuracyThreeTable_Text.getWidth() + timeThreeTable_Text.getWidth(),
				marginTop, 60, defaultHeight);
		ittarationsThreeTable_Text.setFont(font);
		ittarationsThreeTable_Text.setBorder(null);
		marginTop += defaultHeight;
		add(ittarationsThreeTable_Text);

		// --------------------------------------------------------------

		KNLayer_Box = new JCheckBox();
		KNLayer_Box.setBounds(marginLeft, marginTop + 5, 20, 20);
		KNLayer_Box.setBorder(null);
		KNLayer_Box.setBackground(Color.WHITE);
		add(KNLayer_Box);

		KNMethoTable_Label = new JLabel("Кранк-Николсон");
		KNMethoTable_Label.setBounds(marginLeft + KNLayer_Box.getWidth(), marginTop, 200, defaultHeight);
		KNMethoTable_Label.setFont(font);
		KNMethoTable_Label.setBorder(null);
		add(KNMethoTable_Label);

		accuracyKNTable_Text = new JTextField();
		accuracyKNTable_Text.setBounds(marginLeft + KNLayer_Box.getWidth() + KNMethoTable_Label.getWidth(), marginTop,
				200, defaultHeight);
		accuracyKNTable_Text.setFont(font);
		accuracyKNTable_Text.setBorder(null);
		add(accuracyKNTable_Text);

		timeKNTable_Text = new JTextField();
		timeKNTable_Text.setBounds(
				marginLeft + KNLayer_Box.getWidth() + KNMethoTable_Label.getWidth() + accuracyKNTable_Text.getWidth(),
				marginTop, 180, defaultHeight);
		timeKNTable_Text.setFont(font);
		timeKNTable_Text.setBorder(null);
		add(timeKNTable_Text);

		ittarationsKNTable_Text = new JTextField();
		ittarationsKNTable_Text.setBounds(marginLeft + KNLayer_Box.getWidth() + KNMethoTable_Label.getWidth()
				+ accuracyKNTable_Text.getWidth() + timeKNTable_Text.getWidth(), marginTop, 60, defaultHeight);
		ittarationsKNTable_Text.setFont(font);
		ittarationsKNTable_Text.setBorder(null);
		marginTop += defaultHeight;
		add(ittarationsKNTable_Text);

		// --------------------------------------------------------------

		// JFreeChart

		// --------------------------------------------------------------

		dataset = new XYSeriesCollection();

		seriesReal = new XYSeries("Real");
		seriesTwo = new XYSeries("Two layer");
		seriesThree = new XYSeries("Three layer");
		seriesKN = new XYSeries("Krank-Nikolson");

		dataset.addSeries(seriesKN);
		dataset.addSeries(seriesTwo);
		dataset.addSeries(seriesThree);
		dataset.addSeries(seriesReal);

		NumberAxis domain = new NumberAxis("x");
		NumberAxis range = new NumberAxis("f(x)");

		XYSplineRenderer r = new XYSplineRenderer(3);
		XYPlot xyplot = new XYPlot(dataset, domain, range, r);
		JFreeChart chart = new JFreeChart(xyplot);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(leftColumnWidth - 5, 0, width - leftColumnWidth, height - tableHeight);
		ChartUtilities.applyCurrentTheme(chart);
		add(chartPanel);
	}

	void createBackground() {

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, width, height);
		panel.setBackground(Color.WHITE);
		add(panel);
	}
}
