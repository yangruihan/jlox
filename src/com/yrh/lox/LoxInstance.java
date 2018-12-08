package com.yrh.lox;

class LoxInstance {

    private LoxClass klass;

    LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return String.format("<instance of '%s'>", klass.name);
    }

}
