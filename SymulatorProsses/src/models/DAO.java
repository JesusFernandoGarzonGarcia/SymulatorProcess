package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DAO {

	private int time;
	private int idProcess;
	private int tiempoNuevoProceso;
	private Queue<Process> stackBlock;
	private Queue<Process> stackRunning;
	private Status status;
	private Process processActual;
	private Process processActualBlock;
	private ArrayList<double[]> dataGraficacion;

	public DAO(int time) {
		this.idProcess =0;
		this.time = time;
		this.status = Status.IDLE;
		this.stackBlock = new LinkedList<>();
		this.stackRunning = new LinkedList<>();
		this.dataGraficacion = new ArrayList<>();
		processActual = null;
	}


	public void crearProceso() {
		if (tiempoNuevoProceso==0) {
			Process procesoNuevo = new Process((++idProcess)+"",(int) Util.pseudoaleatorios(2,20),(int) Util.pseudoaleatorios(1,5), (int) Util.pseudoaleatorios(1,3),(int) Util.pseudoaleatorios(1,5),Status.READY);
			stackRunning.add(procesoNuevo);
			this.status=Status.BUSY;
			tiempoParaNuevoProceso();
		}
	}

	public void peckProcessActual() {
		if (stackRunning.size()>0) {
			validateProcess();
			processActual=stackRunning.remove();
			if (processActual!=null) {
				processActual.setStatus(Status.RUNNING);
			}
			status=Status.BUSY;
		}
	}

	public void peckProcessActualBlock() {
		if (stackBlock.size()>0) {
			processActualBlock=stackBlock.remove();
		}
	}

	private void tiempoParaNuevoProceso() {
		tiempoNuevoProceso=(int)Util.pseudoaleatorios(0,5);
	}


	public void deleteNextIO() {
		if (processActual!=null) {
			boolean state= processActual.deliteNexIO();
			if (state) {
				stackBlock.add(processActual);
			}
		}
	}

	public void deleteIO() {
		if (processActualBlock!=null) {
			boolean state= processActualBlock.deleteTrabajoIO();
			if (state) {
				stackRunning.add(processActualBlock);
			}
		}
	}


	public void deleteTimeProcess() {
		if (processActual!=null&&processActual.getTimeLife()>0) {
			processActual.deliteLive();
		}
	}


	public void deleteTime() {
		time--;
		if (tiempoNuevoProceso>0) {
			tiempoNuevoProceso--;
		}
	}

	public int getTime() {
		return time;
	}

	public ArrayList<double[]> getDataGraficacion() {
		return dataGraficacion;
	}

	public ArrayList<String[]> getDataCPU() {
		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[] {"Tiempo de reloj",time+""});
		data.add(new String[] {"Tiempo para nuevo proceso",tiempoNuevoProceso+""});
		data.add(new String[] {"Estado",status.toString()});
		dataGraficacion.add(new double[] {time,tiempoNuevoProceso});
		return data;
	}

	public String[] getProcessActual() {
		String [] dataProces=new String[] {};
		if (processActual!=null) {
			dataProces = processActual.getdataProcess();
		}else {
			status=Status.IDLE;
		}
		return dataProces;
	}

	public void deleteQuamtum() {
		if (processActual!=null) {
			if (processActual.getQuantum()>0) {
				processActual.deliteQuamtum();
				validateProcess();
			}
		}
	}

	private void validateProcess() {
		if (processActual!=null) {
			if (processActual.getNextIO()>0) {
				processActual.setStatus(Status.READY);
				stackRunning.remove(processActual);
				stackRunning.add(processActual);
				processActual=null;
				status=Status.IDLE;
			}
			if (processActual!=null&&processActual.getTimeLife()>0&&processActual.getIO()>0) {
				processActual.setStatus(Status.BLOCKED);
				stackBlock.add(processActual);
				processActual=null;
				status=Status.IDLE;
			}else {
				stackRunning.add(processActual);
				processActual=null;
			}
			if (processActual!=null&&processActual.getQuantum()==0) {
				processActual.reiniciarQuamtum();
			}
			if (processActual!=null&&processActual.getTimeLife()==0) {
				processActual.setStatus(Status.FINNISH);
//				processActual=null;
//				peckProcessActual();
			}
		}
	}

	public ArrayList<String[]> getDataProcess() {
		ArrayList<String[]> dataProcess = new ArrayList<>();
		if (stackRunning.size()>0) {
			for (Process strings : stackRunning) {
				if (strings!=null) {
					dataProcess.add(strings.getdataProcess());
				}
			}
		}
		return dataProcess;
	}

	public double[][] dataCpu() {
		if (dataGraficacion.size()>0) {
			double[][] d = new double[2][dataGraficacion.size()];
			for (int i = 0; i < dataGraficacion.size(); i++) {
				d[0][i]=dataGraficacion.get(i)[0];
				d[1][i]=dataGraficacion.get(i)[1];

			}
			return d;
		}
		return null;
	}

	public ArrayList<String[]> getProcesosBloqueados() {
		ArrayList<String[]> data = new ArrayList<>();
		for (Process strings : stackBlock) {
			data.add(strings.getdataProcess());
		}
		return data;
	}

	public Queue<Process> getProcesos() {
		return stackRunning;
	}


}
