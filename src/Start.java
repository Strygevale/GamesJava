import javax.swing.*;
import java.awt.*;

public class Start {
    public static void main(String[] args) {
        System.out.println("Start...");
        JFrame window = new JFrame("Крестики-нолики");//создание окна
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//программа закрывается на крестик
        window.setSize(400,400);//размер окна
        window.setLayout(new BorderLayout());//менедже компановки для других элементов
        window.setLocationRelativeTo(null);//окно по центру экрана
        window.setVisible(true);//видимость окна
        TicTacToe game = new TicTacToe();//Создаем объект класса TicTacToe
        window.add(game);// Добавляем его в окно

    }
}
