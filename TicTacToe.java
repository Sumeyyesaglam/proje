import java.util.Random;
import java.util.Scanner;
  /*

    Spelet är utformat som en Tre-i-rad (Tic-Tac-Toe) där en spelare spelar mot datorn.
    Spelet ska spelas på en 3x3-rutnätsbräda. Spelaren väljer sin symbol som antingen 'X' eller 'O'. 
    Spelet ska slumpmässigt välja vem som börjar. Spelaren och motståndaren turas om att placera sina symboler i en tom ruta.
    Om en spelare får tre av samma symbol (vertikalt, horisontellt eller diagonalt) i rad vinner de spelet.
    Om spelbrädan fylls och ingen har bildat en tre-i-rad, slutar spelet oavgjort.
     Spelet erbjuder spelaren möjligheten att börja om och håller reda på antalet spel som spelaren har vunnit.

    Observera: Nyckelordet "static" indikerar att en variabel eller metod är på klassnivå;
     i detta fall kan den användas direkt utan att skapa en instans av klassen.

  */

 public class TicTacToe {
    static char[][] bräde = new char[3][3];//Spelet ska spelas på en bräda som består av ett rutnät med storleken 3x3.


    static char symbol1;
    
    static char symbol2;
    static Random random = new Random();//
    static Scanner scanner = new Scanner(System.in);//Att hämta information från användaren från konsolen.

    public static void main(String[] args) {
        System.out.println("Vänligen välj X eller O.");
        symbol1 = scanner.next().charAt(0);
        symbol2 = (symbol1 == 'X') ? 'O' : 'X';//ternary
        /*
            if (symbol1 == 'X') {
            symbol2 = 'O';
            } else {
            symbol2 = 'X';
            }
         */

        initializeBoard();//För att initiera brädan fylls varje ruta med det tomma tecknet (' ').
        printBoard();//Det skriver ut brädan på skärmen så att spelaren kan se drag.

        // Spelordningen bestäms slumpmässigt.
        boolean playerTurn = random.nextBoolean();
        System.out.println((playerTurn ? "Spelare" : "Dator") + " start!");

        // Spelcyckel
    while (true) {
        if (playerTurn) { // Spelarens drag
            System.out.println("Gör ett drag: Rad och Kolumn (mellan 0 och 2)");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            // En drag begärs tills en giltig drag görs.
            if (makeMove(row, col, symbol1)) { 
                printBoard();
                if (checkWinner(symbol1)) { // Kontrollerar om spelaren har vunnit.
                    System.out.println("Grattis, du vann!");
                    break;
                }
                playerTurn = false; // Turen går över till datorn.
            }
        } else { //Datorns drag.
            System.out.println("Datorns drag");
            int row, col;
            // Slingan fortsätter tills en giltig drag hittas.
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (!makeMove(row, col, symbol2));
            printBoard();
            if (checkWinner(symbol2)) { // Kontrollerar om datorn har vunnit
                System.out.println("Datorn vann.");
                break;
            }
            playerTurn = true; // Turen går till spelaren.
        }

        if (isDraw()) { // Kontrollerar om det är oavgjort.
            System.out.println("Spelet är oavgjort!");
            break;
        }
    }
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            bräde[i][j] = ' ';
    }

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print("|" +bräde[i][j]);
            System.out.println("|");
        }
    }

    static boolean makeMove(int row, int col, char symbol) {//Gör ett drag. Om den angivna rutan är tom, 
        //placerar den spelarens symbol där; annars ger den ett felmeddelande."
        // Kontroll om raden och kolumnen är inom gränserna.
    if (row < 0 || row > 2 || col < 0 || col > 2) { // Detta kontrollerar radgränsen.
        System.out.println("Var god ange ett tal mellan 0 och 2");
        return false;
    }
    if (bräde[row][col] == ' ') { // Om rutan är tom.
        bräde[row][col] = symbol; // Placerar symbolen i rutan.
        return true; // Returnerar true för att ange att det var framgångsrikt.
    } else {
        System.out.println("Denna ruta är upptagen, välj en annan ruta.");
    }
    return false; // Returnerar false för att indikera att det misslyckades.
    }

    static boolean checkWinner(char symbol) {//Kontrollerar vinstvillkoren. 
        //När tre lika symboler ligger i rad horisontellt, vertikalt eller diagonalt, deklareras vinnaren
        for (int i = 0; i < 3; i++) {
            // Kontrollera rader och kolumner.
            if (bräde[i][0] == symbol && bräde[i][1] == symbol && bräde[i][2] == symbol) return true;//return-metoden avslutar omedelbart.
            if (bräde[0][i] == symbol && bräde[1][i] == symbol && bräde[2][i] == symbol) return true;
            // Om det finns en trippeluppsättning av symboler någonstans returnerar den true och spelet har vunnits; 
            //annars returnerar den false och spelet fortsätter.
        }
        // Kontrollera diagonalerna.
        return (bräde[0][0] == symbol && bräde[1][1] == symbol && bräde[2][2] == symbol) ||
               (bräde[0][2] == symbol && bräde[1][1] == symbol && bräde[2][0] == symbol);
    }

    static boolean isDraw() {// Kontrollerar om det är oavgjort. Om alla rutor är fyllda men det inte finns någon vinnare, deklareras oavgjor.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (bräde[i][j] == ' ') 
                return false;//// Om det finns tomma rutor, finns det ingen oavgjord.
        return true;// // Om det inte finns några tomma rutor, finns det oavgjort.
    } }

    /*
        printBoard(): Skriver ut brädan på skärmen.
makeMove(): Försöker göra ett drag på angiven position, returnerar true om det lyckas, annars false.
checkWinner(): Kontrollerar om en spelare har vunnit.
isDraw(): Kontrollerar om det är oavgjort.
initializeBoard(): Rensar spelbrädan.

Brädan är en 3x3 matris

    ' ' | ' ' | ' '
    ' ' | ' ' | ' '
    ' ' | ' ' | ' '

    bräda[0][0] övre vänstra cellen
    bräda[1][1] mittcellen
    bräda[2][2] nedre högra cellen

    Denna diagonal ser ut så här:

           
    X |   |  
      | X |  
      |   | X

      */