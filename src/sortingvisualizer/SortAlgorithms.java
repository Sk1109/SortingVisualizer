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
}
