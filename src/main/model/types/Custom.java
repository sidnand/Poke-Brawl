package model.types;

import model.Type;

import java.util.ArrayList;
import java.util.List;

public class Custom implements Type {
    private final String name;
    private List<Type> superEffective;
    private List<Type> notEffective;

    public Custom(String name, List<Type> superEffective, List<Type> notEffective) {
        this.name = name;
        this.superEffective = superEffective;
        this.notEffective = notEffective;
    }

    @Override
    public int damageOn(Type t) {
        for (Type i : superEffective) {
            if (i.getName().equals(t.getName())) {
                return 40;
            }
        }

        for (Type i : notEffective) {
            if (i.getName().equals(t.getName())) {
                return 10;
            }
        }

        return 20;
    }

    @Override
    public String getName() {
        return name;
    }
}
