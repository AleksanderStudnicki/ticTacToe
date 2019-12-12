package app.studnicki.tictactoe;

class ResultChecker {
    private boolean[][] toProceed;

    ResultChecker(GameBoard gameBoard){
        toProceed = gameBoard.board;
    }

    Value winner() throws IllegalArgumentException{

        Value winner = null;

        for(int i = 0; i < toProceed.length; i++){
            Value temp = checkHorizontally(i);
            if(winner != Value.NOBODY && temp != Value.NOBODY){
                throw new IllegalArgumentException("More than one winner!!!");
            } else{
                winner = temp;
            }
            temp = checkVertically(i);
            if(winner != Value.NOBODY && temp != Value.NOBODY){
                throw new IllegalArgumentException("More than one winner!!!");
            } else{
                winner = temp;
            }
        }

        return winner;
    }

    private Value checkVertically(int index){

        boolean b = toProceed[0][index];

        for(int i = 1; i < toProceed.length; i++){
            if(toProceed[i][index] != b){
                return Value.NOBODY;
            }
        }

        return b ? Value.X : Value.O;
    }

    private Value checkHorizontally(int index){

        boolean b = toProceed[index][0];

        for(int i = 1; i < toProceed.length; i++){
            if(toProceed[index][i] != b){
                return Value.NOBODY;
            }
        }

        return b ? Value.X : Value.O;
    }
}
