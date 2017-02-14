package com.gooagoo.jackson.core.json;

import com.gooagoo.jackson.core.Version;
import com.gooagoo.jackson.core.Versioned;
import com.gooagoo.jackson.core.util.VersionUtil;

/**
 * Automatically generated from PackageVersion.java.in during
 * packageVersion-generate execution of maven-replacer-plugin in
 * pom.xml.
 */
public final class PackageVersion implements Versioned {
    public final static Version VERSION = VersionUtil.parseVersion(
        "2.8.2", "com.gooagoo.jackson.core", "jackson-core");

    @Override
    public Version version() {
        return VERSION;
    }
}
