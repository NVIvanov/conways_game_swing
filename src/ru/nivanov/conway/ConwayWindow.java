package ru.nivanov.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ConwayWindow extends JFrame {
    private final CellMap map;
    private final JPanel drawPanel = new DrawPanel();
    private final int size;
    private LifeCycle lifeCycle = new LifeCycle(200);
    private JComboBox<String> comboBox;

    ConwayWindow(int size){
        this.size = size;
        map = new CellMap(size);
        initComponents(size);
        lifeCycle.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lifeCycle.stop();
                super.windowClosed(e);
            }
        });
    }

    private void initComponents(int size) {
        setSize(Cell.SIZE * size + 40, Cell.SIZE * size + 40);
        setTitle("Conway's Game");
        addPanel(size);
        addPauseButton();
        addSpinner();
        addComboBox();
        setLocationRelativeTo(null);
        startRedrawPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addPanel(int size) {
        drawPanel.setSize(Cell.SIZE * size, Cell.SIZE * size);
        drawPanel.setDoubleBuffered(true);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / Cell.SIZE;
                int y = e.getY() / Cell.SIZE;
                ConfigurationFactory.getConfiguration((String) comboBox.getSelectedItem())
                        .apply(map, x, y);
            }
        });
        getContentPane().add(drawPanel, BorderLayout.CENTER);
    }

    private void addPauseButton(){
        JButton jButton = new JButton("Пауза");
        jButton.addActionListener(e -> {
            if (lifeCycle.paused())
                lifeCycle.resume();
            else
                lifeCycle.pause();
        });
        getContentPane().add(jButton, BorderLayout.SOUTH);
    }

    private void addSpinner(){
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(500, 1, 1000, 50));
        getContentPane().add(spinner, BorderLayout.EAST);
        spinner.addChangeListener(e -> lifeCycle.setDelay(Integer.valueOf(String.valueOf(spinner.getValue()))));
    }

    private void addComboBox(){
        comboBox = new JComboBox<>();
        ConfigurationFactory.getConfigurationNames().forEach(comboBox::addItem);
        getContentPane().add(comboBox, BorderLayout.NORTH);
    }

    private void startRedrawPanel(){
        Timer timer = new Timer(10, e -> drawPanel.updateUI());
        timer.setRepeats(true);
        timer.start();
    }

    private class DrawPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (map.alive(i, j))
                        g.fillRect(i * Cell.SIZE, j * Cell.SIZE, Cell.SIZE, Cell.SIZE);
        }
    }

    private class LifeCycle implements Runnable{
        private volatile int delay;
        private volatile boolean stop, sleep;

        LifeCycle(int delay){
            this.delay = delay;
        }

        void start(){
            new Thread(this).start();
        }

        @Override
        public void run() {
            while (!stop){
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!sleep)
                    map.nextState();
            }
        }

        void stop(){
            stop = true;
        }

        void setDelay(int delay){
            this.delay = delay;
        }

        void pause() {
            sleep = true;
        }

        void resume() {
            sleep = false;
        }

        boolean paused(){
            return sleep;
        }
    }
}
