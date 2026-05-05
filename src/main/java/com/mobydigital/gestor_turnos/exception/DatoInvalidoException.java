package com.mobydigital.gestor_turnos.exception;

public class DatoInvalidoException extends RuntimeException {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}