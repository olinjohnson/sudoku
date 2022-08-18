package io.github.olinjohnson.sudoku;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

public class Main {

    private static int SCREEN_HEIGHT = 710;
    private static int SCREEN_WIDTH = 600;
    private static int MARGIN = 6;
    private static int CELL_WIDTH = (SCREEN_WIDTH - (2 * MARGIN)) / 9;
    static JTextField[][] cells = new JTextField[9][9];

    public static void main(String[] args) {

        JFrame window = new JFrame();

        JLabel text = new JLabel();
        text.setText("SUDOKU SOLVER");
        text.setBounds(SCREEN_WIDTH / 2 - 110, 10, 300, 40);

        File font_file = new File("Staatliches-Regular.ttf");
        Font new_font = new Font("Default", Font.PLAIN, 30);
        try {
            new_font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(38f);
            text.setFont(new_font);
        }catch(FontFormatException | IOException ex){
            System.out.println("Something went wrong importing font");
        }
        window.add(text);

        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {

                JTextField cell = new JTextField();
                cell.setBounds(i * CELL_WIDTH + MARGIN, x * CELL_WIDTH + MARGIN + 50, CELL_WIDTH, CELL_WIDTH);
                Border border = BorderFactory.createLineBorder(window.getBackground(), 1, true);
                cell.setBorder(border);
                cell.setFont(new_font.deriveFont(30f));
                cell.setHorizontalAlignment(JTextField.CENTER);
                cells[i][x] = cell;
                window.add(cell);

            }
        }

        JButton buttonSolve = new JButton("Solve");
        buttonSolve.setFont(new_font.deriveFont(16f));
        buttonSolve.setBounds(250, 650, 100, 25);
        buttonSolve.addActionListener(e -> {
            solvePuzzle();
        });
        window.add(buttonSolve);

        JButton buttonReset = new JButton("Reset");
        buttonReset.setFont(new_font.deriveFont(16f));
        buttonReset.setBounds(400, 650, 100, 25);
        buttonReset.addActionListener(e -> {
            for (int i = 0; i < 9; i++) {
                for (int x = 0; x < 9; x++) {
                    cells[i][x].setText("");
                }
            }
        });
        window.add(buttonReset);

        JButton buttonGenerate = new JButton("Generate");
        buttonGenerate.setFont(new_font.deriveFont(16f));
        buttonGenerate.setBounds(100, 650, 100, 25);
        buttonGenerate.addActionListener(e -> {
            Generator g = new Generator();
            Puzzle newP = g.generatePuzzle();
            updateBoard(newP);
        });
        window.add(buttonGenerate);

        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setLayout(null);
        window.setResizable(false);
        window.setVisible(true);

    }

    static void solvePuzzle() {
        Puzzle puzzle = new Puzzle();
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                String text = cells[i][x].getText();
                int newText = 0;

                try {
                    newText = Integer.parseInt(text);
                } catch (Exception e) {
                }

                puzzle.coords[i][x] = newText;
            }
        }

        puzzle.coordsComp = puzzle.coords.clone();

        Solve solveClass = new Solve();
        Puzzle solvedPuzzle = solveClass.run(puzzle);
        updateBoard(solvedPuzzle);

    }

    static void updateBoard(Puzzle p) {
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                cells[i][x].setText("" + p.coords[i][x] + "");
            }
        }
    }

}
