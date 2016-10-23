package ru.nivanov.conway;

class Cell {
    private int x, y;
    private boolean alive = false;
    private boolean nextStatus;
    static final int SIZE = 3;


    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    void nextState(CellMap parent){
        int aroundAlive = countAround(parent);
        if (alive){
            nextStatus = !(aroundAlive < 2 || aroundAlive > 3);
        }else {
            nextStatus = aroundAlive == 3;
        }
    }

    void apply(){
        alive = nextStatus;
    }

    private int countAround(CellMap parent){
        int aliveAround = 0;
        if (parent.alive(x - 1, y - 1))
            aliveAround++;
        if (parent.alive(x, y - 1))
            aliveAround++;
        if (parent.alive(x + 1, y - 1))
            aliveAround++;
        if (parent.alive(x - 1, y))
            aliveAround++;
        if (parent.alive(x + 1, y))
            aliveAround++;
        if (parent.alive(x - 1, y + 1))
            aliveAround++;
        if (parent.alive(x + 1, y + 1))
            aliveAround++;
        if (parent.alive(x, y + 1))
            aliveAround++;
        return aliveAround;
    }

    void handOfGod(){
        alive = true;
    }

    boolean isAlive() {
        return alive;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

}
