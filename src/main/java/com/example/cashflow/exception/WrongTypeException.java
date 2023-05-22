package com.example.cashflow.exception;

public class WrongTypeException extends Exception {

    public WrongTypeException(){
        super("Tipo incorreto, digite \"credit\" ou \"debit\"");
    }

}
