import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scan = new Scanner(System.in);
    private static final  int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    private boolean  boxAvailable;
    private byte winner = 0;
    private boolean boxEmpty = false;
    private char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");
        makeMove();
    }
    private void makeMove(){
        while (winner == 0) {
            printBoard(box);
            boxEmpty = isBoxEmpty(boxEmpty, box);
            isboxAvailable();
            winner = getWinner(box, winner, boxAvailable);
            fillingTheCrosses(scan, box);
            fillingTheZeros(box);
        }
        if(winner == 1){
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if(winner == 2){
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if(winner == 3){
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

        }
    }

    private void isboxAvailable() {
        for(int counter=0; counter<9; counter++){
            if(box[counter] != 'X' && box[counter] != 'O'){
                boxAvailable = true;
                break;
            }
        }
    }

    private boolean isBoxEmpty(boolean boxEmpty, char[] box) {
        if(!boxEmpty){
            Arrays.fill(box, ' ');
            boxEmpty = true;
        }
        else {
            boxAvailable = false;
        }
        return boxEmpty;
    }

    private byte getWinner(char[] box, byte winner, boolean boxAvailable) {

        for (int[] pattern : winPatterns) {
            if (box[pattern[0]] == box[pattern[1]] && box[pattern[1]] == box[pattern[2]]) {
                if (box[pattern[0]] == 'O') {
                    return 2;
                } else if (box[pattern[0]] == 'X') {
                    return 1;
                }
            }
        }
        if (!boxAvailable) {
            winner = 3;
        }
        return winner;
    }

    private void fillingTheZeros(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private void fillingTheCrosses(Scanner scan, char[] box) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if ((input > 0) && (input < 10)) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    private void printBoard(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

}
