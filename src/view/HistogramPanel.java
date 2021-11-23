package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.max;

public final class HistogramPanel extends JLayeredPane {

    public HistogramPanel(List<List<Integer>> frequencies) {
        super();
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.LIGHT_GRAY);

        int maxFreq = 0;
        for (List<Integer> l : frequencies) {
            for (Integer i : l) {
                maxFreq = max(maxFreq, i);
            }
        }

        for(List<Integer> l : frequencies) {
            this.add(new Histogram(l, maxFreq));
        }


    }

    public void paintComponent(Graphics g) {
        List<Color> lineColors = new ArrayList<>(Arrays.asList(Color.MAGENTA, Color.RED, Color.GREEN, Color.BLUE));
        for (int i = 0; i < this.getComponentCount(); i++) {
            g.setColor(lineColors.get(i));
            getComponent(i).paint(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,300);
    }
}