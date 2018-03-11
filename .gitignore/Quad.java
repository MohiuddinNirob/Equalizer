package quad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static java.lang.Math.*;

public class Quad extends JFrame {

    private static final int N = 700;
    private static final int A = 0;
    private static final int B = 0;
    private static final int C = 0;
    private static final int D = 0;
    private static double[] getTicks(double k){
         double increment = (k*5), currentTick = -1*(increment*5);
         double[] tick = new double[9];
         for(int i = 0; i < 9; i++){
         currentTick+=increment;
         tick[i] = Math.round(currentTick*100.0)/100.0;
         }
         return tick;
    }
    public static void main(String[] args) {
        new Quad();
    }

    public Quad() {
        JFrame frame = new JFrame("Graph It!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new QuadraticPanel());
        frame.pack();
        frame.setVisible(true);
    }

    private static class QuadraticPanel extends JPanel {

        private JButton but,but1;
        private JTextField t1,t2;
        private Box controls;
        private JPanel graphPanel;
        private JSlider aSlider, bSlider, cSlider, dSlider;
        private JLabel aLabel, bLabel, cLabel, dLabel, fLabel, kLabel, mLabel, nLabel;
        double a, b, c, d, x, y, k;
        int p;
        private String s,s1;

        public QuadraticPanel() {
            t1 = new JTextField(20);
            but = new JButton("OK");
            
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    s = t1.getText();
                    if (s.equals("ax^2+bx+c")) {
                        p = 1;
                    } else if (s.equals("c*sin^d(ax)+b")) {
                        p = 2;
                    } else if (s.equals("c*cos^d(ax)+b")) {
                        p = 3;
                    } else if (s.equals("c*tan^d(ax)+b")) {
                        p = 4;
                    }
                    else if (s.equals("ax^3+bx^2+cx+d")) {
                        p = 5;
                    }
                    else if (s.equals("c*cosec^d(ax)+b")) {
                        p = 6;
                    }
                    else if (s.equals("c*sec^d(ax)+b")) {
                        p = 7;
                    }
                    else if (s.equals("c*cot^d(ax)+b")) {
                        p = 8;
                    }
                     
                }
            });
            
            t2 = new JTextField(20);
            but1 = new JButton("OK");
            
            but1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    s1 = t2.getText();
                    k= Double.parseDouble(s1);
                }
            });
            
            aSlider = new JSlider(JSlider.HORIZONTAL, -25, 25, A);
            aSlider.setMajorTickSpacing(10);
            aSlider.setMinorTickSpacing(5);
            aSlider.setPaintTicks(true);
            aSlider.setPaintLabels(true);
            aSlider.setAlignmentX(Component.LEFT_ALIGNMENT);

            bSlider = new JSlider(JSlider.HORIZONTAL, -10, 10, B);
            bSlider.setMajorTickSpacing(5);
            bSlider.setMinorTickSpacing(1);
            bSlider.setPaintTicks(true);
            bSlider.setPaintLabels(true);
            bSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            cSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, C);
            cSlider.setMajorTickSpacing(50);
            cSlider.setMinorTickSpacing(10);
            cSlider.setPaintTicks(true);
            cSlider.setPaintLabels(true);
            cSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            dSlider = new JSlider(JSlider.HORIZONTAL, -25, 25, D);
            dSlider.setMajorTickSpacing(10);
            dSlider.setMinorTickSpacing(5);
            dSlider.setPaintTicks(true);
            dSlider.setPaintLabels(true);
            dSlider.setAlignmentX(Component.LEFT_ALIGNMENT);

            SliderListener listener = new SliderListener();
            aSlider.addChangeListener(listener);
            bSlider.addChangeListener(listener);
            cSlider.addChangeListener(listener);
            dSlider.addChangeListener(listener);
            
            mLabel = new JLabel("For normal: ax^3+bx^2+cx+d");
            nLabel = new JLabel("For trigonometric: c*sin^d(ax)+b");
            fLabel = new JLabel("f(x)=");
            kLabel = new JLabel("Enter unit per division:");
            aLabel = new JLabel("a: 0");
            bLabel = new JLabel("b: 0");
            cLabel = new JLabel("c: 0");
            dLabel = new JLabel("d: 0");
            controls = new Box(BoxLayout.Y_AXIS);
            controls.add(mLabel);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(nLabel);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(fLabel);
            controls.add(t1);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(but);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(kLabel);
            controls.add(t2);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(but1);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(aLabel);
            controls.add(aSlider);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(bLabel);
            controls.add(bSlider);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(cLabel);
            controls.add(cSlider);
            controls.add(Box.createRigidArea(new Dimension(0, 20)));
            controls.add(dLabel);
            controls.add(dSlider);

            graphPanel = new JPanel() {
                private static final int SCALE = 5;

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(N, N);
                }

                @Override
                public void paintComponent(Graphics g) {
                    
                    super.paintComponent(g);
                    
                    double[] tick = getTicks(k);
                    int x1 = N / 10, y1 = N / 2, vA = 8;
                    //g.setFont(new Font("Sansserif", Font.PLAIN, N/30));
                    
                    for (int i = 0; i < 9; i++) {
                        g.drawLine(x1, y1 + 5, x1, y1 - 5);
                        if (i != 4 && vA != 4) {
                            if (i > 4) {
                                g.drawString(tick[i] + "", x1 - N / 40, y1 + N/ 21);
                            } else {
                                g.drawString(tick[i] + "", x1 - N / 30, y1 + N / 21);
                            }
                            if (vA > 4) {
                                g.drawString(tick[vA] + "", y1 - N / 13, x1 + N / 60);
                            } else {
                                g.drawString(tick[vA] + "", y1 - N / 12, x1 + N / 60);
                            }
                        }
                        
                        g.drawLine(y1 + 5, x1, y1 - 5, x1);
                        x1 += N / 10;
                        vA--;

                        g.setColor(Color.RED);
                    }
                   
                    g.drawLine(0, 350, 700, 350);
                    g.drawLine(350, 0, 350, 700);
                    
                    if (p == 1) {
                        for (double x = -350; x <= 350; x = x + 0.1) {
                            y = a * x * x + b * x + c;
                            int w = (int) (x * SCALE) + N / 2;
                            int h = (int) (-y * SCALE) + N / 2;
                            g.setColor(Color.blue);
                            g.fillOval(w, h, 5, 5);
                        }
                    } 
                    
                    else if (p == 2) {
                        for (double x = -450; x <= 450; x = x + 0.1) {
                            double y = c * pow(sin(a * x * (3.1415926) / 180),d) + b;
                            int w1 = (int) y;
                            int h1 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h1, 350 - w1, 350 + h1, 350 - w1);
                        }
                    } 
                    
                    else if (p == 3) {
                        for (double x = -350; x <= 350; x = x + 0.1) {
                            double y = c * pow(cos(a * x * (3.1415926) / 180),d) + b;
                            int w2 = (int) y;
                            int h2 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h2, 350 - w2, 350 + h2, 350 - w2);
                        }
                    } 
                    
                    else if (p == 4) {
                        for (double x = -450; x <= 450; x = x + 0.1) {
                            double y = c * pow(tan(a * x * (3.1415926) / 180),d) + b;
                            int w2 = (int) y;
                            int h2 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h2, 350 - w2, 350 + h2, 350 - w2);
                        }
                    }
                    
                    else if (p == 5) {
                        for (double x = -550; x <= 550; x = x + 0.1) {
                            y = a * x * x *x + b * x * x + c * x + d;
                            int w = (int) (x * SCALE) + N / 2;
                            int h = (int) (-y * SCALE) + N / 2;
                            g.setColor(Color.blue);
                            g.fillOval(w, h, 5, 5);
                        }
                    }
                    else if (p == 6) {
                        for (double x = -450; x <= 450; x = x + 0.1) {
                            double y = c * pow(1/(sin(a * x * (3.1415926) / 180)),d) + b;
                            int w1 = (int) y;
                            int h1 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h1, 350 - w1, 350 + h1, 350 - w1);
                        }
                    }
                    else if (p == 7) {
                        for (double x = -450; x <= 450; x = x + 0.1) {
                            double y = c * pow(1/(cos(a * x * (3.1415926) / 180)),d) + b;
                            int w1 = (int) y;
                            int h1 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h1, 350 - w1, 350 + h1, 350 - w1);
                        }
                    }
                    else if (p == 8) {
                        for (double x = -450; x <= 450; x = x + 0.1) {
                            double y = c * pow(1/(tan(a * x * (3.1415926) / 180)),d) + b;
                            int w1 = (int) y;
                            int h1 = (int) x;
                            g.setColor(Color.blue);
                            g.drawLine(350 + h1, 350 - w1, 350 + h1, 350 - w1);
                        }
                    }
                     
                }

            };
            
            graphPanel.setBackground(Color.YELLOW);

            add(controls);
            add(graphPanel);
            listener.stateChanged(null);
        }
        

        class SliderListener implements ChangeListener {

            @Override
            public void stateChanged(ChangeEvent event) {
                a = aSlider.getValue() / 5d;
                b = bSlider.getValue();
                c = cSlider.getValue();
                d = dSlider.getValue();

                aLabel.setText("a: " + a);
                bLabel.setText("b: " + b);
                cLabel.setText("c: " + c);
                dLabel.setText("d: " + d);

                repaint();
            }
        }
    }
}
