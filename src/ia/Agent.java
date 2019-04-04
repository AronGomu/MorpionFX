package ia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Agent {
	
	public int[] layers;
	public double error = 0.0 ;
	public MultiLayerPerceptron net;
	public int nbHiddenLayers;
	public double learningRate;
	public int epochs;
	
	public Agent() throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("save_options")));
		String line = "" ;
		int count = 0;
		while ( (line=br.readLine()) != null ) {
			count++;
			if (count == 1) nbHiddenLayers = Integer.parseInt(line);
			if (count == 2) epochs = Integer.parseInt(line);
			if (count == 3) learningRate = Double.parseDouble(line);
		}
		br.close();
		
		
		layers = new int[]{ 9, nbHiddenLayers, 9 };
		net = new MultiLayerPerceptron(layers, learningRate, new SigmoidalTransferFunction());
		
		LearningMlp.learn(net,"save.txt", epochs);
		
		
	}
	
	public double[] findPercentage(double[] plateau) {
		double[] output = net.forwardPropagation(plateau);
		return output;
	}
}
