package ru.nivanov.conway;

class DotConfiguration extends AbstractConfiguration {

    @Override
    Cell[] getActiveCells() {
        return new Cell[]{new Cell(0, 0)};
    }
}
