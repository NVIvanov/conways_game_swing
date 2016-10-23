package ru.nivanov.conway;

import java.util.Optional;

class CellMap {
    private Cell[][] cells;
    private final int size;

    Optional<Cell> getCell(int x, int y){
        if (x >= size || x < 0 || y < 0 || y >= size)
            return Optional.empty();
        return Optional.of(cells[x][y]);
    }

    boolean alive(int x, int y) {
        return !(x < 0 || y < 0 || x >= size || y >= size) && cells[x][y].isAlive();
    }

    CellMap(int size){
        this.size = size;
        cells = new Cell[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = new Cell(i, j);
    }

    void nextState(){
        for(Cell[] cellRow: cells)
            for (Cell cell: cellRow)
                cell.nextState(this);
        for(Cell[] cellRow: cells)
            for (Cell cell: cellRow)
                cell.apply();
    }


}
