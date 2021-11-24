package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Histogram extends JPanel {

    private final int height = 300;
    private final int width = 400;
    private final int unitY; //height / maxFrequency
    private final int unitX = width / 255;
    private final int maxFreq;
    private final List<List<Line>> lineLists = new ArrayList<>();

    public Histogram(List<List<Integer>> frequencies) {

        super.setBackground(Color.LIGHT_GRAY);
        super.setPreferredSize(new Dimension(400, 300));

        int maxFreq = 0;

        for (List<Integer> list : frequencies) {
            for (Integer i : list) {
                maxFreq = Math.max(maxFreq, i);
            }
        }
        this.maxFreq = maxFreq;
        this.unitY = height / this.maxFreq;

        for (List<Integer> list : frequencies) {
            List<Line> newList = new ArrayList<>();
            for (int i = 0; i < 255; i++) {
                newList.add(new Line(i * unitX, list.get(i) * unitY,
                        (i + 1) * unitX, list.get(i + 1) * unitY));
            }
            lineLists.add(newList);
        }
    }

    public void paintComponent(Graphics g) {
        List<Color> lineColors = new ArrayList<>(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA));
        for (int i = 0; i < lineLists.size(); i++) {
            g.setColor(lineColors.get(i));
            for (Line l : lineLists.get(i)) {
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
        return new Dimension(400, 300);
    }
}