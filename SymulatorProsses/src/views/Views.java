package views;

import java.util.ArrayList;
public interface Views {

	void procesosListos(ArrayList<String[]> procesos);
	void procesosBloquedos(ArrayList<String[]> dataCPU);
	void mostrarProceso();
	void paintDataCPU(ArrayList<String[]> dataCPU,String[] dataProcessActual);
}
