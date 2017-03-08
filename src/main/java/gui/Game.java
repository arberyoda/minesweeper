package gui;

import game.Field;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Arber Heqimi
 * @version 1.0
 */
public class Game
{
    private Home home;
    private JFrame frameGame;
    private JPanel panelInfo;
    private JPanel panelGame;
    private JButton buttonRestart;
    private JTextField textFieldOpenFields;
    private JTextField textFieldClosedFields;
    private JTextField textFieldNumberOfMines;
    private JTextField textFieldNumberOfLives;
    private JLabel labelOpenFields;
    private JLabel labelClosedFields;
    private JLabel labelNumberOfMines;
    private JLabel labelNumberOfLives;

    public Game(Home home)
    {
        this.home = home;
        this.frameGame = new JFrame("Minesweeper");
        this.panelInfo = new JPanel();
        this.panelGame = new JPanel();
        this.buttonRestart = new JButton("Restart");
        this.textFieldOpenFields = new JTextField();
        this.textFieldClosedFields = new JTextField();
        this.textFieldNumberOfMines = new JTextField();
        this.textFieldNumberOfLives = new JTextField();
        this.labelOpenFields = new JLabel("Open fields", SwingConstants.CENTER);
        this.labelClosedFields = new JLabel("Closed fields", SwingConstants.CENTER);
        this.labelNumberOfMines = new JLabel("Mines", SwingConstants.CENTER);
        this.labelNumberOfLives = new JLabel("Lives", SwingConstants.CENTER);

        paint();
    }

    public void paint()
    {
        frameGame.setResizable(false);
        frameGame.setSize(800, 600);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGame.setLayout(new BorderLayout(5, 5));

        Field field = new Field(home, Game.this);
        field.placeMines();

        textFieldOpenFields.setPreferredSize(new Dimension(0, getFrameGame().getHeight()/10));
        textFieldOpenFields.setEditable(false);
        textFieldOpenFields.setText(String.valueOf(field.getOpenFields()));
        textFieldOpenFields.setHorizontalAlignment(JTextField.CENTER);

        textFieldClosedFields.setPreferredSize(new Dimension(0, getFrameGame().getHeight()/10));
        textFieldClosedFields.setEditable(false);
        textFieldClosedFields.setText(String.valueOf(field.getFieldsTotal() - field.getOpenFields()));
        textFieldClosedFields.setHorizontalAlignment(JTextField.CENTER);

        textFieldNumberOfMines.setPreferredSize(new Dimension(0, getFrameGame().getHeight()/10));
        textFieldNumberOfMines.setEditable(false);
        textFieldNumberOfMines.setText(String.valueOf(field.getMinesTotal()));
        textFieldNumberOfMines.setHorizontalAlignment(JTextField.CENTER);

        textFieldNumberOfLives.setPreferredSize(new Dimension(0, getFrameGame().getHeight()/10));
        textFieldNumberOfLives.setEditable(false);
        textFieldNumberOfLives.setText(String.valueOf(field.getPlayer().getLives()));
        textFieldNumberOfLives.setHorizontalAlignment(JTextField.CENTER);

        panelInfo.setLayout(new GridLayout(1, 9, 5, 5));
        panelInfo.add(labelOpenFields);
        panelInfo.add(textFieldOpenFields);
        panelInfo.add(labelClosedFields);
        panelInfo.add(textFieldClosedFields);
        panelInfo.add(buttonRestart);
        panelInfo.add(labelNumberOfMines);
        panelInfo.add(textFieldNumberOfMines);
        panelInfo.add(labelNumberOfLives);
        panelInfo.add(textFieldNumberOfLives);

        buttonRestart.addActionListener(new KeyListener());

        home.getFrameHome().dispose();

        frameGame.add(panelInfo, BorderLayout.NORTH);
        frameGame.add(panelGame, BorderLayout.CENTER);
        frameGame.setLocationRelativeTo(null);
        frameGame.setVisible(true);
    }

    public JFrame getFrameGame()
    {
        return frameGame;
    }

    public JPanel getPanelGame()
    {
        return panelGame;
    }

    public JTextField getTextFieldOpenFields()
    {
        return textFieldOpenFields;
    }

    public JTextField getTextFieldClosedFields()
    {
        return textFieldClosedFields;
    }

    public JTextField getTextFieldNumberOfMines()
    {
        return textFieldNumberOfMines;
    }

    public JTextField getTextFieldNumberOfLives()
    {
        return textFieldNumberOfLives;
    }

    public void restartGame()
    {
        frameGame.dispose();
        new Game(home);
    }

    class KeyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            restartGame();
        }
    }
}