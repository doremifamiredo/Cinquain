import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {
    char choice, ignore;

    void showMenu(int i) {
        switch (i) {
            case 1:
                System.out.println("1. Одно ключевое слово, определяющее тему синквейна:");
                break;
            case 2:
                System.out.println("2. Два прилагательных, характеризиющие тему:");
                break;
            case 3:
                System.out.println("3. Три глагола, описывающие действия по теме:");
                break;
            case 4:
                System.out.println("4. Короткое предложение (4-5 слов),\nвыражающее личное отношение автора:");
                break;
            case 5:
                System.out.println("5. Одно слово, выступающее как вывод или обобщение");
                break;
            case 6:
                System.out.println("Вот какой синквейн получился");
                System.out.println(Main.memento.getText());
        }
    }

    public int saveOrEdit() throws IOException {
        System.out.println("Сохранить = 0\n или номер строки, с которой начать редактирование");
        choice = (char) System.in.read();
        do {
            ignore = (char) System.in.read();
        } while (ignore != '\n');
        if (choice == '0') {
            save();
            return 7;
        } else {
            switch (choice) {
                case '1':
                    return 1;
                case '2':
                    return 2;
                case '3':
                    return 3;
                case '4':
                    return 4;
                default:
                    return 5;
            }
        }
    }

    void save() {
        try {
            File file = new File("Cinquain.txt");
            if (!file.createNewFile()) {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("Cinquain.txt");
            writer.write(Main.memento.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
