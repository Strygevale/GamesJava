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

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        /*Проверяем если нажата левая кнопка мыши */
        if(event.getButton() == MouseEvent.BUTTON1){
            int x = event.getX();//координаты x клика
            int y = event.getY();//координаты y клика
            //переводим координаты в индексы ячейки в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            //если выбранная ячейка пуста
            if(field[i][j] == FIELD_EMPTY){
                //Проверка чей ход, если x, ставтим крестик, если 0, то нолик

                field[i][j] = isXturn ? FIELD_X : FIELD_0;
                //меняем ход
                isXturn = !isXturn;
                repaint();//перерисовка компонента, вызовет метод intComponent
                /*Определяем победителя*/
                int res = checkState();
                if(res!=0){
                    if(res == FIELD_0 * 3){
                        //победил 0
                        JOptionPane.showMessageDialog(this, "Выиграл нолик!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    } else if(res == FIELD_X * 3){
                        JOptionPane.showMessageDialog(this, "Выиграл крестик!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ничья!", "Ничья!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame(); //перезапускаем игру
                    repaint(); //перерисовываем поля
                }
            }
        }
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
    /*определяем победителя*/
    int checkState(){

        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x =  dw;
        int y =  dh;
        // если три вряд по диагонали
        int diagonal = 0;
        int diagonal2 = 0;
        for (int i = 0; i < 3; i++){
            diagonal += field[i][i];//сумма значений по диагонали от левого угла
            diagonal2 += field[i][2 - i]; //сумма значений по диагонали от правого угла
        }
        //если по первой диагонали стоят одни крестики или нолики
        if(diagonal == FIELD_X * 3 || diagonal == FIELD_0 * 3){return diagonal;}
        //если по второй диагонали стоят одни крестики или нолики
        if(diagonal2 == FIELD_X * 3 || diagonal2 == FIELD_0 * 3){return  diagonal2;}

        int checkI;
        int checkJ;
        boolean hasEmpty = false;
        //проверяем каждый ряд
        for(int i = 0; i < 3; i++){
            checkI = 0;
            checkJ = 0;
            for(int j = 0; j < 3; j++){
                //суммируем знаки в текущем ряду
                if(field[i][j] == 0){
                    hasEmpty = true;
                }
                checkI += field[i][j];
                checkJ += field[j][i];
            }
            if (checkI == FIELD_X * 3 || checkI == FIELD_0 * 3){return checkI;}
            if (checkJ == FIELD_X * 3 || checkJ == FIELD_0 * 3){return checkJ;}
            }
        if(hasEmpty) return 0; else return -1;
        }

    }


