package neural_network_perceptron;

import java.util.LinkedList;

public class Perceptron {

    // data set
    protected LinkedList<LinkedList> dataSet = new LinkedList<LinkedList>();
    protected LinkedList<Integer> crowds = new LinkedList<Integer>();
    protected LinkedList<Integer> cluster = new LinkedList<Integer>();

    protected LinkedList<LinkedList> trainingSet = new LinkedList<LinkedList>();
    protected LinkedList<LinkedList> testingSet = new LinkedList<LinkedList>();
    
    protected LinkedList<Double> weights = new LinkedList<Double>();

    protected double learningRate;
    protected int learningCount;
    protected double threshold;

    protected double correctRateForTraining = 0;
    protected double correctRateForTesting = 0;
    
    public Perceptron(LinkedList<LinkedList> dataSet, LinkedList<Integer> crowds, LinkedList<Integer> cluster) {
        this.dataSet = dataSet;
        this.cluster = cluster;
        this.crowds = crowds;
        
        learningRate = 0.8;
        learningCount = 10000;
        threshold = -1;
    }

    public void execute() {
        divideSet();
        setInitWeights();
        training();
        testing();
    }

    public LinkedList<LinkedList> getDataSet() {
        return dataSet;
    }
    
    public LinkedList<Integer> getCluster() {
        return cluster;
    }

    public LinkedList<LinkedList> getTrainingSet() {
        return trainingSet;
    }

    public LinkedList<LinkedList> getTestingSet() {
        return testingSet;
    }

    public LinkedList<Double> getWeights() {
        return weights;
    }
    
    public double getCorrectRateForTraining() {
        return correctRateForTraining;
    }

    public double getCorrectRateForTesting() {
        return correctRateForTesting;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setLearningCount(int learningCount) {
        this.learningCount = learningCount;
    }

    protected void divideSet() {
        // clear set
        trainingSet.clear();
        testingSet.clear();

        // less input
        if (dataSet.size() < 10) {
            for ( int i = 0 ; i < dataSet.size() ; i++ ) {
                LinkedList<Double> entry = dataSet.get(i);
                trainingSet.add(entry);
                testingSet.add(entry);
            }
            
            return;
        }

        int training_set_length = (int)Math.ceil(dataSet.size() * 2 / 3);
        int testing_set_length = dataSet.size() - training_set_length;

        // divide data into training or testing set
        for ( int i = 0 ; i < training_set_length ; i++ ) {
            LinkedList<Double> entry = dataSet.get(i);
            trainingSet.add(entry);
        }

        for ( int i = 0 ; i < testing_set_length ; i++ ) {
            LinkedList<Double> entry = dataSet.get(training_set_length+i);
            testingSet.add(entry);
        }
    }

    protected void setInitWeights() {
        double init_value = 1;
        int dimension_of_data = dataSet.get(0).size();

        weights.clear();
        
        for ( int i = 0 ; i < dimension_of_data ; i++) {
            weights.add(init_value);
        }
        
        weights.addFirst(threshold);
    }

    protected void training() {
        int num_of_execute = 0;
        int num_of_correct = 0;
        int learningCount = this.learningCount;

        while ( learningCount > 0 ) {
            for (int i = 0 ; i < trainingSet.size() ; i++) {
                LinkedList<Double> data = (LinkedList<Double>) trainingSet.get(i).clone();
                data.addFirst((double)-1);

                double result = 0;

                for ( int j = 0 ; j < data.size() ; j++ ) {
                    result += weights.get(j) * data.get(j);
                }

                if (sgn(result) == crowds.get(i)) {
                    ++num_of_correct;
                } else {
                    adjustWeight(data, crowds.get(i), result);
                }

                ++num_of_execute;
            }

            --learningCount;
        }

        correctRateForTraining = (double)num_of_correct / (double)num_of_execute;
        
        System.out.println("correctRateForTraining:" + correctRateForTraining);
        System.out.println("num_of_correct:" + num_of_correct);
        System.out.println("num_of_execute:" + num_of_execute);
    }
    
    protected void testing() {
        int num_of_execute = 0;
        int num_of_correct = 0;

        for ( int i = 0 ; i < testingSet.size() ; i++ ) {
            LinkedList<Double> data = (LinkedList<Double>) testingSet.get(i).clone();

            double result = 0;

            for ( int j = 0 ; j < data.size() ; j++ ) {
                result += weights.get(j) * data.get(j);
            }

            if (sgn(result) == crowds.get(i)) {
                ++num_of_correct;
            }

            ++num_of_execute;
        }

        correctRateForTesting = (double)num_of_correct / (double)num_of_execute;
        System.out.println("correctRateForTesting: " + correctRateForTesting);
    }
    
    protected int sgn(double input) {
        return (input >= 0) ? cluster.get(0) : cluster.get(1); 
    }

    protected void adjustWeight(LinkedList<Double> data, int crowd, double result) {
        if (crowd == cluster.get(0) && result < 0) {
            for ( int i = 0 ; i < weights.size() ; i++) {
                double weight_advised = weights.remove(i) + learningRate*data.get(i);
                weights.add(i, weight_advised);
            }
        } else if (crowd == cluster.get(1) && result > 0) {
            for ( int i = 0 ; i < weights.size() ; i++ ) {
                double weight_advised = weights.remove(i) - learningRate*data.get(i);
                weights.add(i, weight_advised);
            }
        }
    }
}
