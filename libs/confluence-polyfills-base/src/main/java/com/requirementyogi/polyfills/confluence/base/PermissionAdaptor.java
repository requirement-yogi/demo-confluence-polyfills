package com.requirementyogi.polyfills.confluence.base;

import com.atlassian.confluence.core.ConfluenceEntityObject;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.user.ConfluenceUser;

public interface PermissionAdaptor {
    boolean isConfluenceAdministrator(ConfluenceUser user);
    boolean canViewObject(ConfluenceEntityObject ceo, ConfluenceUser user);
    boolean canEditObject(ConfluenceEntityObject ceo, ConfluenceUser user);
    boolean canRemoveObject(ConfluenceEntityObject ceo, ConfluenceUser user);
    boolean canAdminObject(ConfluenceEntityObject ceo, ConfluenceUser user);
    boolean canCreateObject(Space ceo, ConfluenceUser user);
    boolean canExportSpace(Space ceo, ConfluenceUser user);
}
