package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VIewsInicial extends JPanel{

	private static final long serialVersionUID = 1L;

	JSpinner timeSymulation;
	JButton enviarButton;

	public VIewsInicial(ActionListener listener) {
		setLayout(new BorderLayout());

		JPanel panelConten = new JPanel();
		panelConten.setLayout(new GridLayout(5,1));
		timeSymulation = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel(2,2,10,1);
		timeSymulation.setModel(model);
		panelConten.add(timeSymulation);

		enviarButton = new JButton("ENVIAR");
		enviarButton.setActionCommand("ENVIAR");
		enviarButton.addActionListener(listener);

		add(panelConten,BorderLayout.CENTER);
		add(enviarButton);

	}

}
