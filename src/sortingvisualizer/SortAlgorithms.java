package sortingvisualizer;

public class SortAlgorithms {
	// Bubble Sort's position tracker
	private int bubbleI = 0;
	private int bubbleJ = 0;
	private boolean bubbleFinished = false;

	public boolean isBubbleFinished() {
		return bubbleFinished;
	}

	public void resetBubbleSort() {
		bubbleI = 0;
		bubbleJ = 0;
		bubbleFinished = false;
	}

	// Performs ONE comparison (and possibly one swap) of Bubble Sort
	public void bubbleSortStep(ArrayState state) {
		if (bubbleFinished) {
			return;
		}

		int n = state.size();

		if (bubbleI < n - 1) {
			if (bubbleJ < n - bubbleI - 1) {
				state.setComparing(bubbleJ, bubbleJ + 1);

				int[] values = state.getValues();
				if (values[bubbleJ] > values[bubbleJ + 1]) {
					state.swap(bubbleJ, bubbleJ + 1);
				}

				bubbleJ++;
			} else {
				state.markSorted(n - bubbleI - 1);
				bubbleJ = 0;
				bubbleI++;
			}
		} else {
			state.markSorted(0);
			bubbleFinished = true;
		}
	}

	// ---------- Insertion Sort ----------

	private int insertI = 1;
	private int insertJ = 0;
	private boolean insertShifting = false;
	private boolean insertFinished = false;

	public boolean isInsertFinished() {
		return insertFinished;
	}

	public void resetInsertionSort() {
		insertI = 1;
		insertJ = 0;
		insertShifting = false;
		insertFinished = false;
	}

	// Performs ONE comparison (and possibly one swap) of Insertion Sort
	public void insertionSortStep(ArrayState state) {
		if (insertFinished) {
			return;
		}

		int n = state.size();

		if (insertI >= n) {
			insertFinished = true;
			return;
		}

		if (!insertShifting) {
			insertJ = insertI;
			insertShifting = true;
		}

		int[] values = state.getValues();

		if (insertJ > 0) {
			state.setComparing(insertJ - 1, insertJ);

			if (values[insertJ - 1] > values[insertJ]) {
				state.swap(insertJ - 1, insertJ);
				insertJ--;
			} else {
				insertShifting = false;
				insertI++;
				for (int i = 0; i < insertI; i++) {
			        state.markSorted(i);
			    }
			}
		} else {
			insertShifting = false;
			insertI++;
			for (int i = 0; i < insertI; i++) {
		        state.markSorted(i);
		    }
		}
	}
}
