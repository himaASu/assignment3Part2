package com.svvt.assignment3;

public class MergeSortAnomaly {

	public static void main(String[] args) {
		int[] arr = { 12, 11, 13, 5, 6, 7 };
		mergeSort(arr, 0, arr.length - 1);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;

			// Data Flow Anomaly 1: Unused variable
			int z = 10;

			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int i = 0; i < n1; i++) {
			L[i] = arr[l + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = arr[m + 1 + j];
		}

		int i = 0, j = 0;
		int k = l;

		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// Data Flow Anomaly 2: This loop is redundant
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}

		// Data Flow Anomaly 3: Unreachable code
		if (false) {
			System.out.println("This line will never be executed.");
		}
	}
}
