package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.DAO;
import views.ViewsPrincipal;

public class CPU implements ActionListener {
	private ViewsPrincipal view;
	private DAO dao;

	public CPU() {
		dao = new DAO(20);
		view = new ViewsPrincipal(this);
		view.iniciarSimulacion();
		view.procesosListos(dao.getDataProcess());
		timer();
	}

	public void timer() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (dao.getTime()>0) {
					try {
						dao.deleteTime();
						dao.crearProceso();
						dao.peckProcessActual();
						dao.deleteTimeProcess();
						dao.deleteNextIO();
						dao.peckProcessActualBlock();
						dao.deleteIO();
						view.paintDataCPU(dao.getDataCPU(),dao.getProcessActual());
						view.procesosListos(dao.getDataProcess());
						view.procesosBloquedos(dao.getProcesosBloqueados());
						dao.deleteQuamtum();
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public static void main(String[] args) {
		new CPU();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ENVIAR":

			break;

		default:
			break;
		}
	}

}
