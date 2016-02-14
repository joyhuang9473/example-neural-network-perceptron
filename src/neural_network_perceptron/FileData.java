package neural_network_perceptron;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import javax.swing.JFileChooser;

public class FileData {
    protected LinkedList<LinkedList> data_set = new LinkedList<LinkedList>();
    protected LinkedList<Integer> crowds = new LinkedList<Integer>();
    
    protected LinkedList<Integer> cluster = new LinkedList<Integer>();
    
    public FileData() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();

            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                LinkedList<Double> data = new LinkedList<Double>();

                String row_entries = input.nextLine();
                String[] entries = row_entries.split("\t");
                int dimension = 0;

                while (dimension < entries.length-1) {
                    data.add(Double.parseDouble(entries[dimension]));
                    ++dimension;
                }

                int crowd = Integer.parseInt(entries[entries.length-1]);

                if (!cluster.contains(crowd)) cluster.add(crowd);

                crowds.add(crowd);
                data_set.add(data);
            }
            
            input.close();
        } else {
            System.out.println("No File selected");
        }
    }

    public LinkedList<LinkedList> getDataSet() {
        return data_set;
    }

    public LinkedList<Integer> getCrowds() {
        return crowds;
    }
    
    public LinkedList<Integer> getCluster() {
        return cluster;
    }
}
