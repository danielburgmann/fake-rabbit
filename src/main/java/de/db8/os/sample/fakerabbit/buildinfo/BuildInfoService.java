package de.db8.os.sample.fakerabbit.buildinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Service
public class BuildInfoService {

    private static final Logger log = LoggerFactory.getLogger(BuildInfoService.class);

    private static final String BUILD_INFO_PATH_DEFAULT = "classpath:/build-info.properties";

    private final String buildInfoPath;
    private BuildInfo buildInfo;

    private final ResourceLoader resourceLoader;

    @Autowired
    public BuildInfoService(ResourceLoader resourceLoader) {
        this(resourceLoader, BUILD_INFO_PATH_DEFAULT);
    }

    public BuildInfoService(ResourceLoader resourceLoader, String buildInfoPath) {
        this.buildInfoPath = buildInfoPath;
        this.resourceLoader = resourceLoader;
    }

    public BuildInfo getBuildInfo() {
        return buildInfo;
    }

    @PostConstruct
    private void loadBuildInfo() {
        Resource buildInfoResource = resourceLoader.getResource(buildInfoPath);
        Properties buildInfoProps = new Properties();
        try {
            buildInfoProps.load(buildInfoResource.getInputStream());
            buildInfo = new BuildInfo(buildInfoProps);
        }
        catch (IOException e) {
            log.error("Could not load build info file \"{}\" from classpath. Have no data to fill BuildInfo object.", buildInfoPath);
            buildInfo = new BuildInfo();
        }

    }

}
