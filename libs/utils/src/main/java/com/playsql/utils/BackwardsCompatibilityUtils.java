package com.playsql.utils;

import com.atlassian.config.ApplicationConfiguration;
import com.atlassian.confluence.setup.BootstrapManager;
import com.atlassian.spring.container.ContainerManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.lang.reflect.*;
import java.util.Objects;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.playsql.utils.BaseUtils.equal;
import static com.playsql.utils.BaseUtils.intOf;

public class BackwardsCompatibilityUtils {

    public final static Logger LOGGER = Log4Yogi.getLogger(BackwardsCompatibilityUtils.class);


    public static final int BUILD_NUMBER_CONFLUENCE_7_20 = 8901;
    public static final int BUILD_NUMBER_CONFLUENCE_8_5 = 9012;

    public static boolean isConfluenceVersionAbove(int buildNumber) {
        BootstrapManager bootstrapManager = ContainerManager.getComponent("bootstrapManager", BootstrapManager.class);
        if (bootstrapManager != null) {
            ApplicationConfiguration config = bootstrapManager.getApplicationConfig();
            if (config != null) {
                return intOf(config.getBuildNumber()) >= buildNumber;
            }
        }
        // Since we didn't expect bootstrapManager or config to be null, that means we didn't expect that version
        // of Confluence, and that means the version must surely be above
        return true;
    }
}
