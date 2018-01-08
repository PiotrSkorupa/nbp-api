package pl.sda.nbp;

import pl.sda.nbp.view.Displayable;
import pl.sda.nbp.view.MainMenu;
import pl.sda.nbp.view.QuoteMenu;

public class Main {

    public static void main(String[] args) {
        Displayable mainMenu = new MainMenu();

        mainMenu.display();
    }
}
