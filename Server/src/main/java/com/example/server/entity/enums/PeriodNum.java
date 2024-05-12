package com.example.server.entity.enums;

public enum PeriodNum{
    UNO(1),
    DOS(2);

    private final int valor;

    PeriodNum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
