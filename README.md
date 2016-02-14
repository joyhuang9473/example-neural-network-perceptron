![2cring@480.png](http://imgur.com/fG9Ri4u.png)

Example-Neural-Network-Perceptron
=========

Example-Neural-Network-Perceptron is an implementation of perceptron algorithm for supervised learning of binary classifiers, written in Java.

Junior project in class "Neural Network". Last updated 10/15/2014.

How to use
-----------------------

- Select input file from `dataSet` directory.
- Field `學習率 (learning rate)` and `學習次數 (count of learning loop)` columns
or Field blank to use the default value.
- Click `執行 (execute)` button to run.

Feature
-----------------------

### Graphics ###

Blue dots for testing set.

Red dots for training set.

Yellow dots for weight vectors.

### DataSet (Trainging Set, Testing Set) ###

    // src/neural_network_perceptron/Perceptron.java
    ...
    int training_set_length = (int)Math.ceil(dataSet.size() * 2 / 3);
    int testing_set_length = dataSet.size() - training_set_length;

### Rate ###

    // src/neural_network_perceptron/Perceptron.java
    ...
    correctRateForTraining = (double)num_of_correct / (double)num_of_execute;
    ...
    correctRateForTesting = (double)num_of_correct / (double)num_of_execute;

### Default Setting ###

    // src/neural_network_perceptron/Perceptron.java
    ...
    learningRate = 0.8;
    learningCount = 10000;

Dependency
-----------------------

JDK

    $ java -version

    java version "1.8.0_66"
    Java(TM) SE Runtime Environment (build 1.8.0_66-b17)
    Java HotSpot(TM) 64-Bit Server VM (build 25.66-b17, mixed mode)

Build and run
-----------------------

### Compile ###

    $ javac -d bin -sourcepath src src/neural_network_perceptron/*.java

### Run ###

    $ java -cp bin neural_network_perceptron.Main

### Create jar file ###

    $ jar cfe neural_network_perceptron.jar neural_network_perceptron.Main -C bin/ .

    $ jar tf neural_network_perceptron.jar # list table of contents for archive

    META-INF/
    META-INF/MANIFEST.MF
    .gitkeep
    neural_network_perceptron/
    neural_network_perceptron/Coordinate.class
    neural_network_perceptron/FileData.class
    neural_network_perceptron/Framework$CustomActionListener.class
    neural_network_perceptron/Framework.class
    neural_network_perceptron/Main.class
    neural_network_perceptron/Perceptron.class

Screenshot
-----------------------

![2Ccircle1@480.png](http://imgur.com/TUEl8x4.png)

![2Hcircle1@480.png](http://imgur.com/Z9A41xn.png)

![2ring@480.png](http://imgur.com/OCvr7Vd.png)

More details in [http://imgur.com/a/WO2Ip](http://imgur.com/a/WO2Ip)

Reference
-----------------------

[1]. Sergiy Kovalchuk, "[How to Compile and Run Java Code from a Command Line](http://www.sergiy.ca/how-to-compile-and-launch-java-code-from-command-line/)", 2011

[2]. StackOverFlow, "[Create jar file from command line](http://stackoverflow.com/questions/11243442/create-jar-file-from-command-line)", 2012
