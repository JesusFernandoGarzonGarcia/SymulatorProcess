package views;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintProsses extends JPanel{

	private static final long serialVersionUID = 1L;

	private ArrayList<String[]> data;
	private String[] dataProcess;
	JPanel panelDataCPU;
	JPanel dataProcessActual;
	public PaintProsses() {
		setLayout(new GridLayout(2,2));
		panelDataCPU = new JPanel();
		panelDataCPU.setLayout(new GridLayout(1,2));
		dataProcessActual = new JPanel();
		dataProcessActual.setLayout(new GridLayout(1,6));
		add(panelDataCPU);
		add(dataProcessActual);
	}

	public void paintDataCPU(ArrayList<String[]> data,String[] dataProcess) {
		this.data=data;
		this.dataProcess=dataProcess;
		paintData();
		 paintDataProcessActual();
	}
	private void paintData() {
		panelDataCPU.removeAll();
		for (String[] strings : data) {
			JLabel label = new JLabel();
			label.setBorder(BorderFactory.createTitledBorder(strings[0]));
			label.setText(strings[1]);
			panelDataCPU.add(label);
		}
		revalidate();
		repaint();
	}
	private void paintDataProcessActual() {
		dataProcessActual.removeAll();
		dataProcessActual.setBorder(BorderFactory.createTitledBorder("Proceso Actual"));
		if (dataProcess.length>0) {
			for (String strings : dataProcess) {
				JLabel label = new JLabel();
				label.setText(strings);
				dataProcessActual.add(label);
			}
		}
		revalidate();
		repaint();
	}

}
