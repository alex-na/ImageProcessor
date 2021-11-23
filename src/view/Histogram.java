package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Histogram extends JPanel {

    private final int height = 400;
    private final int width = 500;
    private final int unitY; //height / maxFrequency
    private final int unitX = width / 255;
    private final ArrayList<Line> lines = new ArrayList<Line>();

    public Histogram(List<Integer> frequencies, int maxFreq) {
        super();
        super.setPreferredSize(new Dimension(500, 400));

        this.unitY = height / maxFreq;
        for (int i = 0; i < 255; i++) {
            addLine(i * unitX, frequencies.get(i) * unitY, (i + 1) * unitX, frequencies.get(i + 1) * unitY);
        }
    }


    public void addLine(int xFrom, int yFrom, int xTo, int yTo) {
        this.lines.add(new Line(xFrom, yFrom, xTo, yTo));
    }

    public void paintComponent(Graphics g) {
        for (Line l : lines) {
            l.paint(g);
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