import java.util.Stack;

public class Memento {
    private StringBuilder text;
    private Stack<StringBuilder> history;

    public Memento() {
        text = new StringBuilder();
        history = new Stack<>();
        makeSnapshot();
    }

    public Memento addPhrase(String phrase) {
        makeSnapshot();
        text.append(phrase + "\n");
        return this;
    }

    private void makeSnapshot() {
        history.push(new StringBuilder(text));
    }

    public Memento undo() {
        if (!history.isEmpty()) text = history.pop();
        return this;
    }

    public void edit(int i) {
        for (int j = i; j < 6; j++) {
            undo();
        }
    }

    public StringBuilder getText() {
        return text;
    }

    public String toString() {
        return text.toString();
    }
}
