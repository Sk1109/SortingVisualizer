package sortingvisualizer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class VisualizerPanel extends JPanel {
	private ArrayState state;

	public VisualizerPanel(ArrayState state) {
		this.state = state;
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
