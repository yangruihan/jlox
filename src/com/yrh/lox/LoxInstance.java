package com.yrh.lox;

import java.util.HashMap;
import java.util.Map;

class LoxInstance {

    private LoxClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        LoxFunction method = klass.findMethod(this, name.lexeme);
        if (method != null)
            return method;

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }

    @Override
    public String toString() {
        return String.format("<instance of '%s'>", klass.name);
    }

}
