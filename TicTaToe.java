import java.util.Scanner;
import java.util.Random;

public class TicTaToe {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        TTTBoard board = new TTTBoard();
        Random random = new Random();

        System.out.println("Welcome to Tic Tac Toe!");
        
        // Kullanıcıdan 'X' veya 'O' seçimi alınır
        System.out.print("Do you want to be 'X' or 'O'? ");
        char player = reader.nextLine().toUpperCase().charAt(0);
        while (player != 'X' && player != 'O') {
            System.out.print("Invalid choice. Please choose 'X' or 'O': ");
            player = reader.nextLine().toUpperCase().charAt(0);
        }
        
        char computer = (player == 'X') ? 'O' : 'X'; // Bilgisayar kullanıcının tersi olarak atanır
        System.out.println("You are '" + player + "' and the computer is '" + computer + "'.");
        System.out.println(board);

        // Oyun döngüsü
        while (board.getWinner() == '-' && !board.full()) {
            int row = 0;
            int column = 0;
            boolean success = false;
            System.out.println(player + "'s turn.");

            // Kullanıcı hamlesi
            if (player == 'X') {
                System.out.print("Enter row and column [1-3 space 1-3]: ");
                row = reader.nextInt();
                column = reader.nextInt();
                success = board.placeXorO(player, row, column);
            } 
            // Bilgisayar hamlesi
            else {
                while (!success) {
                    row = random.nextInt(3) + 1;
                    column = random.nextInt(3) + 1;
                    success = board.placeXorO(computer, row, column);
                }
                System.out.println("Computer placed '" + computer + "' at (" + row + ", " + column + ")");
            }

            if (!success) {
                System.out.println("Error: cell already occupied. Try again.");
            } else {
                System.out.println(board);
                player = (player == 'X') ? 'O' : 'X'; // Sırayı değiştir
            }
        }

        // Oyun sonucunu göster
        char winner = board.getWinner();
        if (winner != '-') {
            System.out.println(winner + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}


