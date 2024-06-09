package ma.game.tictactoemultiplayer.Services;

import com.almasb.fxgl.core.fsm.State;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;

public class GameService {
    public static void clearBoardButtonsContent(ArrayList<Button> buttons){
        buttons.forEach(button -> {
            button.setText("");
            button.setDisable(false);
        });
    }

    public static void makeMove(Button button, String xORo){
        button.setText(xORo);
        button.setDisable(true);
    }

    public static void makeMoveAI(ArrayList<Button> buttons){
        int moveIndex = GameService.findBestMove(buttons, "X");
        GameService.makeMove(buttons.get(moveIndex), "X");
    }

    public static String[] getBoardState(ArrayList<Button> buttons){
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons.get(i).getText();
        }
        return board;
    }

    ////////////////////////////////////////////////
    public static int findBestMove(ArrayList<Button> buttons, String currentPlayer) {
        String[] board = getBoardState(buttons);
        int bestScore = (currentPlayer.equals("X")) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i].isEmpty()) {
                board[i] = currentPlayer;
                int score = minimax(board, 0, !currentPlayer.equals("X"));
                board[i] = "";
                if ((currentPlayer.equals("X") && score > bestScore) || (currentPlayer.equals("O") && score < bestScore)) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private static int minimax(String[] board, int depth, boolean isMaximizing) {
        String result = checkWinner(board);
        if (!result.isEmpty()) {
            return score(result, depth);
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i].isEmpty()) {
                    board[i] = "X";
                    int score = minimax(board, depth + 1, false);
                    board[i] = "";
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i].isEmpty()) {
                    board[i] = "O";
                    int score = minimax(board, depth + 1, true);
                    board[i] = "";
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    private static int score(String result, int depth) {
        if (result.equals("X")) {
            return 10 - depth;
        } else if (result.equals("O")) {
            return depth - 10;
        }
        return 0;
    }

    private static String checkWinner(String[] board) {
        String[] lines = new String[]{
                board[0] + board[1] + board[2],
                board[3] + board[4] + board[5],
                board[6] + board[7] + board[8],
                board[0] + board[3] + board[6],
                board[1] + board[4] + board[7],
                board[2] + board[5] + board[8],
                board[0] + board[4] + board[8],
                board[2] + board[4] + board[6]
        };

        for (String line : lines) {
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (String s : board) {
            if (s.isEmpty()) {
                return "";
            }
        }

        return "Tie";
    }
    //////////////////////////////////////////////////////////

    public static String checkGameOver(ArrayList<Button> buttons){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText();
                case 1 -> buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText();
                case 2 -> buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText();
                case 3 -> buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText();
                case 4 -> buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText();
                case 5 -> buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText();
                case 6 -> buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText();
                case 7 -> buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText();
                default -> null;
            };

            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!buttons.get(i).getText().isEmpty()){
                count++;
            }
        }

        if (count == 9) {
            return "Tie";
        }

        return "";
    }
}
