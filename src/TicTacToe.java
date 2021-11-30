import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {

    public static final int FIELD_EMPTY = 0;//пустое поле
    public static final int FIELD_X = 10;//поле с крестиком
    public static final int FIELD_0 = 200;//поле с ноликом
    int[][] field;//массив игрового поля
    boolean isXturn; //показывает чей сейчас ход

    /*Очищаем игровое поле*/
    public void initGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = FIELD_EMPTY; //массив 3 на 3 клетки заполняем 0
            }
        }
        isXturn = true;
    }

    public TicTacToe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);//получение события от мыши
        field = new int[3][3];
        initGame();
    }


    /*Отрисовка элементов на поле*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        drawXO(g);
        drawGrid(g);
    }

    /*Создание линий для  игрового поля*/
    void drawGrid(Graphics graphics){
        int w = getWidth();//ширина игрового поля
        int h = getHeight();//высота игрового поля
        int dw = w / 3;//ширина одной ячейки
        int dh = h / 3;//высота одной ячейки
        graphics.setColor(Color.BLACK);
        for(int i = 1; i < 3; i++){
            graphics.drawLine(0, dh * i, w, dh * i);//горизонтальная линия
            graphics.drawLine(dw * i, 0, dw * i, h);//вертикальная линия
        }
    }
    /*отрисовка крестиков*/
    void drawX(int i, int j, Graphics graphics){
       // graphics.setFont(new Font("serif", Font.BOLD,24));
        graphics.setColor(Color.RED);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x, y, x + dw, y + dh);//линия от левого верхнего угла в правый нижний
        graphics.drawLine(x, y + dh, x + dw, y);//линия от левого нижнего угла в правый верхний
    }

    /*отрисовка ноликов*/
    void drawO(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLUE);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);

    }

    /*метод просматривает в массиве ходы*/
    void drawXO(Graphics graphics){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                //если в данной ячейке крестик, то рисуем его
                if(field[i][j] == FIELD_X){
                    drawX(i, j, graphics);
                }
                //если нолик
                else if(field[i][j] == FIELD_0){
                    drawO(i, j, graphics);
                }
            }
        }
    }

    }


