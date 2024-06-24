package com.example;

import java.util.Random;

public class TClasificador {
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;

    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

/*     protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = posicionPivote;
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote >= entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                intercambiar(entrada, derecha, izquierda);
                izquierda++;
                derecha--;
            }

            if (i > derecha)
                quicksort(entrada, i, derecha);
            if (j < izquierda)
                quicksort(entrada, izquierda, j);
        }
    }
 */
    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        int[] prof = { 0 };
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1, prof);
        System.out.println(prof[0]);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j, int[] profundidad) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            // int pivote = posicionPivote;
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    // izquierda--;
                    izquierda++;
                }

                // while ((pivote < entrada[derecha]) && (derecha > i)) {
                while ((pivote <= entrada[derecha]) && (derecha > i)) {
                    // derecha++;
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            boolean flag = false;
            if (i < derecha) {
                quicksort(entrada, i, izquierda, profundidad);
                flag = true;
            }
            if (izquierda < j) {
                // quicksort(entrada, derecha, j);
                quicksort(entrada, izquierda++, j, profundidad);
                flag = true;
            }
            if (flag) {
                profundidad[0]++;
            }
        }
    }

	public int encuentraPivote(int izq, int der, int[] entrada) {
		Random r = new Random();
		return r.nextInt(der - izq + 1) + izq;
	}

    private int[] intercambiar(int[] entrada, int derecha, int izquierda) {
        int x = entrada[derecha];
        entrada[derecha] = entrada[izquierda];
        entrada[izquierda] = x;
        return entrada;

    }
}
