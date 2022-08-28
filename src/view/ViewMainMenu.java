package view;

import config.Config;
import controller.UserController;
import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;


public class ViewMainMenu {

    UserController userController = new UserController();

    public void menu() {
        System.out.println("MENU");
        System.out.println("1.Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        switch (Config.getValidInteger()) {
            case 1:
                formRegister();
                break;
            case 2:
                formLogin();
                break;
            case 3:
                System.exit(0);
        }
        menu();
    }

    private void formLogin() {
        System.out.println("*****FORM LOGIN*****");
        System.out.println("Enter user name");
        String username = Config.scanner().nextLine();
        System.out.println("Enter password");
        String password = Config.scanner().nextLine();

        SignInDTO signInDTO = new SignInDTO(username, password);
        ResponseMessenger messenger = userController.login(signInDTO);

        switch (messenger.getMessage()) {
            case "username_not_found":
                System.out.println("Username not found");
                break;
            case "password_wrong":
                System.out.println("Password incorrect");
            case "success":
                System.out.println("Login successful");
                new ViewHome();
        }
    }

    private void formRegister() {
        System.out.println("*****FORM REGISTER*****");
        //id
        int id = userController.getLastId();
        //name
        String name;
        while (true) {
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid name, try again!");
            }
        }
        //username
        String username;
        while (true) {
            System.out.println("Enter username");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }
        //password
        String password;
        while (true) {
            System.out.println("Enter password");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }
        //email
        String email;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            if (email.matches("[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-z]+(\\.[a-z]+){1,2}")) {
                break;
            } else {
                System.out.println("Invalid email, try again!");
            }
        }

        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, password, email);
        ResponseMessenger messenger = userController.register(signUpDTO);
        switch (messenger.getMessage()) {
            case "username_exists":
                System.err.println("Username already exists!");
                break;
            case "email_exists":
                System.err.println("Email already exists!");
                break;
            case "success":
                System.out.println("Register success!");
        }
    }

}
