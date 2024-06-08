package ru.nivanov.conway;

import javax.swing.*;

public class StartWindow extends JFrame{
    private final JTextField fieldSize = new JTextField();
    private final JButton ok = new JButton("Start");

    private StartWindow(){
        addContent();
        ok.addActionListener(e -> {
            new ConwayWindow(Integer.parseInt(fieldSize.getText())).setVisible(true);
            StartWindow.this.setVisible(false);
        });
    }

    private void addContent(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start");

        JLabel sizeLabel = new JLabel();
        sizeLabel.setText("Field size");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

        GroupLayout.SequentialGroup h1 = layout.createSequentialGroup();
        GroupLayout.ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);

        h1.addContainerGap();

        h2.addComponent(ok, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);

        GroupLayout.SequentialGroup h3 = layout.createSequentialGroup();
        h3.addComponent(sizeLabel);
        h3.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
        h3.addComponent(fieldSize, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);

        h2.addGroup(h3);
        h1.addGroup(h2);

        h1.addContainerGap();

        hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup v1 = layout.createSequentialGroup();
        v1.addContainerGap();
        GroupLayout.ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        v2.addComponent(sizeLabel);
        v2.addComponent(fieldSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        v1.addGroup(v2);
        v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        v1.addComponent(ok, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE);
        v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        v1.addContainerGap();

        vGroup.addGroup(v1);
        layout.setVerticalGroup(vGroup);
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartWindow().setVisible(true));
    }
}
