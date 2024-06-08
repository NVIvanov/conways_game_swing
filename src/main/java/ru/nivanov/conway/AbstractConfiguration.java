package ru.nivanov.conway;

import java.util.Optional;

abstract class AbstractConfiguration implements Configuration{
    @Override
    public void apply(CellMap cellMap, int x, int y) {
        Cell[] cells = getActiveCells();
        for (Cell cell: cells){
            Optional<Cell> cellOnMap = cellMap.getCell(x + cell.getX(), y + cell.getY());
            cellOnMap.ifPresent(Cell::handOfGod);
        }
    }

    abstract Cell[] getActiveCells();
}
