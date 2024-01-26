/*
 * Autor: Tobias Schuler
 * Datum: 26.01.2024
 * Thema: Einfache Fraktal- Zeichnungen in Java
 * 
*/

package fraktal_programm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Fractal_all extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fraktal-Blatt");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);

            // Menü erstellen und hinzufügen
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("Change Fractal");
            
            JMenuItem leafMenuItem = new JMenuItem("Leaf");
            JMenuItem geometricMenuItem = new JMenuItem("Geometrics");
            JMenuItem treeMenuItem = new JMenuItem("Tree");

            fileMenu.add(leafMenuItem);
            fileMenu.add(geometricMenuItem);
            fileMenu.add(treeMenuItem);

            menuBar.add(fileMenu);
            frame.setJMenuBar(menuBar);

            // Border Layout erstellen
            FractalLeaf fractalLeafPanel = new FractalLeaf();
            FractalGeometrics fractalGeometricsPanel = new FractalGeometrics();
            //FractalTree fractalTreePanel = new FractalTree();
            
            final JPanel[] currentPanel = {fractalLeafPanel}; // Initialisiere mit Leaf-Panel
            frame.add(currentPanel[0], BorderLayout.CENTER);

            // ActionListener Funktionen für die Menüpunkte
            leafMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Change to Leaf Fractal");
                    frame.remove(currentPanel[0]); // Entferne das aktuelle Panel
                    currentPanel[0] = fractalLeafPanel; // Wechsle zum Leaf-Panel
                    frame.add(currentPanel[0], BorderLayout.CENTER); // Füge das neue Panel hinzu
                    frame.revalidate(); // Aktualisiere das Layout
                    frame.repaint(); // Repaint das Fenster
                }
            });

            geometricMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Change to Geometrics Fractal");
                    frame.remove(currentPanel[0]); // Entferne das aktuelle Panel
                    currentPanel[0] = fractalGeometricsPanel; // Wechsle zum Geometrics-Panel
                    frame.add(currentPanel[0], BorderLayout.CENTER); // Füge das neue Panel hinzu
                    frame.revalidate(); // Aktualisiere das Layout
                    frame.repaint(); // Repaint das Fenster
                }
            });

            treeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Change to Fractal Tree");
                    frame.remove(currentPanel[0]); // Entferne das aktuelle Panel
              //      currentPanel[0] = fractalTreePanel; // Wechsle zum Geometrics-Panel
                    frame.add(currentPanel[0], BorderLayout.CENTER); // Füge das neue Panel hinzu
                    frame.revalidate(); // Aktualisiere das Layout
                    frame.repaint(); // Repaint das Fenster
                }
            });

            frame.setVisible(true);
        });
    }
}



class FractalLeaf extends JPanel {
    private double x = 0, y = 0;
    //Variablen für die Bild- Verschiebung
    public double xShift = 0, yShift = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.WHITE);
        g.setColor(Color.green);

        for (int i = 0; i < 200000; i++) {
            
            //Y-Shift noch nicht implementiert !
            g.drawLine((int) ((x + 3.5) * 50), (int) (y * 50), (int) ((x + 3.5) * 50), (int) (y * 50));


            int rand = (int) (Math.random() * 100);

            if (rand <= 85 && rand > 0) {
                double newX = (0.85 * x) + (0.04 * y);
                double newY = (-0.04 * x) + (0.85 * y) + 1.6;
                x = newX;
                y = newY;
            } else if (rand <= 92 && rand > 85) {
                double newX = (0.2 * x) - (0.26 * y);
                double newY = (0.23 * x) + (0.22 * y) + 1.6;
                x = newX;
                y = newY;
            } else if (rand <= 99 && rand > 92) {
                double newX = (-0.15 * x) + (0.28 * y);
                double newY = (0.26 * x) + (0.24 * y) + 0.44;
                x = newX;
                y = newY;
            } else if (rand == 100) {
                double newX = 0;
                double newY = 0.016 * y;
                x = newX;
                y = newY;
                System.out.println(100);
            }
        }

    }
    
}


class FractalGeometrics extends JPanel {
    private double x = 1, y = 1;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Schwarzer Hintergrund, weiße Zeichnung
        setBackground(Color.black);
        g.setColor(Color.white);

        //Iteration 
        for (int i = 0; i < 50000; i++) {
            //Punkt zeichnen
            g.drawLine((int) (x * 200), (int) (y * 200), (int) (x * 200), (int) (y * 200));

            //Zufällige berechnung des nächsten Punktes
            int rand = (int) (Math.random() * 3);
            switch (rand) {
                case 0:
                    x = x / 2.0;
                    y = y / 2.0;
                    break;
                case 1:
                    x = 1.0 / 2.0 + x / 2.0;
                    y = 1.0 / 2.0 + y / 2.0;
                    break;
                case 2:
                    x = 1.0 / 2.0 + x / 2.0;
                    y = y / 2.0;
                    break;
            }
        }
    }
}


