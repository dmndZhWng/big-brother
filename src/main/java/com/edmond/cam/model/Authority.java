package com.edmond.cam.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    ADMIN {
        @Override
        public String getAuthority() {
            return null;
        }
    },
    READ {
        @Override
        public String getAuthority() {
            return null;
        }
    },
    ;
}
