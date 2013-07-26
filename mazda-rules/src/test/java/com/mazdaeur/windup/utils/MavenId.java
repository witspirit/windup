package com.mazdaeur.windup.utils;


public class MavenId {
    public static final String DEFAULT_GROUP = "com.mazdaeur";

    private String group;
    private String name;

    public MavenId(String group, String name) {
        this.group = group;
        this.name = name;
    }

    public static MavenId parse(String id) {
        String group = DEFAULT_GROUP;
        String name;
        String[] nameComponents = id.split(":");
        if (nameComponents.length == 1) {
            name = nameComponents[0];
        } else if (nameComponents.length == 2) {
            group = nameComponents[0];
            name = nameComponents[1];
        } else {
            throw new IllegalArgumentException("Maven id should be of the format <group>:<name> or <name>");
        }

        return new MavenId(group, name);
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return group+":"+name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MavenId mavenId = (MavenId) o;

        if (!group.equals(mavenId.group)) return false;
        if (!name.equals(mavenId.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = group.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
