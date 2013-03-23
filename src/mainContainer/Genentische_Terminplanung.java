package mainContainer;

public class Genentische_Terminplanung {

	/************* Globale Definitionen ********************************/
	static final int MAXLEB = 12;		/* Anzahl der Lebewesen  */
	static final int  S_TAGPUNKTE = 3;  /* Strafpunkte für Prüfungen am selben Tag */
	static final int N_TAGPUNKTE = 1;   /* Strafpunkte für Prüfungen am nächsten Tag */
	
	/************* Globale Deklarationen ********************************/

	static int genera, 					// aktuelle Zahl der Generationen
		bestleb,						// Index des besten Lebewesens
		partner,	
		worstleb1,  	    			// Index des schlechtesten Lebewesens
		worstleb2,      				// Index des 2. schlechtesten Lebewesens
		anz_pruef,  			    	// Anzahl der Prüfungen die verteilt werden sollen
		maxgen;							// Maximale Anzahl der Gene => Anzahl der Prüfungen
	
	static long maxgenera;				// maximal erlaubte Zahl der Generationen

	static int[] paar; 					// Feld für die 0/1 - Kombination für die Paarung
	static int[][] pool;				// Gesamtheit aller Lebewesen + Feld für Strafpunkte
										/**Achtung: Durch Umdrehung von MAXLEB und MAXGEN erscheint es 
										teilweise etwas unlogisch. Rein gefühlsmäßig würde
										man Spalten und Zeilen nicht wie oben anordnen **/

	/********************************************************************
	*		   Funktion init
	* Genpool mit 0 vorbelegen; Variablen vorbesetzen
	********************************************************************/
	
	public static void init() {

		genera = 0;
		pool = new int[maxgen+1][MAXLEB]; //Array mit MAXLEB Integerelementen
		
		for(int i = 0; i < MAXLEB; i++) {
			for (int j = 0; j < maxgen+1; j++) {
				pool[j][i] = 0; // Gen mit 0 besetzen und Strafpunktefeld vorbelegen
			}
		}
	}
	
	/********************************************************************
	*		   Funktion setpool
	* Genpool initialisieren; 
	********************************************************************/
	
	public static void setpool() {
		
		for(int i = 0; i < MAXLEB; i++) {
			for (int j = 0; j < anz_pruef; j++) {
				int z = (int) (maxgen * Math.random());
				pool[z][i]++; // Gen um 1 aufaddieren
			}
		}
	}
	
	/******************************************************************
	*               Funktion strafpunkte
	* Berechne Strafpunkte fuer die Felder 
	******************************************************************/
	
	public static void strafpunkte (int lebnr) {
		
		pool[maxgen][lebnr] = 0; //Zurück auf 0 setzen
     
		for (int j = 0; j < maxgen; j++) {
			if (pool[j][lebnr] > 1) { // Strafpunkte f. Prüfungen am selben Tag 
				pool[maxgen][lebnr]=pool[maxgen][lebnr] + ((pool[j][lebnr]-1)*S_TAGPUNKTE);
			}
		}
		 
		for (int j = 1; j < maxgen; j++) {
			if (pool[j][lebnr] > 0 && pool[j-1][lebnr] > 0) { // Strafpunkte f. Prüfungen am naechsten Tag
				pool[maxgen][lebnr] = pool[maxgen][lebnr] + N_TAGPUNKTE;
			}
		}
	}
	
	/*******************************************************************
	*		Funktion findbest
	* Bestes Lebewesen herausfinden
	********************************************************************/
	
	public static void findbest() {
		
		bestleb = 0;
		
		for (int i = 1; i< MAXLEB;i++) { // alle Lebewesen überprüfen
			if ( pool[maxgen][i] < pool[maxgen][bestleb] ) {
				bestleb = i;
			}
		}
	}
	
	/*******************************************************************
	*		Funktion findworst
	* Schlechtestes Lebewesen herausfinden
	********************************************************************/

	public static void findworst() {
		
		worstleb1 = 0;
		
		for (int i = 1; i< MAXLEB;i++) { // alle Lebewesen überprüfen
			if ( pool[maxgen][i] > pool[maxgen][worstleb1] ) {		
				worstleb1 = i;
			}
		}
		if(worstleb1==bestleb) {
			worstleb1 = worstleb1 + 1;
		}
	}
	  
	/*******************************************************************
	*		Funktion find2worst
	* 2.Schlechtestes Lebewesen herausfinden
	********************************************************************/

	public static void find2worst()	{

		if (worstleb1 == 0) {
			worstleb2 = 1;
		}
		else {
			worstleb2 = 0;
		}
		for (int i = 0; i< MAXLEB;i++) { // alle Lebewesen überprüfen
			if ( pool[maxgen][i] > pool[maxgen][worstleb2] && i != worstleb1) {		
				worstleb2 = i;
			}
		}
		if (worstleb2 == bestleb) {
			if (worstleb2+1 == worstleb1) {
				worstleb2 = worstleb2 + 2;
			 }
			else {
				worstleb2 = worstleb2 + 1;
			}
		}
	}
	
	/********************************************************************
	*		Funktion paarung
	*
	* Erzeugen des 0/1 - Kombination für die Paarung
	* Auswählen des Partners für die Paarung
	*
	*
	********************************************************************/
	
	public static void paarung() {
		
		paar = new int[maxgen];
		
		for(int i=0; i < maxgen; i++) { // Erzeugen des 0/1 - Feldes         		
			paar[i] = (int) (2 * Math.random());	
		}

		do { //Auswählen des Partners 
			partner = (int) (MAXLEB * Math.random());
		}
		while(partner == bestleb || partner == worstleb1 || partner == worstleb2);
	}

	/********************************************************************
	*		Funktion mutation
	*
	*
	********************************************************************/
	
	public static void mutation() {
		int sum_kind1= 0;
		int sum_kind2= 0;

		int kind1 = worstleb1;
		int kind2 = worstleb2;

		int vater = bestleb;
		int mutter = partner;

		for (int i = 0; i < maxgen; i++) { // die 2 schlechtesten Lebewesen sterben lassen und durch 2 Kinder ersetzen
			pool[i][kind1] = 0;
			pool[i][kind2] = 0;
		}
		for (int i = 0; i < maxgen; i++) {
			if(paar[i] == 1) { //für Kind1 wird Wert von Vater übernommen
				pool[i][kind1] = pool[i][vater];
			} else { //für Kind2 wird Wert von Mutter übernommen
				pool[i][kind2] = pool[i][mutter];
			}
		}
	       /* Verbleibende Klausuren verteilen bei Kind1 u. Kind2 */
		for (int i = 0; i < maxgen; i++) {
			sum_kind1 = sum_kind1+pool[i][kind1];
			sum_kind2 = sum_kind2+pool[i][kind2];
		}
		for (int i = 0; i < (anz_pruef - sum_kind1); i++) { // für Kind1
			int z = (int) (maxgen * Math.random());
			if(paar[z] == 0) { // Nur in leere Spalten auffüllen
				pool[z][kind1]++;
			} else {
				i--;
			}
		}
		for (int i = 0; i < (anz_pruef - sum_kind2); i++) { // für Kind2
			int z = (int) (maxgen * Math.random());
			if(paar[z] == 1) { // Nur in leere Spalten auffüllen
				pool[z][kind2]++;
			} else {
				i--;
			}
		}
	}

	/********************************************************************
	*		Funktion checkbest
	*
	* Überprüft, ob Lebewesen b bereits perfekt
	********************************************************************/
	
	public static int checkbest () {
		
		int i = 0;
		int r_checkbest=-1;   //Rückgabewert: -1 wenn kein Lebew. optimal Nr. des Lebew. wenn optimal

		while(i < MAXLEB && r_checkbest == -1) {
			if(pool[maxgen][i] == 0) {
				r_checkbest = i;
			}
			i++;
		}
		return(r_checkbest);
	}
	
	/********************************************************************
	*			main
	********************************************************************/
	
	public static void main(String[] args) {
	
		/*Eingabe aller benötigen Daten*/
		maxgen = 5;
		anz_pruef = 6;
		maxgenera = 4;
		init();
		setpool();
		int check = 0, x;

		
		for(x = 0; x < maxgenera; x++) {
			for(int i = 0; i < MAXLEB; i++)
				strafpunkte(i);
			check = checkbest();
			if (check != -1) {
				break;
			}
			findbest();
			findworst();
			find2worst();
			paarung();
			mutation();
		}
		ausgabe(x,check);
		
		
		/*Ausgabe des Arrays in Konsole*/
		for(int i = 0; i < MAXLEB; i++) {
			System.out.println("");
			for (int j = 0; j < maxgen; j++) {
				System.out.print(pool[j][i]);
			}
		}
	}
	
	/********************************************************************
	*		Funktion ausgabe
	*
	********************************************************************/
	public static void ausgabe(int generation, int check) {

		System.out.println("Ergebnis nach " + generation + " Generationen:" );
		if(generation < 10)
			System.out.println("=============================");
		for(int i = 0; i < MAXLEB; i++) {
			System.out.println(i + 1);
			for (int j = 0; j < maxgen + 1; j++) {
				if (j == maxgen) System.out.println( "  "+  pool[j][i]);
			}
		}
		if (check != -1) {
			int ausgabeOptimum = check + 1;
			System.out.println("\nOptimum: " + ausgabeOptimum);
			System.out.println("Termine:");
			for(int k = 0; k < maxgen; k++) {
				System.out.println("  " + pool[k][check]);
			}
		} else {
			if(pool[maxgen][bestleb] < 2) {
				int ausgabeBestesMitEinemStrafpunkt = bestleb + 1;
				System.out.println("\nDas Beste: (mit einem Strafpunkt)" + ausgabeBestesMitEinemStrafpunkt);
			}
			else {
				int ausgabeBestesMitMehrAlsEinemStrafpunkt = bestleb+1;
				int ausgabeAnzahlStrafpunktedesBestenMitMehrAlsEinemStrafpunkt = pool[maxgen][bestleb]; 
				System.out.println("\nDas Beste: " + ausgabeBestesMitMehrAlsEinemStrafpunkt + 
						" (mit " + ausgabeAnzahlStrafpunktedesBestenMitMehrAlsEinemStrafpunkt + " Strafpunkten)");
			}
			System.out.println("Termine:");
			for(int m = 0; m < maxgen; m++) {
				System.out.println("  "+  pool[m][bestleb]) ;
			}
		}
		return;
	}
	
}