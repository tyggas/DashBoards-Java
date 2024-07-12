package com.example.Monitoramento.controller;

public class Maquina {
    private String name;
    private String status;
    private double oee;
    private int piecesProduced;
    private int piecesCurrent;
    private int piecesPlanned;

    public Maquina(String name, String status, double oee, int piecesProduced, int piecesCurrent, int piecesPlanned) {
        this.name = name;
        this.status = status;
        this.oee = oee;
        this.piecesProduced = piecesProduced;
        this.piecesCurrent = piecesCurrent;
        this.piecesPlanned = piecesPlanned;
    }

    // Getters e Setters
    public int getPiecesPlanned() {
        return piecesPlanned;
    }

    public void setPiecesPlanned(int piecesPlanned) {
        this.piecesPlanned = piecesPlanned;
    }

    public int getPiecesCurrent() {
        return piecesCurrent;
    }

    public void setPiecesCurrent(int piecesCurrent) {
        this.piecesCurrent = piecesCurrent;
    }

    public int getPiecesProduced() {
        return piecesProduced;
    }

    public void setPiecesProduced(int piecesProduced) {
        this.piecesProduced = piecesProduced;
    }

    public double getOee() {
        return oee;
    }

    public void setOee(double oee) {
        this.oee = oee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
