package mainContainer;

public class TestRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GenApp ga = new GenApp();
		
		ga.setMaxgenera("4");
		ga.setAnz_pruef("4");
		ga.setMaxgen("5");
		
		ga.berechnen();
		
		if (ga.getCheck() != -1) {
			int ausgabeOptimum = ga.getCheck() + 1;
			System.out.println("\nOptimum: " + ausgabeOptimum);
			System.out.println("Termine:");
			for(int k = 0; k < ga.getMaxgen(); k++) {
				System.out.println("  " + ga.getPool()[k][ga.getCheck()]);
			}
		} else {
			if(ga.getPool()[ga.getMaxgen()][ga.getBestleb()] < 2) {
				int ausgabeBestesMitEinemStrafpunkt = ga.getBestleb() + 1;
				System.out.println("\nDas Beste: (mit einem Strafpunkt)" + ausgabeBestesMitEinemStrafpunkt);
			}
			else {
				int ausgabeBestesMitMehrAlsEinemStrafpunkt = ga.getBestleb() + 1;
				int ausgabeAnzahlStrafpunktedesBestenMitMehrAlsEinemStrafpunkt = ga.getPool()[ga.getMaxgen()][ga.getBestleb()]; 
				System.out.println("\nDas Beste: " + ausgabeBestesMitMehrAlsEinemStrafpunkt + 
					" (mit " + ausgabeAnzahlStrafpunktedesBestenMitMehrAlsEinemStrafpunkt + " Strafpunkten)");
			}
			System.out.println("Termine:");
			for(int m = 0; m < ga.getMaxgen(); m++) {
				System.out.println("  "+  ga.getPool()[m][ga.getBestleb()]) ;
			}
		}
	}
}
