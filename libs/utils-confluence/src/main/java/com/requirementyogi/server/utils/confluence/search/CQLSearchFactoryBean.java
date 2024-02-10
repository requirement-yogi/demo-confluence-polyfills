package com.requirementyogi.server.utils.confluence.search;

import com.playsql.utils.BackwardsCompatibilityUtils;
import com.requirementyogi.polyfills.confluence.base.cql.CQLSearch;
import com.requirementyogi.polyfills.confluence.c719.cql.CQLSearchC713;
import com.requirementyogi.polyfills.confluence.c850.cql.CQLSearchC720;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import static com.playsql.utils.BackwardsCompatibilityUtils.BUILD_NUMBER_CONFLUENCE_7_20;

public class CQLSearchFactoryBean implements FactoryBean<CQLSearch> {

    private final AutowireCapableBeanFactory beanFactory;

    public CQLSearchFactoryBean(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public CQLSearch getObject() throws Exception {
        if (BackwardsCompatibilityUtils.isConfluenceVersionAbove(BUILD_NUMBER_CONFLUENCE_7_20)) {
            return beanFactory.createBean(CQLSearchC720.class);
        } else {
            try {
                return beanFactory.createBean(CQLSearchC713.class);
            } catch (Exception e) {
                return beanFactory.createBean(CQLSearchC720.class);
            }
        }
    }

    @Override
    public Class<CQLSearch> getObjectType() {
        return CQLSearch.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
