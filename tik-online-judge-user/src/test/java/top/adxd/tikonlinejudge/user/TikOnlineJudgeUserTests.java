package top.adxd.tikonlinejudge.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wait_light
 * @create 2021/9/6
 */
//@Slf4j
//@SpringBootTest
public class TikOnlineJudgeUserTests {

    @Test
    public void aaaaa() {
        boolean can = true;
        for (int i = 0; i < 10; i++) {
            can &= i % 2 == 0;
        }
        System.out.println(can);
    }

    @Test
    public void aaa() {
        Solution solution = new Solution();
        char[][] chars = new char[4][];
        chars[0] = "XOXOXO".toCharArray();
        chars[1] = "OXOXOX".toCharArray();
        chars[2] = "XOXOXO".toCharArray();
        chars[3] = "OXOXOX".toCharArray();
//        chars[4] = "XOOOOOOOX".toCharArray();
//        chars[5] = "XXOOXOXOX".toCharArray();
//        chars[6] = "OOOXOOOOO".toCharArray();
//        chars[7] = "OOOXOOOOO".toCharArray();
//        chars[8] = "OOOOOXXOO".toCharArray();
//        ,,,,,,,,
//        chars[0] =  "OOXOOXOOOOOOOOXXOXOO".toCharArray();
//        chars[1] =  "OXOOOOXXOOOXOOXXOOOO".toCharArray();
//        chars[2] =  "XOXXOOOOOXOOOXOXXXXO".toCharArray();
//        chars[3] =  "XXOOOOOOOOXOOXXXXXXX".toCharArray();
//        chars[4] =  "OOOOOXOOOXXXXOXOOOOO".toCharArray();
//        chars[5] =  "OOOOOXOOOXXXXOXOOOOO".toCharArray();
//        chars[6] =  "OXXXOOOXOXOXOOXOXXOO".toCharArray();
//        chars[7] =  "OOOOOOOOXXXOOXXOOOOO".toCharArray();
//        chars[8] =  "OXXOOOOOXOXXOXXOOXOO".toCharArray();
//        chars[9] =  "OOXXXOOXOOOOOOOXXXOX".toCharArray();
//        chars[10] = "XXOOOXOXOOOXXOOXOXXO".toCharArray();
//        chars[11] = "OOOOOOXOXXOOXOXXXXOX".toCharArray();
//        chars[12] = "OOXXOOXOXOOXOOXOOXOX".toCharArray();
//        chars[13] = "OXOOOOOXOOOOOOXXXOOO".toCharArray();
//        chars[14] = "OOXOXOOXXOXXXOOXXOOX".toCharArray();
//        chars[15] = "XOXOXOXOOOOOOOXOOXXO".toCharArray();
//        chars[16] = "XOXXXOXOOOOOOXOOOOXX".toCharArray();
//        chars[17] = "XOOOOXOOOOOOXOOOOOXX".toCharArray();
//        chars[18] = "OOOOOOXOOOXOXOXXOXOX".toCharArray();
//        chars[19] = "XOOXOOOOOOXOOOOOXXXX".toCharArray();
//        chars[20] = "OOOXXOOOOOOOOXOOOXOO".toCharArray();

        char x = 'X', o = 'O';
//        char[][] chars = {{x, x, x, x}, {x, o, o, x}, {x, x, o, x}, {o, x, x, x}};
        solution.solve(chars);
        for (int i = 0; i < chars.length; i++) {
            System.out.println(Arrays.toString(chars[i]));
        }

    }

    class Solution {
        //方向
        private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int m, n;
        private char[][] bo;

        public void solve(char[][] board) {
            //基本数据处理
            if (board == null || board.length <= 0 || board[0].length <= 0) {
                return;
            }
            m = board.length;
            n = board[0].length;
            bo = board;
            for (int i = 0; i < m; i++) {
                //处于边界的0，将其可以到达的O全部改成X
                if (board[i][0] == 'O') {
                    dfs(i, 0);
                }
                if (board[i][n - 1] == 'O') {
                    dfs(i, n - 1);
                }
            }
            for (int i = 0; i < n; i++) {
                //处于边界的0，将其可以到达的O全部改成X
                if (board[0][i] == 'O') {
                    dfs(0, i);
                }
                if (board[m - 1][i] == 'O') {
                    dfs(m - 1, i);
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'C') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public void dfs(int x, int y) {
            //边界判断
            if (x < 0 || x >= m || y < 0 || y >= n) {
                return;
            }
            if (bo[x][y] == 'X' || bo[x][y] == 'C') {
                return;
            }
            bo[x][y] = 'C';
            //向四个方向搜索
            for (int i = 0; i < direction.length; i++) {
                int tx = x + direction[i][0];
                int ty = y + direction[i][1];
                dfs(tx, ty);
            }
        }
    }
}
