package br.com.rh.util;

import br.com.rh.model.Funcionario;

import java.util.List;

public class MergeSort {

    public static void ordenarPorProducaoDecrescente(List<Funcionario> lista) {
        if (lista == null || lista.size() <= 1) {
            return;
        }
        Funcionario[] array = lista.toArray(new Funcionario[0]);
        mergeSort(array, 0, array.length - 1);
        
        lista.clear();
        for (Funcionario f : array) {
            lista.add(f);
        }
    }

    private static void mergeSort(Funcionario[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;

            mergeSort(array, esquerda, meio);
            mergeSort(array, meio + 1, direita);

            merge(array, esquerda, meio, direita);
        }
    }

    private static void merge(Funcionario[] array, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        Funcionario[] L = new Funcionario[n1];
        Funcionario[] R = new Funcionario[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = esquerda;

        while (i < n1 && j < n2) {
            // Ordenação decrescente: comparamos se a produção do lado Esquerdo é >= a do lado Direito
            if (L[i].getProducao() >= R[j].getProducao()) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
