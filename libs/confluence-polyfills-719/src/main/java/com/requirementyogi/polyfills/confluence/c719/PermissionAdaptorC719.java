package com.requirementyogi.polyfills.confluence.c719;

import com.atlassian.confluence.core.ConfluenceEntityObject;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.user.User;
import com.requirementyogi.polyfills.confluence.base.PermissionAdaptor;

public class PermissionAdaptorC719 implements PermissionAdaptor {

    private final PermissionManager permissionManager;

    public PermissionAdaptorC719(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    /**
     * Returns true if user is a confluence administrator
     * <p>
     * Equivalent to hasPermission(user, Permission.ADMINISTER, TARGET_APPLICATION);
     */
    public boolean isConfluenceAdministrator(ConfluenceUser user) {
        return permissionManager.isConfluenceAdministrator(user);
    }

    /**
     * Returns true if the user has the permission to {@link Permission#VIEW} the object {@link ConfluenceEntityObject}
     */
    public boolean canViewObject(ConfluenceEntityObject ceo, ConfluenceUser user) {
        return permissionManager.hasPermission((User) user, Permission.VIEW, ceo);
    }

    /**
     * Returns true if the user has the permission to {@link Permission#EDIT} the object {@link ConfluenceEntityObject}
     */
    public boolean canEditObject(ConfluenceEntityObject ceo, ConfluenceUser user) {
        return permissionManager.hasPermission(user, Permission.EDIT, ceo);
    }

    /**
     * Returns true if the user has the permission to {@link Permission#REMOVE} the object {@link ConfluenceEntityObject}
     */
    public boolean canRemoveObject(ConfluenceEntityObject ceo, ConfluenceUser user) {
        return permissionManager.hasPermission(user, Permission.REMOVE, ceo);
    }

    /**
     * Returns true if the user has the permission to {@link Permission#ADMINISTER} the object {@link ConfluenceEntityObject}
     */
    public boolean canAdminObject(ConfluenceEntityObject ceo, ConfluenceUser user) {
        return permissionManager.hasPermission(user, Permission.ADMINISTER, ceo);
    }

    /**
     * Returns true if the user has the permission to create a page in that space
     */
    public boolean canCreateObject(Space ceo, ConfluenceUser user) {
        return permissionManager.hasCreatePermission(user, ceo, Page.class);
    }

    public boolean canExportSpace(Space space, ConfluenceUser user) {
        return permissionManager.hasPermission(user, Permission.EXPORT, space);
    }
}
