import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Memento memento = new Memento();

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        int i = 1;
        while (i != 7) {
            menu.showMenu(i);
            if (i != 6) {
                memento.addPhrase(scanner.nextLine());
                i++;
            } else {
                i = menu.saveOrEdit();
                if (i < 5) memento.edit(i);
            }
        }
    }
}
