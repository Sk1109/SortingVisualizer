package sortingvisualizer;

public class SortAlgorithms {
	// Bubble Sort's position tracker
	private int bubbleI = 0;
	private int bubbleJ = 0;
	private boolean bubbleFinished = false;
	private boolean bubbleWaitingToSwap = false;

	public boolean isBubbleFinished() {
		return bubbleFinished;
	}

	public void resetBubbleSort() {
		bubbleI = 0;
		bubbleJ = 0;
		bubbleFinished = false;
		bubbleWaitingToSwap = false;

	}

	// Performs ONE comparison (and possibly one swap) of Bubble Sort
	public void bubbleSortStep(ArrayState state) {
		if (bubbleFinished) {
			return;
		}

		int n = state.size();

		if (bubbleI < n - 1) {
			if (bubbleJ < n - bubbleI - 1) {
				int[] values = state.getValues();

				// FIRST TIMER TICK:
				// Highlight the bars only.
				if (!bubbleWaitingToSwap) {
					state.setComparing(bubbleJ, bubbleJ + 1);
					bubbleWaitingToSwap = true;
					return;
				}

				// SECOND TIMER TICK:
				// Perform the comparison/swap.
				bubbleWaitingToSwap = false;

				if (values[bubbleJ] > values[bubbleJ + 1]) {
					state.swap(bubbleJ, bubbleJ + 1);
				}
				state.clearComparing();
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
	private boolean insertWaitingToSwap = false;

	public boolean isInsertFinished() {
		return insertFinished;
	}

	public void resetInsertionSort() {
		insertI = 1;
		insertJ = 0;
		insertShifting = false;
		insertFinished = false;
		insertWaitingToSwap = false;
	}

	// Performs ONE animation step of Insertion Sort
	public void insertionSortStep(ArrayState state) {

		if (insertFinished) {
			return;
		}

		int n = state.size();

		if (insertI >= n) {
			for (int i = 0; i < n; i++) {
				state.markSorted(i);
			}
			state.clearComparing();
			insertFinished = true;
			return;
		}

		if (!insertShifting) {
			insertJ = insertI;
			insertShifting = true;
		}

		int[] values = state.getValues();

		if (insertJ > 0) {

			// FIRST TIMER TICK
			if (!insertWaitingToSwap) {
				state.setComparing(insertJ - 1, insertJ);
				insertWaitingToSwap = true;
				return;
			}

			// SECOND TIMER TICK
			insertWaitingToSwap = false;

			if (values[insertJ - 1] > values[insertJ]) {

				state.swap(insertJ - 1, insertJ);
				state.clearComparing();
				insertJ--;

			} else {

				state.clearComparing();
				insertShifting = false;
				insertI++;

			}

		} else {

			state.clearComparing();
			insertShifting = false;
			insertI++;

		}
	}
}