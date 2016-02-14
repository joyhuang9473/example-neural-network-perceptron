package neural_network_perceptron;

import javax.swing.JFrame;

public class Main {
    public static Framework frame = new Framework();

    public static void main(String[] args) {
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Neural Network Perceptron");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}