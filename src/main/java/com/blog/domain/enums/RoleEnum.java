package com.blog.domain.enums;

import java.util.UUID;

public enum RoleEnum {
    ROLE_ADMIN(UUID.fromString("08f93b36-ebde-4d93-8d9f-354acad094ae")),
    ROLE_USER(UUID.fromString("7d21a9af-64d2-4e2e-b21f-96ee4323dc37"));

    private final UUID id;

    RoleEnum(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
