package ia;

public class Coup {

	public Coup(double[] in, double[] out) {
		// TODO Auto-generated constructor stub
		this.in = in ;
		this.out = out ;
	}
	
	public static double[] getVector(String[] t) {
		try {
			double[] vector = new double[t.length];
			
			for (int i = 0; i < vector.length; i++) {
				vector[i] = new Double(t[i]);
			}
			return vector ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public double[] in ;
	public double[] out ;
	
	public int joueur ;
	
	public static int X = 1 ;
	public static int O = -1 ;
	public static int V = 0 ;
}
