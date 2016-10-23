package ru.nivanov.conway;

import java.util.Optional;

abstract class AbstractConfiguration implements Configuration{
    @Override
    public void apply(CellMap cellMap, int x, int y) {
        Cell[] cells = getActiveCells();
        for (Cell cell: cells){
            Optional<Cell> cellOnMap = cellMap.getCell(x + cell.getX(), y + cell.getY());
            if (cellOnMap.isPresent())
                cellOnMap.get().handOfGod();
        }
    }

    abstract Cell[] getActiveCells();
}
