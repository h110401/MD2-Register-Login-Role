package view;

import config.Config;
import controller.UserController;
import model.User;

public class ViewHome {

    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();

    public ViewHome() {
        switch (currentUser.getRoleNameOfUser()) {
            case ADMIN:
                menuAdmin();
                break;
            case USER:
                menuUser();
                break;
            case PM:
                menuPm();
                break;
        }
    }

    private void menuPm() {
        System.out.println("ViewHome");
        System.out.println("Hello PM: " + currentUser.getName());
        System.out.println("1. Log out");
        int id = Config.getValidInteger();
        if (id == 1) {
            userController.logout();
            new ViewMainMenu().menu();
            return;
        }
        menuPm();
    }

    private void menuUser() {
        System.out.println("ViewHome");
        System.out.println("Hello USER: " + currentUser.getName());
        System.out.println("1. Log out");
        int id = Config.getValidInteger();
        if (id == 1) {
            userController.logout();
            new ViewMainMenu().menu();
            return;
        }
        menuUser();
    }

    private void menuAdmin() {
        System.out.println("ViewHome");
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Log out");
        int id = Config.getValidInteger();
        if (id == 1) {
            userController.logout();
            new ViewMainMenu().menu();
            return;
        }
        menuAdmin();
    }

}
