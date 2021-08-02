import java.util.*;

public class RecursionBacktracking {

    public static void displayBoard(int[][] board) {
        for (int i = 0;i < board.length;i++) {
            for (int j = 0;j < board[0].length;j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("===============================================");
    }

    // =====================================================================================================

    public static int mazePath(int[][] maze, int sr, int sc, int er, int ec, String asf, int[][] dir, String[] dirS) {
        if (sr == er && sc == ec) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec)
                count += mazePath(maze, r, c, er, ec, asf + dirS[d], dir, dirS);
        }

        return count;
    }

    // =====================================================================================================

    // 0 -> Empty cell, 1 -> Blocked cell
    public static int floodFill(int[][] board, int sr, int sc, String asf, int[][] dir, String[] dirS) {
        if (sr == board.length - 1 && sc == board[0].length - 1) {
            System.out.println(asf);
            return 1;
        }

        board[sr][sc] = 1;
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= board.length - 1 && c <= board[0].length - 1 && board[r][c] == 0)
                count += floodFill(board, r, c, asf + dirS[d], dir, dirS);
        }

        board[sr][sc] = 0;
        return count;
    }

    // =====================================================================================================

    // 0 -> Empty cell, 1 -> Blocked cell, rad cannaot start from 0, since circle with 0 radius is undefined
    public static int floodFill_Jump(int[][] board, int sr, int sc, int n, int m, String asf, int[][] dir, String[] dirS, int Radius) {
        if (sr == n-1 && sc == m-1) {
            System.out.println(asf);
            return 1;
        }

        board[sr][sc] = 1;
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Radius; rad++) {
                int r = sr + rad*dir[d][0];
                int c = sc + rad*dir[d][1];
    
                // Check within boundary conditions or not
                if (r >= 0 && c >= 0 && r <= n-1 && c <= m-1) {
                    if (board[r][c] == 0)
                        count += floodFill_Jump(board, r, c, n, m, asf + rad + dirS[d], dir, dirS, Radius);
                }
                else break;
            }
            
        }

        board[sr][sc] = 0;
        return count;
    }

    // =====================================================================================================

    // Return as soon as we find any one of the paths
    // 0 -> Empty cell, 1 -> Blocked cell
    public static boolean floodFill_(int[][] board, int sr, int sc, String asf, int[][] dir, String[] dirS) {
        if (sr == board.length - 1 && sc == board[0].length - 1) {
            System.out.println(asf);
            return true;
        }

        boolean res = false;
        board[sr][sc] = 1;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= board.length - 1 && c <= board[0].length - 1 && board[r][c] == 0)
                res = res || floodFill_(board, r, c, asf + dirS[d], dir, dirS);
        }

        board[sr][sc] = 0;
        return res;
    }

    // =====================================================================================================

    // Instead use maze path method fully same, this is just for distinguishing
    // Rat in a maze => 1 -> Empty cell, 0 -> Blocked cell
    // Count number of ways to reach destination in a maze => 0 -> Empty cell, -1 -> Blocked cell
    public static int ratInAMaze(int[][] board, int sr, int sc, String asf, int[][] dir, String[] dirS, int[][] temp) {
        int n = board.length, m = board[0].length;

        if (sr == n-1 && sc == m-1) {
            // displayBoard(temp);
            return 1;
        }

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= n-1 && c <= m-1 && board[r][c] == 0)
                count += ratInAMaze(board, r, c, asf + dirS[d], dir, dirS, temp);
        }

        return count;
    }

    // =====================================================================================================

    // 1 - block, 0 - free
    public static int floodFill_LongestPath(int[][] board, int sr, int sc, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n-1 && sc == m-1) return 0;

        board[sr][sc] = 1;
        int longest = Integer.MIN_VALUE;

        for (int d = 0;d < dir.length;d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 0) {
                int res = floodFill_LongestPath(board, r, c, dir);
                if (res != Integer.MIN_VALUE && res+1 > longest)
                    longest = res+1;
            }
        }

        board[sr][sc] = 0;
        return longest;
    }

    // =====================================================================================================
    
    // 1 - block, 0 - free
    public static int floodFill_SmallestPath(int[][] board, int sr, int sc, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n-1 && sc == m-1) return 0;

        board[sr][sc] = 1;
        int smallest = Integer.MAX_VALUE;

        for (int d = 0;d < dir.length;d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 0) {
                int res = floodFill_SmallestPath(board, r, c, dir);
                if (res != Integer.MAX_VALUE && res+1 < smallest)
                    smallest = res+1;
            }
        }

        board[sr][sc] = 0;
        return smallest;
    }

    // =====================================================================================================

    public static boolean knightsTour(int[][] board, int sr, int sc, int[][] dir, int move) {
        int n = board.length, m = board[0].length;
        board[sr][sc] = move;

        if (move == n*m-1)
            return true;

        for (int d = 0;d < dir.length;d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= n-1 && c <= m-1 && board[r][c] == -1) {
                boolean res = knightsTour(board, r, c, dir, move+1);
                if (res == true) 
                    return true;
            }
        }

        board[sr][sc] = -1;
        return false;
    }

    public static void main(String[] args) {

        // int[][] dir = { {0, 1}, {1, 1}, {1, 0} };
        // String[] dirS = { "h", "d", "v" };
        // int[][] maze = new int[3][3];
        // System.out.println(mazePath(maze, 0, 0, maze.length-1, maze[0].length-1 ,"", dir, dirS));

        // ===============================================================

        // 2 directions
        // int[][] dir2 = { {0, 1}, {1, 0} };
        // String[] dirS2 = { "h", "v" };

        // 4 directions
        int[][] dir4 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        String[] dirS4 = { "t", "r", "d", "l" };
        // 8 directions
        int[][] dir8 = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
        String[] dirS8 = { "t", "E", "r", "S", "d", "N", "l", "W" };

        // int[][] board = { { 0, 0, 0 }, { 1, 0, 0}, {0, 1, 0} };
        int[][] board = new int[3][3];
        
        Arrays.fill(board, -1);
        int[][] dirK = { {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1} };
        System.out.println(knightsTour(board, 0, 0, dirK, 0));

        // ===============================================================

        // int board[][] = { { 1, 0, 0, 0 },
        //                  { 1, 1, 0, 1 },
        //                  { 0, 1, 0, 0 },
        //                  { 1, 1, 1, 1 } };
        // int[][] temp = new int[n][m];
        
        // System.out.println(ratInAMaze(board, 0, 0, "", dir2, dirS2, temp));

        // ===============================================================

        // Count number of ways to reach destination in a maze - For this problem, refer mazePath problem

        // ===============================================================

        // System.out.println(floodFill_LongestPath(board, 0, 0, dir4));

        // ===============================================================

        // System.out.println(floodFill_SmallestPath(board, 0, 0, dir4));

        // ===============================================================

    }
}
