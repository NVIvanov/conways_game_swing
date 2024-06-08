package ru.nivanov.conway;

class OscillatorConfiguration extends AbstractConfiguration {

    @Override
    Cell[] getActiveCells() {
        return new Cell[]{
                new Cell(0, 2),
                new Cell(1, 1),
                new Cell(1, 3),
                new Cell(2, 0),
                new Cell(2, 4),
                new Cell(3, 0),
                new Cell(3, 1),
                new Cell(3, 2),
                new Cell(3, 3),
                new Cell(3, 4),
                new Cell(5, 0),
                new Cell(5, 1),
                new Cell(5, 2),
                new Cell(5, 3),
                new Cell(5, 4),
                new Cell(6, 0),
                new Cell(6, 4),
                new Cell(7, 1),
                new Cell(7, 3),
                new Cell(8, 2)
        };
    }
}
