package app.studnicki.tictactoe;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

class GameBoard {

    boolean[][] board;
    UserInterface ui;

    public GameBoard(String path, UserInterface ui) {
        File file = new File(path);
        if (file.isFile()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                int lines = lines(bufferedReader);
                inputStream.getChannel().position(0);
                board = parseBoard(bufferedReader, lines);
                ui.showMessage(toString());
            } catch (IOException ex) {
                ui.showMessage(path + ": can't parse a file!");
            } catch (IllegalArgumentException ex) {
                ui.showMessage(path + ": " + ex.getMessage());
            }
        } else {
            ui.showMessage(path + ": it is not a file!");
        }
    }

    private boolean[][] parseBoard(BufferedReader bf, int lines) throws IOException, IllegalArgumentException {
        String line = bf.readLine();

        boolean[][] board = new boolean[lines][lines];

        int index = 0;

        while (line != null) {
            if (line.length() == lines) {
                char[] chars = line.toCharArray();
                board[index] = parseSingleLine(chars);
            } else {
                throw new IllegalArgumentException("File contains lines with higher amount of characters than lines!");
            }
            line  = bf.readLine();
            index++;
        }

        return board;
    }

    private int lines(BufferedReader bf) throws IOException {
        int lines = 0;
        while (bf.readLine() != null) {
            lines++;
        }
        return lines;
    }

    private boolean[] parseSingleLine(char[] chars) throws IllegalArgumentException {
        boolean[] output = new boolean[chars.length];

        IntStream.range(0, chars.length)
                .forEach(i -> {
                    char c = chars[i];
                    if (c != 'O' && c != 'X') {
                        throw new IllegalArgumentException("File contains illegal characters!");
                    } else {
                        if (c == 'X') {
                            output[i] = true;
                        }
                    }
                });
        return output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(boolean[] arr : board){
            for(boolean b : arr){
                if(b){
                    sb.append("X");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
