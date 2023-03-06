package com.assignment.horse.track.service;

import java.security.Permission;

class NoExitSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkExit(int status) {
        super.checkExit(status);
        throw new SecurityException(String.valueOf(status));
    }
}

