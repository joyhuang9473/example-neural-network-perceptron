package neural_network_perceptron;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Framework extends JFrame {

    public JPanel inputPanel = null;
    public JTextField jt_learningRate = new JTextField(8);
    public JTextField jt_learningCount = new JTextField(8);
    
    public JButton jb_select_file = new JButton("選擇檔案");
    public JButton jb_execute = new JButton("執行");

    private FileData read_file = null;
    private Perceptron perceptron = null;
    private Coordinate graphPanel = new Coordinate();

    public JPanel resultPanel = null;
    public JLabel correctRateOfTraining = new JLabel("(執行後顯示)");
    public JLabel correctRateOfTesting = new JLabel("(執行後顯示)");
    
    public Framework() {
        setLayout(null);

        inputPanel = setInputPanel();
        resultPanel = setResultPanel();
        
        graphPanel.setBounds(0, 0, 600, 600);
        inputPanel.setBounds(600, 0, 200, 400);
        resultPanel.setBounds(600, 400, 200, 200);
        
        jb_select_file.addActionListener(new CustomActionListener());
        jb_execute.addActionListener(new CustomActionListener());

        add(graphPanel);
        add(inputPanel);
        add(resultPanel);
    }

    protected JPanel setGraphPanel() {
        JPanel panel = new JPanel();
        Border line = new LineBorder(Color.BLACK, 1);

        panel.add(new JLabel("圖形:"));
        panel.setBorder(line);
        
        return panel;
    }

    protected JPanel setInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

        panel.add(jb_select_file);
        panel.add(new JLabel("Input file"));
        panel.add(new JLabel("學習率:"));
        panel.add(jt_learningRate);
        panel.add(new JLabel("學習次數:"));
        panel.add(jt_learningCount);
        panel.add(jb_execute);
        panel.add(new JLabel("(欄位空白為套用預設值)"));

        return panel;
    }

    protected JPanel setResultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

        panel.add(new JLabel("訓練正確率:"));
        panel.add(correctRateOfTraining);
        panel.add(new JLabel("測試正確率:"));
        panel.add(correctRateOfTesting);

        return panel;    
    }
    
    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb_select_file) {
                try {
                    read_file = new FileData();
                    perceptron = new Perceptron(read_file.getDataSet(), read_file.getCrowds(), read_file.getCluster());
                    graphPanel.setCollection(perceptron);

                    graphPanel.setType(Coordinate.TYPE_BEFORE_TRAINING);
                    graphPanel.draw();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == jb_execute) {
                if (read_file == null) {
                    JOptionPane.showMessageDialog(null,"請選擇檔案");
                    return;
                }

                if (!jt_learningRate.getText().isEmpty()) {
                    perceptron.setLearningRate(Double.parseDouble(jt_learningRate.getText()));
                }
                if (!jt_learningCount.getText().isEmpty()) {
                    perceptron.setLearningCount(Integer.parseInt(jt_learningCount.getText()));
                }
                
                perceptron.execute();
                correctRateOfTraining.setText(perceptron.getCorrectRateForTraining() + "");
                correctRateOfTesting.setText(perceptron.getCorrectRateForTesting() + "");
                graphPanel.setType(Coordinate.TYPE_AFTER_TRAINING);
                graphPanel.draw();
            }
        }
    }

}
