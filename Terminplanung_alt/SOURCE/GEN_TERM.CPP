/********************************************************************
*
* Implementierung eines genetischen Alghorithmus
* zur Terminplanung
* 
* C-Programm
* Hahn, Banholzer
*
* 26.11.94
*
* term_gen.cpp
* Version: 2.0
*
********************************************************************/



/************ Präprozessoranweisungen ******************************/
#include <conio.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <iostream.h>


/************* Globale Definitionen ********************************/
#define MAXLEB 12	/* Anzahl der Lebewesen  */
#define S_TAGPUNKTE 3   /* Strafpunkte für Prüfungen am selben Tag */
#define N_TAGPUNKTE 1   /* Strafpunkte für Prüfungen am nächsten Tag */



/************* Globale Deklarationen ********************************/

int     (*pool)[MAXLEB],// Zeiger auf ein Array mit MAXLEB Integerelementen
			// siehe Funktion eingeben
			// Gesamtheit aller Lebewesen + Feld für Strafpunkte
	   //!!!Achtung: Durch Umdrehung von MAXLEB und MAXGEN erscheint es
	   //	         teilweise etwas unlogisch. Rein gefühlsmäßig würde
           //            man Spalten und Zeilen nicht wie oben anordnen
	(*paar),        // siehe Funktion eingeben
			/* Feld für die 0/1 - Kombination für die Paarung */
	genera, 	/* aktuelle Zahl der Generationen */
	bestleb,	/* Index des besten Lebewesens */
	partner,
	worstleb1,      /* Index des schlechtesten Lebewesens */
	worstleb2,      /* Index des 2. schlechtesten Lebewesens */
	anz_pruef,      /* Anzahl der Prüfungen die verteilt werden sollen */
	maxgen;		/* Maximale Anzahl der Gene => Anzahl der Prüfungen */
long    maxgenera;	/* maximal erlaubte Zahl der Generationen */

/********************************************************************
*                       Funktion kopf
*
* Zweck: Ausgabe des Kopfes für Ein- und Ausgabe
********************************************************************/ 
void kopf (void)
{

	clrscr();
	gotoxy(8,3);
	cout << "Anwendung eines Genetischen Algorithmus auf die Terminplanung!";
	gotoxy(8,4);
	cout << "--------------------------------------------------------------";
}


/********************************************************************
*                       Funktion eingeben
*
* Zweck: Eingabe der globalen Variablen:
*                           Anzahl der Gene
*                           Anzahl der Prüfungen
********************************************************************/ 
void eingeben (void)
     {

	kopf();
	gotoxy(10,11);
	cout << "Bitte Anzahl der Tage eingeben: ";
	gotoxy(10,14);
	cout << "Bitte Anzahl der Termine eingeben: ";
	gotoxy(10,17);
	cout << "Ueber wieviele Generationen soll die Evolution laufen?";

	gotoxy(42,11);
	cin >> maxgen;
	pool=new[maxgen+1][MAXLEB]; //Zweidim.Array im Freispeicher belegen
				    //Verwendung über den zeiger pool
	paar=new[maxgen];  // Feld für die 0/1 - Kombination für die Paarung
	gotoxy(45,14);
	cin >> anz_pruef;

	gotoxy(64,17);
	cin >> maxgenera;
	if (maxgenera >= 30000)
	   {
	    maxgenera = 30000;
	   }
	clrscr();
     }
/********************************************************************
*		   Funktion init
* Genpool mit 0 vorbelegen; Variablen vorbesetzen
********************************************************************/
void init (void)
{
	int i, j;

	genera = 0;
	
	for(i=0; i < MAXLEB; i++)
	{
                for (j=0; j < maxgen+1; j++)
		{
		       pool[j][i] = 0;
	/* Gen mit 0 besetzen und Strafpunktefeld vorbelegen */
		}
	}
}



/********************************************************************
*		   Funktion setpool
* Genpool initialisieren; 
********************************************************************/
void setpool(void)
{
	int i, j , z;

	for(i=0; i < MAXLEB; i++)
	{
                for (j=0; j < anz_pruef; j++)
		{
		       z = random(maxgen);
                       pool[z][i]++;
			/* Gen um 1 aufaddieren */
		}
	}
}



/******************************************************************
*               Funktion strafpunkte
* Berechne Strafpunkte fuer die Felder 
******************************************************************/
void strafpunkte (int lebnr)
{
  int j;
	 pool[maxgen][lebnr]=0; //Zurück auf 0 setzen
                                              
         for (j=0; j < maxgen; j++)
		{
		   if (pool[j][lebnr] > 1) /* Strafpunkte f. Prüfungen am
                                              selben Tag*/   
		      {
		       pool[maxgen][lebnr]=pool[maxgen][lebnr]+
		                            ((pool[j][lebnr]-1)*S_TAGPUNKTE);
		      }
		}


         for (j=1; j < maxgen; j++)
		{
		   if (pool[j][lebnr] > 0 && pool[j-1][lebnr] > 0)
		                 /* Strafpunkte f. Prüfungen am
                                    naechsten Tag*/   
		      {
		       pool[maxgen][lebnr] = pool[maxgen][lebnr]+N_TAGPUNKTE;
                      }
		}
}

/*******************************************************************
*		Funktion findbest
* Bestes Lebewesen herausfinden
********************************************************************/

void findbest(void)
{				
	int     i=0;
 		
        bestleb = 0;

 	for (i=1; i< MAXLEB;i++) /* alle Lebewesen überprüfen */
 	 {
		if ( pool[maxgen][i] < pool[maxgen][bestleb] )
 		   {		
 			bestleb = i;
 		   }
 	 }
}

/*******************************************************************
*		Funktion findworst
* Schlechtestes Lebewesen herausfinden
********************************************************************/

void findworst(void)
{				
	int     i=0;
 		
	worstleb1 = 0;
	
        for (i=1; i< MAXLEB;i++) /* alle Lebewesen überprüfen */
 	 {
		if ( pool[maxgen][i] > pool[maxgen][worstleb1] )
 		   {		
			worstleb1 = i;
		   }
	 }
	if(worstleb1==bestleb)
	   {
	    worstleb1 = worstleb1 + 1;
	   }
}
  
/*******************************************************************
*		Funktion find2worst
* 2.Schlechtestes Lebewesen herausfinden
********************************************************************/

  void find2worst(void)
{				
	int     i=0;

       	if (worstleb1 == 0)
	   {
	     worstleb2 = 1;
           }
        else   
	  {
	     worstleb2 = 0;
	  } 
          

        for (i=0; i< MAXLEB;i++) /* alle Lebewesen überprüfen */
 	 {
		if ( pool[maxgen][i] > pool[maxgen][worstleb2] && i != worstleb1)
 		   {		
			worstleb2 = i;
		   }
	 }
      if (worstleb2 == bestleb)
	 {
	  if (worstleb2+1 == worstleb1)
	      {
	       worstleb2 = worstleb2 + 2;
	      }
	  else
	      {
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
void paarung(void)
     {
	        

	for(int i=0; i < maxgen; i++) // Erzeugen des 0/1 - Feldes
	{        		
	  paar[i] = random(2);	
	}

        do                                //Auswählen des Partners 
	 {
	  partner=random(MAXLEB);
         
	 }while(partner == bestleb || partner == worstleb1 ||
	        partner == worstleb2);



}



/********************************************************************
*		Funktion mutation
*
*
********************************************************************/
void mutation(void)
{
	int 	i,
		j,
                z,
		vater,
                mutter,
                kind1,
		kind2,
		sum_kind1= 0,
		sum_kind2= 0;

	kind1 = worstleb1;
	kind2 = worstleb2;

	vater = bestleb;
	mutter = partner;

	for (i=0;i<maxgen;i++)  //die 2 schlechtesten Lebewesen sterben
	                        //lassen und durch 2 Kinder ersetzen
	  {
	   pool[i][kind1] = 0;
           pool[i][kind2] = 0;
	  }

	
	for (i=0;i<maxgen;i++)
	  {
	   if(paar[i] == 1) //für Kind1 wird Wert von Vater übernommen
	      {
               pool[i][kind1] = pool[i][vater];
	      }
	   else            //für Kind2 wird Wert von Mutter übernommen
	      {
               pool[i][kind2] = pool[i][mutter];
	      }
           }
       /* Verbleibende Klausuren verteilen bei Kind1 u. Kind2 */
	
	for (i=0;i<maxgen;i++)
	   {
            sum_kind1 = sum_kind1+pool[i][kind1];
	    sum_kind2 = sum_kind2+pool[i][kind2];
           }

	
	

	for (i=0; i < (anz_pruef - sum_kind1); i++)  // für Kind1
		{
		       z = random(maxgen);
		       if(paar[z] == 0)   // Nur in leere Spalten auffüllen
                         {
			  pool[z][kind1]++;
			 }
		       else
			 {
			  i--;	  
                         }
			
		}
	
	
	for (i=0; i < (anz_pruef - sum_kind2); i++) // für Kind2
		{
		       z = random(maxgen);
		       if(paar[z] == 1)   // Nur in leere Spalten auffüllen
                         {
			  pool[z][kind2]++;
			 }
		       else
			 {
			  i--;	  
                         }
			
		}

    }



/********************************************************************
*		Funktion checkbest
*
* Überprüft, ob Lebewesen b bereits perfekt
********************************************************************/
int checkbest (void)
{
	int 	i=0,
		r_checkbest=-1;   /*Rückgabewert: -1 wenn kein Lebew. optimal
		                               Nr. des Lebew. wenn optimal*/

	while(i < MAXLEB && r_checkbest == -1)
	    {
             if(pool[maxgen][i] == 0) 
	      {
	        r_checkbest = i;
	      }
             i++;
	    }

        return(r_checkbest);
}


/********************************************************************
*		Funktion ausgabe
*
********************************************************************/
void ausgabe(int generation, int check)
{

	gotoxy(12,2);
	printf("Ergebnis nach %d Generationen:",generation);
	gotoxy(12,3);
	if(generation < 10)
	   cout << "=============================";
	else
	   if (generation < 100)
	      cout << "==============================";
	   else
	      if (generation < 1000)
		 cout << "===============================";
                 else
		    if (generation < 10000)
		       cout << "================================";
	            else
			cout << "=================================";

	gotoxy(2,5);	
	cout << "Lebe-";
	gotoxy(2,6);
	cout << "wesen";
	gotoxy(9,5);
	cout << "Straf-";
	gotoxy(9,6);
	cout << "punkte";
	        	
        for(int i=0; i < MAXLEB; i++)
 	{
		gotoxy(2,i+7);
		printf("%2d",i+1);
		gotoxy(15,i+7);
		for (int j=0; j < maxgen+1; j++)
 		{
		   if (j == maxgen)
		      gotoxy(9,i+7);
		   cout << "  "<<  pool[j][i];
		   
                }
	}

	if (check != -1)
        {
	   printf("\n\n\n Optimum: %d",check+1);
	   gotoxy(7,23);
	   cout << "Termine:";
           gotoxy(15,23);
	   for(int k=0; k < maxgen; k++)
           {
	      cout << "  "<<  pool[k][check];
	   }
	}
	else
	{
	   if(pool[maxgen][bestleb] < 2)
	      printf("\n\n\n Das Beste: %d  (mit einem Strafpunkt)",bestleb+1);
	   else
	      printf("\n\n\n Das Beste: %d  (mit %d Strafpunkten)",bestleb+1,pool[maxgen][bestleb]);
	   gotoxy(7,23);
	   cout << "Termine:";
           gotoxy(15,23);
	   for(int m=0; m < maxgen; m++)
           {
	      cout << "  "<<  pool[m][bestleb];
	   }
	}
	
	return;
}



/********************************************************************
*			main
********************************************************************/
void main(void)
{
	char c;

	int check;

	randomize();
        eingeben();  	//Eingabebildschirm
    	init();         // Genpool initialisieren
	setpool();	// Genpool vorbesetzen

	for(int x=0;x < maxgenera;x++)
     	{
	   for(int i=0; i < MAXLEB; i++)
              strafpunkte(i);
           check = checkbest();
           if (check != -1)
	   {
	      break;
	   }
           findbest();
	   findworst();
	   find2worst();
	   paarung();
           mutation();
        }

	ausgabe(x,check);

	c=getche();

}
