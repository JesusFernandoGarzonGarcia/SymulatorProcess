package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	String dataProcess[];
	JScrollPane sc;

	public TablePanel() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		dataProcess =new String[] {"ID","TIME LIFE","NEXT I/O", "I/O", "QUANTUM", "STATUS"};

		model = new DefaultTableModel();
		table = new JTable(model);
		table.setOpaque(false);

		sc = new JScrollPane();
		sc.setBackground(Color.WHITE);
	}

	public void addElement(Object[] object) {
		model.addRow(object);
		repaint();
	}

	public void fillTable(ArrayList<String[]> elementos,ActionListener listener,String name) {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),name,1,1,new Font("Arial",Font.BOLD,25)));
		model.setRowCount(0);
		if (elementos.size()>0) {
			model.setColumnIdentifiers(dataProcess);
			table.setModel(model);
				for (String[] document : elementos) {
					addElement(document);
				}
			}
		
		remove(sc);
		sc.setViewportView(table);
		sc.revalidate();
		sc.repaint();
		sc.setBackground(Color.WHITE);
		add(sc,BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	public JTable getTable() {
		return table;
	}

}
