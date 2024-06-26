package algortimosdeordenacion;

import java.util.Random;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_RAPIDA = 4;
	public static final int METODO_CLASIFICACION_HEAPSORT = 5;
	public static final int METODO_CLASIFICACION_ESTUPIDA= 420;


	/**
	 * Punto de entrada al clasificador
	 * 
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
		case METODO_CLASIFICACION_INSERCION:
			return ordenarPorInsercion(datosParaClasificar);
		case METODO_CLASIFICACION_SHELL:
			return ordenarPorShell(datosParaClasificar);
		case METODO_CLASIFICACION_BURBUJA:
			return ordenarPorBurbuja(datosParaClasificar);
		case METODO_CLASIFICACION_RAPIDA:
			return ordenarPorQuickSort(datosParaClasificar, 0, datosParaClasificar.length - 1);
		case METODO_CLASIFICACION_HEAPSORT:
			return ordenarPorHeapSort(datosParaClasificar);
		case METODO_CLASIFICACION_ESTUPIDA:
			return ordenarPorBogosort(datosParaClasificar);
		default:
			System.err.println("Este codigo no deberia haberse ejecutado");
			break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}



	/**
	 * @param datosParaClasificar
	 * @return
	 */
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j, inc;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };
	
		for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];
			if (inc < datosParaClasificar.length) {
				for (int i = inc; i < datosParaClasificar.length; i++) {
					j = i;
					while (j >= inc && datosParaClasificar[j - inc] > /*< */ datosParaClasificar[j]) {
						intercambiar(datosParaClasificar, j, j - inc);
						j -= inc;
					}
				}
			}
		}
		return datosParaClasificar;
	}


	/**
	 * @param datosParaClasificar
	 * @return
	 */
	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int j = i - 1;
				while ((j >= 0) && (datosParaClasificar[j] > datosParaClasificar[j+1])) {
					intercambiar(datosParaClasificar, j, j + 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		int n = datosParaClasificar.length - 1;
		for (int i = 0; i <= n; i++) {
			for (int j = n; j >= (i + 1); j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
				}
			}
		}
		return datosParaClasificar;
	}

	private int obtenerClavePivote(int[] vector, int pos1, int pos2){
		int[] vectorMediana = new int[3];
		vectorMediana[0] = vector[pos1];
		vectorMediana[1] = vector[(pos1 + pos2) / 2];
		vectorMediana[2] = vector[pos2];
		return ordenarPorInsercion(vectorMediana)[1];
	}

	private int particion(int[] vector, int i, int j, int pivote){
		int izquierda = i;
		int derecha = j;
		while (izquierda <= derecha){
			while (vector[izquierda] < /*> */ pivote){
				izquierda++;
			}
			while (vector[derecha] > /*< */pivote){
				derecha--;
			}
			if (izquierda <= derecha){
				intercambiar(vector, izquierda, derecha);
				izquierda++;
				derecha--;
			}
		}
		return izquierda;
	}

	private int[] ordenarPorQuickSort(int[] datosParaClasificar, int i, int j) {
		if (i < j) {
			int pivote = obtenerClavePivote(datosParaClasificar, i, j);
			int k = particion(datosParaClasificar, i, j, pivote);
			if (i < k - 1){
				ordenarPorQuickSort(datosParaClasificar, i, k - 1);
			}
			if (k < j){
				ordenarPorQuickSort(datosParaClasificar, k, j);
			}
		}
		return datosParaClasificar;
		
	}

	protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		// Construir el heap
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { 
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
		// Extraer elementos del heap uno por uno
		for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
			intercambiar(datosParaClasificar, 0, i);
			armaHeap(datosParaClasificar, 0, i - 1);
		}
		return datosParaClasificar;
	}
	
	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		int r = primero;
		while (2 * r + 1 <= ultimo) { // Mientras el nodo tenga al menos un hijo
			int hijoIzquierdo = 2 * r + 1;
			int hijoDerecho = 2 * r + 2;
			int maximo = r;
	
			// Encontrar el mayor de los tres: nodo, hijo izquierdo, hijo derecho
			if (hijoIzquierdo <= ultimo && datosParaClasificar[hijoIzquierdo] > datosParaClasificar[maximo]) {
				maximo = hijoIzquierdo;
			}
			if (hijoDerecho <= ultimo && datosParaClasificar[hijoDerecho] > datosParaClasificar[maximo]) {
				maximo = hijoDerecho;
			}
	
			// Si el mayor no es el nodo actual, intercambiar y seguir bajando
			if (maximo != r) {
				intercambiar(datosParaClasificar, r, maximo);
				r = maximo;
			} else {
				break;
			}
		}
	}




	protected int[] ordenarPorBogosort(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			Random random = new Random();
			
			GeneradorDatosGenericos generadorDatosGenericos = new GeneradorDatosGenericos();
			while (!generadorDatosGenericos.estaOrdenado(datosParaClasificar)) {
				mezclar(datosParaClasificar, random);
			}
			return datosParaClasificar;
		}
		return null;
	}

	private void mezclar(int[] array, Random random) {
		for (int i = 0; i < array.length; i++) {
			int randomIndex = random.nextInt(array.length);
			intercambiar(array, i, randomIndex);
		}
	}


	public static void main(String args[]) {
		TClasificador clasif = new TClasificador();
		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		int[] vectorAscendente = gdg.generarDatosAscendentes();
		int[] vectorDescendente = gdg.generarDatosDescendentes();

		int[] resAleatorio = clasif.clasificar(vectorAleatorio,
				METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resAleatorio.length; i++) {
			System.out.print(resAleatorio[i] + " ");
		}
		int[] resAscendente = clasif.clasificar(vectorAscendente,
				METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resAscendente.length; i++) {
			System.out.print(resAscendente[i] + " ");
		}
		int[] resDescendente = clasif.clasificar(vectorDescendente,
				METODO_CLASIFICACION_INSERCION);
		for (int i = 0; i < resDescendente.length; i++) {
			System.out.print(resDescendente[i] + " ");
		}
	}
}
