package jantardosfilosofos;


import javax.swing.JFrame;

public class JantarDosFilosofos extends JFrame {

    public JantarDosFilosofos() {
        add(new Mesa());
        setTitle("Jantar dos Filósofos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(410, 410);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new JantarDosFilosofos();
    }
}
