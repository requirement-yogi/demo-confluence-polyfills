package com.requirementyogi.server.utils.confluence.compat;

import com.playsql.utils.BackwardsCompatibilityUtils;
import com.requirementyogi.polyfills.confluence.base.PermissionAdaptor;
import com.requirementyogi.polyfills.confluence.c719.PermissionAdaptorC719;
import com.requirementyogi.polyfills.confluence.c720.PermissionAdaptorC720;
import com.requirementyogi.polyfills.confluence.c850.PermissionAdaptorC850;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import static com.playsql.utils.BackwardsCompatibilityUtils.BUILD_NUMBER_CONFLUENCE_7_20;
import static com.playsql.utils.BackwardsCompatibilityUtils.BUILD_NUMBER_CONFLUENCE_8_5;

public class PermissionAdaptorFactoryBean implements FactoryBean<PermissionAdaptor> {

    private final AutowireCapableBeanFactory beanFactory;

    public PermissionAdaptorFactoryBean(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public PermissionAdaptor getObject() throws Exception {
        if (BackwardsCompatibilityUtils.isConfluenceVersionAbove(BUILD_NUMBER_CONFLUENCE_8_5)) {
            return beanFactory.createBean(PermissionAdaptorC850.class);
        } else if (BackwardsCompatibilityUtils.isConfluenceVersionAbove(BUILD_NUMBER_CONFLUENCE_7_20)) {
            return beanFactory.createBean(PermissionAdaptorC720.class);
        } else {
            return beanFactory.createBean(PermissionAdaptorC719.class);
        }
    }

    @Override
    public Class<PermissionAdaptor> getObjectType() {
        return PermissionAdaptor.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
