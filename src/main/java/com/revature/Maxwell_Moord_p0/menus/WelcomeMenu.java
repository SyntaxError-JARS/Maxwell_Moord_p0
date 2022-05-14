package com.revature.Maxwell_Moord_p0.menus;
import java.util.*;
import com.revature.Maxwell_Moord_p0.util.AppState;
public class WelcomeMenu extends Menu {



    public WelcomeMenu(Scanner kb) {
        super(kb);
    }

    public void render() {
        String header = "Welcome to Maxwell's Magnificent Minecraft Modpack Maker";
        String option1 = "1) Login";
        String option2 = "2) Create an Account";
        String option3 = "3) Exit";

        System.out.printf("%s \n %s \n %s \n %s \n ", header, option1, option2, option3);
        System.out.print("\n Select number from above\n > ");
        String userSelection = kb.next();

        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected create account...");
                CreateAccountMenu createAccountMenu = new CreateAccountMenu(kb);
                //AppState.shutdown();
                try {
                    createAccountMenu.render();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "3":
                System.out.println("User has selected exit...");
                AppState.shutdown();
                break;
            default:
                System.out.println("No valid user input provide");
                break;
        }
    }

}
