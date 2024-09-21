package org.example.calculateneoflex.services.checks;

public interface CheckValidity<T> {
    boolean isValid(T value);
}
