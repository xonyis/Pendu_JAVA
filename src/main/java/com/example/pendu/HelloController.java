package com.example.pendu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Text penduText;

    @FXML
    private TextField responseInput;

    @FXML
    private Text txt;

    @FXML
    private Text wordDisplay;
    @FXML
    private Text resultText;
    private String mot;

    private StringBuilder motSecret = new StringBuilder("");

    private int penduPosition = 0;


    ArrayList<String> penduFigure = new ArrayList<>(Arrays.asList(
            """
            +---+
            |   |
                |
                |
                |
                |
          =========""",
            """
            +---+
            |   |
            O   |
                |
                |
                |
          =========""",
            """
            +---+
            |   |
            O   |
            |   |
                |
                |
          =========""",
            """
            +---+
            |   |
            O   |
           /|   |
                |
                |
          =========""",
            """
            +---+
            |   |
            O   |
           /|\\  |
                |
                |
          =========""",
            """
            +---+
            |   |
            O   |
           /|\\  |
           /    |
                |
          =========""",
            """
            +---+
            |   |
            O   |
           /|\\  |
           / \\  |
                |
          ========="""
    ));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        penduText.setText(penduFigure.get(penduPosition));
    }
    @FXML
    void getTextInput(ActionEvent event) {
        if (mot == null) {
            getMot();
            mot = responseInput.getText();
            setUpMot();
            responseInput.clear();
        } else {
            playTurn();
        }
    }

    public void setUpMot() {
        int longMot = mot.length();
        for (int i = 0; i < longMot; i++) {
            motSecret.append("*");
        }

        wordDisplay.setText(String.valueOf(motSecret));
    }

    public void playTurn() {
        String responseInput = this.responseInput.getText();
        ArrayList<Integer> position =  new ArrayList<>();
        char[] motChar = mot.toCharArray();
        char letterChar = mot.charAt(0);

        if (mot.equals(responseInput)) {
            resultText.setText("Vous avez gagner");

        }

        if (mot.contains(responseInput)) {
            for (int i = 0; i < mot.length(); i++) {
                if(motChar[i] == letterChar){
                    position.add(i);
                }
            }

            position.forEach(pos ->{
                motSecret.setCharAt(pos, letterChar);
            });
            wordDisplay.setText(String.valueOf(motSecret));
        } else {
            penduText.setText(penduFigure.get(++penduPosition));
            if (penduPosition == 6) {
                System.out.println("Game over");
            }
        }
    }
    @FXML
    void reset(ActionEvent event) {
        mot = null;
        motSecret = null;
        penduPosition = 0;
        penduText.setText(penduFigure.get(0));
        resultText.setText("");
    }

    void getMot() {
        String line = new String();
        int j = 0;
        try {
            //lire le fichier file.txt
            FileReader file = new FileReader("./liste_francais.txt");
            BufferedReader buffer = new BufferedReader(file);

            // parcourir le fichier
            for (j = 1; j < 10; j++) {
                // Si le numéro de la ligne = 5 récupérer la ligne
                if (j == 5) {
                    line = buffer.readLine();
                } else
                    buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(line);
    }

}
