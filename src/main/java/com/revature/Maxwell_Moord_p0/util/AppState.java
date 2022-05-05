package com.revature.Maxwell_Moord_p0.util;
import java.util.*;
import com.revature.Maxwell_Moord_p0.menus.WelcomeMenu;
public class AppState {
    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;


    public AppState() {
        isRunning = true;
        Scanner kb = new Scanner(System.in);
        this.welcomeMenu = new WelcomeMenu(kb);
    }

    public void startup(){

        while(isRunning){
            welcomeMenu.welcome();
        }
    }

    public static void shutdown() {
        isRunning = false;
    }
}
