package ia;

public class Coup {

	public double[] in ;
	public double[] out ;
	
	public Coup(double[] in, double[] out) {
		this.in = in ;
		this.out = out ;
	}
	
	public static double[] getVector(String[] t) {
		try {
			double[] vector = new double[t.length];
			System.out.println("YOYOYOYOYOYOY : " + t.length + "\n\n");
			if (t.length < 2) return null;
			
			for (int i = 0; i < vector.length; i++) {
				vector[i] = new Double(t[i]);
			}
			return vector ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
