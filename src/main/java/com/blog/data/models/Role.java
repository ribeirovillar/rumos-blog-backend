package com.blog.data.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public static final class RoleBuilder {
        private UUID id;
        private String name;
        private Collection<User> users;

        private RoleBuilder() {
        }

        public static RoleBuilder aRole() {
            return new RoleBuilder();
        }

        public RoleBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public RoleBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder withUsers(Collection<User> users) {
            this.users = users;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            role.setUsers(users);
            return role;
        }
    }
}
