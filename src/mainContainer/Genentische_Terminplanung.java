package mainContainer;

public class Genentische_Terminplanung {

	/************* Globale Definitionen ********************************/
	static final int MAXLEB = 12;		/* Anzahl der Lebewesen  */
	static final int  S_TAGPUNKTE = 3;  /* Strafpunkte für Prüfungen am selben Tag */
	static final int N_TAGPUNKTE = 1;   /* Strafpunkte für Prüfungen am nächsten Tag */
	
	/************* Globale Deklarationen ********************************/

	/*int (*pool)[MAXLEB] 	/* Zeiger auf ein Array mit MAXLEB Integerelementen
							siehe Funktion eingeben
							Gesamtheit aller Lebewesen + Feld für Strafpunkte */
		   					/**Achtung: Durch Umdrehung von MAXLEB und MAXGEN erscheint es 
		   					teilweise etwas unlogisch. Rein gefühlsmäßig würde
	           				man Spalten und Zeilen nicht wie oben anordnen **/
		//(*paar), siehe Funktion eingeben
							// Feld für die 0/1 - Kombination für die Paarung
		int genera, 		// aktuelle Zahl der Generationen
		bestleb,			// Index des besten Lebewesens
		partner,
		worstleb1,      	// Index des schlechtesten Lebewesens
		worstleb2,      	// Index des 2. schlechtesten Lebewesens
		anz_pruef,      	// Anzahl der Prüfungen die verteilt werden sollen
		maxgen;				// Maximale Anzahl der Gene => Anzahl der Prüfungen
		long maxgenera;		// maximal erlaubte Zahl der Generationen

	public static void main(String[] args) {
		
	}
	
	public void eingabe(){
		
	}
	
	public void init(){
		
	}
	

}
