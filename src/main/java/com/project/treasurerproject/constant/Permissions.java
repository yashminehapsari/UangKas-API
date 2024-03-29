package com.project.treasurerproject.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {
    A_READ("admin::read"), A_CREATE("admin::create"), A_UPDATE("admin::update"), A_DELETE("admin::delete"),
    M_READ("member::read"), M_CREATE("member::create"), M_UPDATE("member::update"), M_DELETE("member::delete");

    private final String permission;
}
