/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author colo7
 */
public class RangoExtendsAnchor extends VBox {

    private Label ini;
    private double ValorIni;
    private Label fin;
    private double ValorFin;
    private Color color;

    public RangoExtendsAnchor() {
    }

    public RangoExtendsAnchor(String fin, String ini, Color color) {
        this.ini = new Label("Inicio: " + ini);
        this.fin = new Label("Fin: " + fin);
        this.ValorIni = Double.valueOf(ini);
        this.ValorFin = Double.valueOf(fin);
        this.color = color;
        this.getChildren().add(this.ini);
        this.getChildren().add(this.fin);
        BackgroundFill fill = new BackgroundFill(this.color, null, null);
        this.setBackground(new Background(fill));
    }

    public Label getIni() {
        return ini;
    }

    public void setIni(Label ini) {
        this.ini = ini;
    }

    public Label getFin() {
        return fin;
    }

    public void setFin(Label fin) {
        this.fin = fin;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getValorIni() {
        return ValorIni;
    }

    public void setValorIni(double ValorIni) {
        this.ValorIni = ValorIni;
    }

    public double getValorFin() {
        return ValorFin;
    }

    public void setValorFin(double ValorFin) {
        this.ValorFin = ValorFin;
    }

}
