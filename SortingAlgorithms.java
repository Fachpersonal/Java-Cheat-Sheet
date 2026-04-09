package dev.fachpersonal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    static void main() {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    // ===================== UI =====================
    static class MainFrame extends JFrame {

        private final DrawPanel panel;
        private final JSlider speedSlider;
        private JButton pauseButton = null;
        private boolean running = false;

        MainFrame() {

            setTitle("Sortier- und Suchalgorithmen Visualizer");
            setSize(1150, 700);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            panel = new DrawPanel();
            add(panel, BorderLayout.CENTER);

            JPanel controls = new JPanel(new GridLayout(5, 4));

            // Geschwindigkeit
            speedSlider = new JSlider(1, 200, 40);
            speedSlider.addChangeListener(_ -> panel.setDelay(speedSlider.getValue()));
            controls.add(new JLabel("Geschwindigkeit"));
            controls.add(speedSlider);

            // Elementanzahl Input
            JTextField sizeField = new JTextField("60");
            JButton setSizeButton = getSizeButton(sizeField);

            controls.add(new JLabel("Elementanzahl"));
            controls.add(sizeField);
            controls.add(setSizeButton);

            controls.add(button("Reset", _ -> {
                if (!running) panel.reset();
            }));

            pauseButton = button("Pause", _ -> {
                panel.togglePause();
                pauseButton.setText(panel.paused ? "Fortsetzen" : "Pause");
            });
            controls.add(pauseButton);

            controls.add(button("Bubble", _ -> run(() -> BubbleSort.sort(panel), "O(n²)")));
            controls.add(button("Selection", _ -> run(() -> SelectionSort.sort(panel), "O(n²)")));
            controls.add(button("Insertion", _ -> run(() -> InsertionSort.sort(panel), "O(n²)")));
            controls.add(button("Merge", _ -> run(() -> MergeSort.sort(panel), "O(n log n)")));
            controls.add(button("Quick", _ -> run(() -> QuickSort.sort(panel), "Ø O(n log n)")));
            controls.add(button("Linear Search", _ -> run(() -> LinearSearch.search(panel), "O(n)")));
            controls.add(button("Binary Search", _ -> run(() -> BinarySearch.search(panel), "O(log n)")));

            add(controls, BorderLayout.SOUTH);

            JLabel infoLabel = new JLabel("Bereit.");
            add(infoLabel, BorderLayout.NORTH);
            panel.setInfoLabel(infoLabel);

            setVisible(true);
        }

        private JButton getSizeButton(JTextField sizeField) {
            JButton setSizeButton = new JButton("Setze Größe");

            setSizeButton.addActionListener(_ -> {
                if (!running) {
                    try {
                        int size = Integer.parseInt(sizeField.getText());
                        if (size >= 5 && size <= 300) {
                            panel.setArraySize(size);
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Größe zwischen 5 und 300 wählen.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Bitte eine gültige Zahl eingeben.");
                    }
                }
            });
            return setSizeButton;
        }

        private JButton button(String name, java.util.function.Consumer<ActionEvent> action) {
            JButton b = new JButton(name);
            b.addActionListener(action::accept);
            return b;
        }

        private void setControlsEnabled(boolean enabled) {
            for (Component c : ((JPanel) getContentPane().getComponent(1)).getComponents())
                if (c instanceof JButton && c != pauseButton)
                    c.setEnabled(enabled);
        }

        private void run(Runnable algorithm, String complexity) {
            if (running) return;

            running = true;
            setControlsEnabled(false);
            panel.resetStats();
            panel.setComplexity(complexity);

            new Thread(() -> {
                algorithm.run();
                running = false;
                SwingUtilities.invokeLater(() -> setControlsEnabled(true));
            }).start();
        }
    }

    // ===================== DRAW PANEL =====================
    static class DrawPanel extends JPanel {

        int[] array;
        int[] original;

        int current = -1;
        int pivotIndex = -1;
        int foundIndex = -1;
        int targetIndex = -1;
        int leftBound = -1;
        int rightBound = -1;

        int targetValue = -1;

        boolean paused = false;

        long comparisons = 0;
        long swaps = 0;

        int delay = 40;
        Random rand = new Random();

        private JLabel infoLabel;
        private String complexityText = "-";

        DrawPanel() {
            setArraySize(60);
        }

        void setArraySize(int size) {
            array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = rand.nextInt(400) + 10;

            original = Arrays.copyOf(array, array.length);
            resetStats();
        }

        void setInfoLabel(JLabel label) {
            this.infoLabel = label;
        }

        void setComplexity(String text) {
            complexityText = text;
        }

        void reset() {
            array = Arrays.copyOf(original, original.length);
            resetStats();
        }

        void resetStats() {
            comparisons = 0;
            swaps = 0;
            current = -1;
            pivotIndex = -1;
            foundIndex = -1;
            targetIndex = -1;
            leftBound = -1;
            rightBound = -1;
            targetValue = -1;
            repaint();
            updateLabel("Bereit.");
        }

        void togglePause() {
            paused = !paused;
        }

        void setDelay(int d) {
            delay = d;
        }

        void updateLabel(String text) {
            if (infoLabel != null) {
                SwingUtilities.invokeLater(() ->
                        infoLabel.setText(text + " | Komplexität: " + complexityText +
                                " | Vergleiche: " + comparisons +
                                " | Swaps: " + swaps));
            }
        }

        void sleep() {
            repaint();
            try {
                while (paused) Thread.sleep(50);
                Thread.sleep(Math.max(1, delay));
            } catch (InterruptedException ignored) {
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth() / array.length;

            for (int i = 0; i < array.length; i++) {

                if (i == foundIndex)
                    g.setColor(Color.ORANGE);
                else if (i == current)
                    g.setColor(Color.RED);
                else if (i == pivotIndex)
                    g.setColor(Color.MAGENTA);
                else if (i == targetIndex)
                    g.setColor(Color.YELLOW);
                else if (i >= leftBound && i <= rightBound && leftBound != -1)
                    g.setColor(new Color(180, 255, 180));
                else
                    g.setColor(Color.BLUE);

                g.fillRect(i * width, getHeight() - array[i], width - 2, array[i]);
            }
        }
    }

    // ===================== SORT =====================

    static class BubbleSort {
        static void sort(DrawPanel p) {
            for (int i = 0; i < p.array.length; i++) {
                for (int j = 0; j < p.array.length - i - 1; j++) {
                    p.current = j;
                    p.comparisons++;
                    if (p.array[j] > p.array[j + 1]) {
                        int t = p.array[j];
                        p.array[j] = p.array[j + 1];
                        p.array[j + 1] = t;
                        p.swaps++;
                    }
                    p.sleep();
                }
            }
        }
    }

    static class SelectionSort {
        static void sort(DrawPanel p) {
            for (int i = 0; i < p.array.length; i++) {
                int min = i;
                for (int j = i + 1; j < p.array.length; j++) {
                    p.current = j;
                    p.comparisons++;
                    if (p.array[j] < p.array[min])
                        min = j;
                    p.sleep();
                }
                int t = p.array[i];
                p.array[i] = p.array[min];
                p.array[min] = t;
                p.swaps++;
            }
        }
    }

    static class InsertionSort {
        static void sort(DrawPanel p) {
            for (int i = 1; i < p.array.length; i++) {
                int key = p.array[i];
                int j = i - 1;
                while (j >= 0 && p.array[j] > key) {
                    p.current = j;
                    p.comparisons++;
                    p.array[j + 1] = p.array[j];
                    j--;
                    p.swaps++;
                    p.sleep();
                }
                p.array[j + 1] = key;
            }
        }
    }

    static class MergeSort {
        static void sort(DrawPanel p) {
            mergeSort(p, 0, p.array.length - 1);
        }

        static void mergeSort(DrawPanel p, int l, int r) {
            if (l >= r) return;
            int m = (l + r) / 2;
            mergeSort(p, l, m);
            mergeSort(p, m + 1, r);
            merge(p, l, m, r);
        }

        static void merge(DrawPanel p, int l, int m, int r) {
            int[] temp = Arrays.copyOfRange(p.array, l, r + 1);
            int i = 0, j = m - l + 1, k = l;

            while (i <= m - l && j <= r - l) {
                p.current = k;
                p.comparisons++;
                if (temp[i] <= temp[j])
                    p.array[k++] = temp[i++];
                else
                    p.array[k++] = temp[j++];
                p.swaps++;
                p.sleep();
            }
            while (i <= m - l)
                p.array[k++] = temp[i++];
        }
    }

    static class QuickSort {
        static void sort(DrawPanel p) {
            quick(p, 0, p.array.length - 1);
        }

        static void quick(DrawPanel p, int low, int high) {
            if (low < high) {
                int pi = partition(p, low, high);
                quick(p, low, pi - 1);
                quick(p, pi + 1, high);
            }
        }

        static int partition(DrawPanel p, int low, int high) {
            int pivot = p.array[high];
            p.pivotIndex = high;

            int i = low - 1;
            for (int j = low; j < high; j++) {
                p.current = j;
                p.comparisons++;
                if (p.array[j] < pivot) {
                    i++;
                    int t = p.array[i];
                    p.array[i] = p.array[j];
                    p.array[j] = t;
                    p.swaps++;
                }
                p.sleep();
            }
            int t = p.array[i + 1];
            p.array[i + 1] = p.array[high];
            p.array[high] = t;
            p.swaps++;
            return i + 1;
        }
    }

    // ===================== SEARCH =====================

    static class LinearSearch {
        static void search(DrawPanel p) {

            p.targetIndex = p.rand.nextInt(p.array.length);
            p.targetValue = p.array[p.targetIndex];

            p.updateLabel("Lineare Suche nach Wert: " + p.targetValue);

            for (int i = 0; i < p.array.length; i++) {
                p.current = i;
                p.comparisons++;
                p.sleep();

                if (p.array[i] == p.targetValue) {
                    p.foundIndex = i;
                    break;
                }
            }
        }
    }

    static class BinarySearch {
        static void search(DrawPanel p) {

            Arrays.sort(p.array);

            p.targetIndex = p.rand.nextInt(p.array.length);
            p.targetValue = p.array[p.targetIndex];

            p.updateLabel("Binäre Suche nach Wert: " + p.targetValue);

            int left = 0;
            int right = p.array.length - 1;

            while (left <= right) {

                p.leftBound = left;
                p.rightBound = right;

                int mid = (left + right) / 2;
                p.current = mid;
                p.comparisons++;
                p.sleep();

                if (p.array[mid] == p.targetValue) {
                    p.foundIndex = mid;
                    break;
                }
                if (p.array[mid] < p.targetValue)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
    }
}
