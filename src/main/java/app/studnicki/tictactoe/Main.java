package app.studnicki.tictactoe;

public class Main {
    public static void main(String[] args){

        ConsoleUserInterface ui = new ConsoleUserInterface();

        for(String filepath : args){
            GameBoard gameBoard = new GameBoard(filepath, ui);
            ui.showMessage(new ResultChecker(gameBoard).winner().toString());
        }
    }
}
