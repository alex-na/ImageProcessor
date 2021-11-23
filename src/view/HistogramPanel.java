package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.max;

public final class HistogramPanel extends JPanel {

    private final int height = 400;
    private final int width = 500;
    private final int unitY; //height / maxFrequency
    private final int unitX = width / 255;
    private final ArrayList<Line> lines = new ArrayList<Line>();

    public HistogramPanel(List<List<Integer>> frequencies){
        setupPanel();
        int maxFreq = 0;

        for (List<Integer> l : frequencies) {
            for (Integer i : l) {
                maxFreq = max(maxFreq, i);
            }
        }
       this.unitY = height / maxFreq;

        for (List<Integer> l : frequencies) {
            for (int i = 0; i < 254; i++) {
                addLine(i * unitX, l.get(i) * unitY,(i + 1) * unitX,l.get(i + 1) * unitY);
            }
        }
    }

    public void addLine(int xFrom, int yFrom, int xTo, int yTo) {
        this.lines.add(new Line(xFrom, yFrom, xTo, yTo));
    }

    private void setupPanel() {
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics g) {

        if (lines.size() > 254) {
            int i = 0;
            int j = 0;
            List<Color> lineColors = new ArrayList<>(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA));
            g.setColor(lineColors.get(i));
            for (Line l : lines) {
                l.paint(g);
                j++;
                if (j >= 254) {
                    j = 0;
                    i++;
                    g.setColor(lineColors.get(i));
                }
            }
        }
        else {
            g.setColor(Color.magenta);
            for(Line l : lines) {
                l.paint(g);
            }
        }
    }

    public static class Line {
        public final int xFrom;
        public final int yTo;
        public final int xTo;
        public final int yFrom;

        public Line(int xFrom, int yFrom, int xTo, int yTo) {
            this.xFrom = xFrom;
            this.xTo = xTo;
            this.yFrom = yFrom;
            this.yTo = yTo;
        }

        public void paint(Graphics g) {
            g.drawLine(this.xFrom, this.yFrom, this.xTo, this.yTo);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,300);
    }
}