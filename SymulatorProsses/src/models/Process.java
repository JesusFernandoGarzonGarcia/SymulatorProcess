package models;

public class Process {
	private String id;
	private int timeLife;
	private int timeInicial;
	private int nextIO;
	private int nextIOInicial;
	private int IO;
	private int IOInicial;
	private int quantum;
	private int quantumInicial;
	private Status status;

	public Process(String id, int timeLife, int nextIO, int iO, int quantum,Status status) {
		this.id = id;
		this.timeLife = timeLife;
		this.nextIO = nextIO;
		this.nextIOInicial = nextIO;
		this.IO =iO;
		this.IOInicial=IO;
		this.quantum = quantum;
		this.quantumInicial = quantum;
		this.status=status;
		this.timeInicial=timeLife;
	}

	public void deliteQuamtum() {
		if (quantum>0) {
			quantum--;
		}
	}


	public boolean deliteNexIO() {
		boolean state =false;
		if (nextIO>0&&getStatus().equals(Status.RUNNING)) {
			nextIO--;
		}else {
			status =Status.BLOCKED;
			state = true;
		}
		return state;
	}

	public boolean deleteTrabajoIO() {
		boolean state =false;
		if (IO>0&&getStatus().equals(Status.BLOCKED)) {
			IO--;
		}else {
			status=Status.READY;
			state= true;
		}
		return state;
	}

	public boolean deliteLive() {
		boolean state =false;
		if(timeLife>0&&getStatus().equals(Status.RUNNING)) {
			timeLife--;
			state= true;
		}else {
			status =Status.FINNISH;
			state= false;
		}
		return state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public int getTimeLife() {
		return timeLife;
	}

	public void setTimeLife(int timeLife) {
		this.timeLife = timeLife;
	}

	public int getNextIO() {
		return nextIO;
	}

	public void setNextIO(int nextIO) {
		this.nextIO = nextIO;
	}

	public int getIO() {
		return IO;
	}

	public void setIO(int iO) {
		IO = iO;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public void reiniciarQuamtum() {
		this.quantum=this.quantumInicial;
	}


	public String[] getdataProcess() {
		return new String[] {id,timeLife+"/"+timeInicial,nextIO+"/"+nextIOInicial, IO+"/"+IOInicial ,quantum+"/"+quantumInicial,status.toString()}; 
	}

	@Override
	public String toString() {
		return super.toString();
	}


}
