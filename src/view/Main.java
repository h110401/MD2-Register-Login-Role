package view;

import controller.UserController;
import model.User;

public class Main {

    public Main() {
        User currentUser = new UserController().getCurrentUser();
        if (currentUser == null) {
            new ViewMainMenu().menu();
        } else {
            new ViewHome();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
