package sortingvisualizer;

public class ArrayState {
	private int[] values;
	private boolean[] sorted;
	private int comparingIndex1 = -1;
	private int comparingIndex2 = -1;

	public ArrayState(int size) {
		values = new int[size];
		sorted = new boolean[size];
		generateRandomArray();
	}

	public void generateRandomArray() {
		for (int i = 0; i < values.length; i++) {
			values[i] = (int) (Math.random() * 300) + 20;
			sorted[i] = false;
		}
		comparingIndex1 = -1;
		comparingIndex2 = -1;
	}

	public void clearSorted() {
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = false;
		}
	}

	public int[] getValues() {
		return values;
	}

	public int size() {
		return values.length;
	}

	public void setComparing(int i, int j) {
		comparingIndex1 = i;
		comparingIndex2 = j;
	}

	public boolean isComparing(int index) {
		return index == comparingIndex1 || index == comparingIndex2;
	}

	public void markSorted(int index) {
		sorted[index] = true;
	}

	public boolean isSorted(int index) {
		return sorted[index];
	}

	public void swap(int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
}
