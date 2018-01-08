package pl.sda.nbp.view;

import java.util.Scanner;

public class MainMenu implements Displayable {

    private static final String EXIT_COMMAND = "0";
    private static final String QUOTE_MENU = "1";

    public void display() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!EXIT_COMMAND.equals(command)) {
            System.out.println("1)Pobierz notowania");
            System.out.println("0)Koniec");
            System.out.print(">");
            command = scanner.nextLine();

            Displayable displayable = selectDisplayable(command);
            if (displayable != null) {
                displayable.display();
            }
        }
    }

    private Displayable selectDisplayable(String command) {
        if (QUOTE_MENU.equals(command)) {
            return new QuoteMenu();
        }
        return null;
    }

}
