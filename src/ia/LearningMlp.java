package ia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class LearningMlp {

	/**
	 * LEARN FROM ALL DATA ...
	 * @param file
	 * @param h
	 * @param learningRate
	 * @param epochs
	 */
	public static void learn(MultiLayerPerceptron net, String file, int epochs) {
		try {

			HashSet<Coup> coups = new HashSet<>();
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "" ;
			
			while ( (line=br.readLine()) != null ) {
				double[] in = Coup.getVector(line.split("\t")[0].split(","));
				double[] out = Coup.getVector(line.split("\t")[1].split(","));
				
				coups.add(new Coup(in, out));
			}

			br.close();

			learnDuringGame(coups, net, epochs);
			
			net.save("agent.txt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// epochs nombre de lecture des coups
	public static void learnDuringGame(HashSet<Coup> coups, MultiLayerPerceptron net, int epochs) {
		try {

			double error = 0.0 ;

			//TRAINING ...
			for(int i = 0; i < epochs; i++){

				for ( Coup coup : coups ) {

					error += net.backPropagate(coup.in, coup.out);

					if ( i % 10 == 0 )
						System.out.println("Error at step "+i+" is "+ (error/(double)i));
				}
			}
			error /= epochs ;

			System.out.println("Final error: "+error);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
