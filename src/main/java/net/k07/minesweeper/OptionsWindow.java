package net.k07.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class OptionsWindow extends JFrame {

    public static JTextField rowField = new JTextField();
    public static JTextField colField = new JTextField();
    public static JTextField mineField = new JTextField();

    private static MSWindow parent;

    public OptionsWindow(MSWindow parent) {
        this.parent = parent;

        this.setTitle("Options");
        this.setLayout(new GridLayout(5, 1));

        JPanel sub1 = new JPanel();
        JPanel sub2 = new JPanel();
        JPanel sub3 = new JPanel();

        JButton playButton = new JButton("Play");
        this.add(new JLabel("Set Options"));

        sub1.setLayout(new GridLayout(1, 2));
        sub1.add(new JLabel("Rows: "));
        sub1.add(rowField);
        this.add(sub1);

        sub2.setLayout(new GridLayout(1, 2));
        sub2.add(new JLabel("Columns: "));
        sub2.add(colField);
        this.add(sub2);

        sub3.setLayout(new GridLayout(1, 2));
        sub3.add(new JLabel("Mines: "));
        sub3.add(mineField);
        this.add(sub3);

        playButton.addActionListener( e -> {
                if (!validateInput(rowField, "Rows", 5, 20)) {
                    return;
                } else if (!validateInput(colField, "Rows", 5, 30)) {
                    return;
                }

                int rowsFromField = Integer.parseInt(rowField.getText());
                int colsFromField =  Integer.parseInt(colField.getText());

                if (!validateInput(mineField, "Mines", 1, (rowsFromField * colsFromField) - 1)) {
                    return;
                }

                int minesFromField =  Integer.parseInt(mineField.getText());

                parent.rows = rowsFromField;
                parent.cols = colsFromField;
                parent.mines = minesFromField;

                parent.options.rows = rowsFromField;
                parent.options.columns = colsFromField;
                parent.options.mines = minesFromField;

                parent.game.newGame();
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        this.add(playButton);
        this.setSize(125, 175);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static boolean validateInput(JTextField field, String fieldName, int min, int max) {
        String text = field.getText();
        int value = -1;

        try {
            value = Integer.parseInt(text);
        }
        catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + ": numeric values only!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(value < min) {
            JOptionPane.showMessageDialog(null, fieldName + ": value too low! Minimum: " + min, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(value > max) {
            JOptionPane.showMessageDialog(null, fieldName + ": value too high! Maximum: " + max, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}