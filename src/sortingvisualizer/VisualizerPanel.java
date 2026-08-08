package sortingvisualizer;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VisualizerPanel extends JPanel {

	private ArrayState state;
	private SortAlgorithms algorithms;
	private Timer timer;
	private String activeSort = "";
	private String selectedAlgorithm = "bubble";

	public VisualizerPanel(ArrayState state, SortAlgorithms algorithms) {
		this.state = state;
		this.algorithms = algorithms;

		timer = new Timer(1000, e -> runStep());

		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPress(e);
			}
		});
	}

	private void handleKeyPress(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_R) {
			regenerateArray();
		} else if (key == KeyEvent.VK_B) {
			selectedAlgorithm = "bubble";
		} else if (key == KeyEvent.VK_I) {
			selectedAlgorithm = "insertion";
		} else if (key == KeyEvent.VK_SPACE) {
			if (selectedAlgorithm.equals("bubble")) {
				startBubbleSort();
			} else {
				startInsertionSort();
			}
		}
	}

	public void startBubbleSort() {
		if (timer.isRunning()) {
			return;
		}
		state.clearSorted();
		activeSort = "bubble";
		algorithms.resetBubbleSort();
		timer.start();
	}

	public void startInsertionSort() {
		if (timer.isRunning()) {
			return;
		}
		state.clearSorted();
		activeSort = "insertion";
		algorithms.resetInsertionSort();
		timer.start();
	}

	public void regenerateArray() {
		timer.stop();
		activeSort = "";
		state.generateRandomArray();
		repaint();
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
			int x = 5 + i * barWidth;
			int y = panelHeight - barHeight;

			if (state.isComparing(i)) {
				g.setColor(Color.RED);
			} else if (state.isSorted(i)) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.BLUE);
			}

			g.fillRect(x, y, barWidth - 5, barHeight);
		}
	}
}