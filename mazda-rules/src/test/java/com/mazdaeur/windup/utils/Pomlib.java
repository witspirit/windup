package com.mazdaeur.windup.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Pomlib {
    public static final String DEFAULT_SCOPE = "compile";

    private MavenId id;
    private String scope;
    private Set<MavenId> dependencies = new HashSet<>();

    public Pomlib(String id) {
        this.id = MavenId.parse(id);
        this.scope = DEFAULT_SCOPE;
    }

    public Pomlib withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public Pomlib contains(String... deps) {
        for (String dependency : deps) {
            dependencies.add(MavenId.parse(dependency));
        }
        return this;
    }

    public MavenId getId() {
        return id;
    }

    public String getScope() {
        return scope;
    }

    public boolean hasDefaultScope() {
        return scope.equals(DEFAULT_SCOPE);
    }

    public Set<MavenId> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return id.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pomlib pomlib = (Pomlib) o;

        if (!id.equals(pomlib.id)) return false;
        if (!scope.equals(pomlib.scope)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + scope.hashCode();
        return result;
    }
}
