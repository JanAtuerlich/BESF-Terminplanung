package mainContainer;

public class Genentische_Terminplanung {

	/************* Globale Definitionen ********************************/
	static final int MAXLEB = 12;		/* Anzahl der Lebewesen  */
	static final int  S_TAGPUNKTE = 3;  /* Strafpunkte f�r Pr�fungen am selben Tag */
	static final int N_TAGPUNKTE = 1;   /* Strafpunkte f�r Pr�fungen am n�chsten Tag */
	
	/************* Globale Deklarationen ********************************/

	/*int (*pool)[MAXLEB] 	/* Zeiger auf ein Array mit MAXLEB Integerelementen
							siehe Funktion eingeben
							Gesamtheit aller Lebewesen + Feld f�r Strafpunkte */
		   					/**Achtung: Durch Umdrehung von MAXLEB und MAXGEN erscheint es 
		   					teilweise etwas unlogisch. Rein gef�hlsm��ig w�rde
	           				man Spalten und Zeilen nicht wie oben anordnen **/
		//(*paar), siehe Funktion eingeben
							// Feld f�r die 0/1 - Kombination f�r die Paarung
		int genera, 		// aktuelle Zahl der Generationen
		bestleb,			// Index des besten Lebewesens
		partner,
		worstleb1,      	// Index des schlechtesten Lebewesens
		worstleb2,      	// Index des 2. schlechtesten Lebewesens
		anz_pruef,      	// Anzahl der Pr�fungen die verteilt werden sollen
		maxgen;				// Maximale Anzahl der Gene => Anzahl der Pr�fungen
		long maxgenera;		// maximal erlaubte Zahl der Generationen

	public static void main(String[] args) {
		
	}
	
	public void eingabe(){
		
	}
	
	public void init(){
		
	}
	

}
