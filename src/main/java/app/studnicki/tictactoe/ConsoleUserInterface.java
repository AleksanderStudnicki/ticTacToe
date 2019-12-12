package app.studnicki.tictactoe;

class ConsoleUserInterface implements UserInterface {

    ConsoleUserInterface(){

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
