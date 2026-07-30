package sortingvisualizer;

import javax.swing.JFrame;

public class VisualizerFrame extends JFrame{
	public VisualizerFrame() {
        setTitle("Sorting Visualizer");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ArrayState state = new ArrayState(50);
        SortAlgorithms algorithms = new SortAlgorithms();
        VisualizerPanel panel = new VisualizerPanel(state, algorithms);

        add(panel);
    }
}
