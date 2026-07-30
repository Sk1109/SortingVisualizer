package sortingvisualizer;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;

public class VisualizerPanel extends JPanel {

	private ArrayState state;
	private SortAlgorithms algorithms;
	private Timer timer;
	private String activeSort = "";

	public VisualizerPanel(ArrayState state, SortAlgorithms algorithms) {
		this.state = state;
		this.algorithms = algorithms;

		timer = new Timer(20, e -> runStep());
	}

	private void runStep() {
		if (activeSort.equals("bubble")) {
			if (!algorithms.isBubbleFinished()) {
				algorithms.bubbleSortStep(state);
			} else {
				timer.stop();
			}
		} else if (activeSort.equals("insertion")) {
			if (!algorithms.isInsertFinished()) {
				algorithms.insertionSortStep(state);
			} else {
				timer.stop();
			}
		}
		repaint();
	}

	public void startBubbleSort() {
		activeSort = "bubble";
		algorithms.resetBubbleSort();
		timer.start();
	}

	public void startInsertionSort() {
		activeSort = "insertion";
		algorithms.resetInsertionSort();
		timer.start();
	}

	public void regenerateArray() {
		timer.stop();
		state.generateRandomArray();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int[] values = state.getValues();
		int n = values.length;

		int panelWidth = getWidth();
		int panelHeight = getHeight();
		int barWidth = panelWidth / n;

		for (int i = 0; i < n; i++) {
			int barHeight = values[i];
			int x = i * barWidth;
			int y = panelHeight - barHeight;

			if (state.isSorted(i)) {
				g.setColor(Color.GREEN);
			} else if (state.isComparing(i)) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLUE);
			}

			g.fillRect(x, y, barWidth - 2, barHeight);
		}
	}
}
