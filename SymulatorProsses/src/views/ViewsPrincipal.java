package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class ViewsPrincipal extends JFrame implements Views{

	private static final long serialVersionUID = 1L;
	TablePanel tableProcess;
	TablePanel tableProcessBlock;
	PaintProsses dataCPU;
	ActionListener listener;
	JPanel grafica;
	DefaultXYDataset dataset;
	JPanel panelProsses;
	VIewsInicial inicial;
	public ViewsPrincipal(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(700, 600));
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		this.listener = listener;
//		inicial = new VIewsInicial(listener);
//		add(inicial,BorderLayout.CENTER);
//		iniciarSimulacion() ;
		setVisible(true);
	}


	public void iniciarSimulacion() {
//		this.remove(inicial);
		tableProcess = new TablePanel();
		tableProcessBlock = new TablePanel();
		dataCPU = new PaintProsses();
		grafica = new JPanel();
		dataset = new DefaultXYDataset();
		panelProsses = new JPanel();
		panelProsses.setLayout(new GridLayout(2,1));
		panelProsses.add(tableProcess);
		panelProsses.add(tableProcessBlock);
		add(dataCPU,BorderLayout.PAGE_START);
//		add(grafica,BorderLayout.CENTER);
		add(panelProsses,BorderLayout.CENTER);
		revalidate();
		repaint();

	}


	public void graficaHoras(double[][] ds) {
		dataset  = new DefaultXYDataset();
		dataset.addSeries("",ds);
		JFreeChart chart = ChartFactory.createXYLineChart("", "","", dataset);
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		chart.getXYPlot().setRenderer(render);

		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		grafica.removeAll();
		grafica.add(panel,BorderLayout.CENTER);
	}


	@Override
	public void procesosListos(ArrayList<String[]> procesos) {
		tableProcess.fillTable(procesos, listener,"Procesos listos");
	}

	@Override
	public void procesosBloquedos(ArrayList<String[]> procesos) {
		tableProcessBlock.fillTable(procesos, listener,"Procesos Bloqueados");


	}

	@Override
	public void mostrarProceso() {

	}

	@Override
	public void paintDataCPU(ArrayList<String[]> dataCPUin,String[] dataProcessActual) {
		dataCPU.paintDataCPU(dataCPUin,dataProcessActual);
	}

}
