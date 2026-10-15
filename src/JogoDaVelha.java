import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        char currentPlayer = 'X';
        boolean gameOver = false;

        System.out.println("-------- Bem vindo ao jogo da velha mais simples do mundo --------");

        while (!gameOver) {
            printBoard(board);
            System.out.println("Vez do: " + currentPlayer);

            System.out.println("Escolha a linha (0, 1 ou 2): ");
            int row = scan.nextInt();

            System.out.println("Escolha a coluna (0, 1 ou 2): ");
            int col = scan.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;

                if (checkWin(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("Parabéns! " + currentPlayer + " venceu o jogo.");
                    gameOver = true;
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("Deu velha! Jogo empatado.");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Jogada inválida! Tente novamente.");
            }
        }

        scan.close();
    }

    // printa o """tabuleiro"""
    public static void printBoard(char[][] board) {
        System.out.println("\n  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
        }
        System.out.println();
    }

    // ve se há vitoria (analisando colunas, linhas e diagonais)
    public static boolean checkWin(char[][] board, char player) {
        // checa linha e coluna
        for (int i = 0; i < 3; i++) {
            if (
                (board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            ) { return true; }
        }

        // checa na diagonal
        if (
            (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) { return true; }

        return false;
    }

    // ve se deu velha
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Ainda há espaços vazios
                }
            }
        }

        return true;
    }
}
