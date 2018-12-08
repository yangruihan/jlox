package com.yrh.lox;

import java.util.List;

class LoxClass implements LoxCallable {

    final String name;

    LoxClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("<class '%s'>", this.name);
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        return instance;
    }

}
