package com.mazdaeur.windup.utils;


import java.util.HashSet;
import java.util.Set;

public class Pomlibs {
    private Set<Pomlib> pomlibs = new HashSet<>();

    public void add(Pomlib pomlib) {
        pomlibs.add(pomlib);
    }

    public Pomlib getPomlib(MavenId id) {
        for (Pomlib pomlib : pomlibs) {
            if (pomlib.getId().equals(id)) {
                return pomlib;
            }
        }
        return null;
    }

    public Set<MavenId> getPomLibIds() {
        Set<MavenId> pomLibNames = new HashSet<>();
        for (Pomlib pomlib : pomlibs) {
            pomLibNames.add(pomlib.getId());
        }
        return pomLibNames;
    }

    public Set<Pomlib> getPomlibsForDependency(MavenId dependency) {
        Set<Pomlib> depPomlibs = new HashSet<>();
        for (Pomlib pomlib : pomlibs) {
            if (pomlib.getDependencies().contains(dependency)) {
                depPomlibs.add(pomlib);
            }
        }
        return depPomlibs;
    }

    public Set<MavenId> getPomLibDependencies() {
        Set<MavenId> pomlibNames = getPomLibIds();
        Set<MavenId> dependencies = new HashSet<>();
        for (Pomlib pomlib : pomlibs) {
            for (MavenId dependency : pomlib.getDependencies()) {
                if (!pomlibNames.contains(dependency)) {
                    dependencies.add(dependency);
                }
            }
        }
        return dependencies;
    }

    public Set<Pomlib> getEmbeddedPomlibs() {
        Set<Pomlib> embeddedPomlibs = new HashSet<>();
        Set<MavenId> pomLibIds = getPomLibIds();
        for (Pomlib pomlib : pomlibs) {
            for (MavenId dependency : pomlib.getDependencies()) {
                if (pomLibIds.contains(dependency)) {
                    embeddedPomlibs.add(getPomlib(dependency));
                }
            }
        }
        return embeddedPomlibs;
    }

    public Set<Pomlib> getParentPomlibsFor(Pomlib embeddedPomlib) {
        Set<Pomlib> parentPomlibs = new HashSet<>();
        for (Pomlib pomlib : pomlibs) {
            if (pomlib.getDependencies().contains(embeddedPomlib.getId())) {
                parentPomlibs.add(pomlib);
            }
        }
        return parentPomlibs;
    }
}
