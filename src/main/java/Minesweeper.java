import java.util.Scanner;

public class Minesweeper {

    private static int dimensionM;
    private static int dimensionN;

    public static void main(String args[]){

        boolean[][] game = buildBoard();
        printBoard(game);

        int[][] gameCounts = generateGameValues(game);
        printBoardWithValues(game, gameCounts);

    }

    public static boolean[][] buildBoard(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter dimension n: ");
        int n = input.nextInt();
        System.out.println();
        System.out.print("Enter dimension m: ");
        int m = input.nextInt();
        System.out.println();
        System.out.print("Enter bomb prob: ");
        double p = input.nextDouble();
        System.out.println();

        dimensionM = m;
        dimensionN = n;
        return generateBoard(m, n, p);

    }


    public static boolean[][] generateBoard(int m, int n, double p){

        // game grid is [1..m][1..n], border is used to handle boundary cases
        boolean[][] bombs = new boolean[m+2][n+2];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                bombs[i][j] = (Math.random() < p);

        return bombs;
    }

    public static int[][] generateGameValues(boolean[][] bombs){

        int tmpCount = 0;
        int[][] bombCounts = new int[dimensionM + 2][dimensionN + 2];
        for (int i = 1; i <= dimensionM; i++){
            for (int j = 1; j <= dimensionN; j++){

                //if spot is a bomb
                if(bombs[i][j]) {
                    continue;
                } else { //count surrounding bombs
                    if(bombs[i - 1][j - 1]) tmpCount++;
                    if(bombs[i - 1][j]) tmpCount++;
                    if(bombs[i][j - 1]) tmpCount++;
                    if(bombs[i + 1][j + 1]) tmpCount++;
                    if(bombs[i + 1][j]) tmpCount++;
                    if(bombs[i][j + 1]) tmpCount++;
                    if(bombs[i + 1][j - 1]) tmpCount++;
                    if(bombs[i - 1][j + 1]) tmpCount++;

                    bombCounts[i][j] = tmpCount;
                    tmpCount = 0;
                }

            }
        }

        return bombCounts;
    }

    public static void printBoard(boolean[][] bombs){
        // print game
        for (int i = 1; i <= dimensionM; i++) {
            for (int j = 1; j <= dimensionN; j++)
                if (bombs[i][j]) System.out.print("* ");
                else             System.out.print(". ");
            System.out.println();
        }
    }

    public static void printBoardWithValues(boolean[][] bombs, int[][] bombCounts){
        // print game
        for (int i = 1; i <= dimensionM; i++) {
            for (int j = 1; j <= dimensionN; j++)
                if (bombs[i][j]) System.out.print("* ");
                else             System.out.print(bombCounts[i][j] + " ");
            System.out.println();
        }
    }
}
