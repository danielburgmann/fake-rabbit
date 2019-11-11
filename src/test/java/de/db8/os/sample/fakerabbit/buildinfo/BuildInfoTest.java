package de.db8.os.sample.fakerabbit.buildinfo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BuildInfoTest {

    @TestConfiguration
    static class BuildInfoServiceTestConfiguration {
        @Bean
        public BuildInfoService goodBuildInfoService() {
            return new BuildInfoService(new DefaultResourceLoader(),
                    "classpath:/de/db8/os/sample/fakerabbit/buildinfo/BuildInfoTest.properties");
        }

        @Bean
        public BuildInfoService badBuildInfoService() {
            return new BuildInfoService(new DefaultResourceLoader(),
                    "classpath:/not-existing.properties");
        }
    }

    @Autowired
    BuildInfoService goodBuildInfoService;
    @Autowired
    BuildInfoService badBuildInfoService;

    @Test
    public void testGoodBuildInfo() {
        BuildInfo buildInfo = goodBuildInfoService.getBuildInfo();

        assertEquals(buildInfo.getBranch(), "master");
        assertEquals(buildInfo.getVersion(), "0.0.1-SNAPSHOT");
        assertEquals(buildInfo.getBuildHostname(), "example-host");
        assertEquals(buildInfo.getCommitId(), "abcdef");
        assertEquals(buildInfo.getBuildUser(), "builder");
        assertEquals(buildInfo.getBuildJavaVendor(), "Oracle Corporation");
        assertEquals(buildInfo.getBuildDate(), "2019-03-08 00:24:04,685 +0100");
        assertEquals(buildInfo.getBuildOsVersion(), "4.18.0-15-generic");
        assertEquals(buildInfo.getBuildOsName(), "Linux");
        assertEquals(buildInfo.getBuildJavaVersion(), "11.0.2");

    }

    @Test
    public void testBadBuildInfo() {
        BuildInfo buildInfo = badBuildInfoService.getBuildInfo();

        assertEquals(buildInfo.getBranch(), "undefined");
        assertEquals(buildInfo.getVersion(), "undefined");
        assertEquals(buildInfo.getBuildHostname(), "undefined");
        assertEquals(buildInfo.getCommitId(), "undefined");
        assertEquals(buildInfo.getBuildUser(), "undefined");
        assertEquals(buildInfo.getBuildJavaVendor(), "undefined");
        assertEquals(buildInfo.getBuildDate(), "undefined");
        assertEquals(buildInfo.getBuildOsVersion(), "undefined");
        assertEquals(buildInfo.getBuildOsName(), "undefined");
        assertEquals(buildInfo.getBuildJavaVersion(), "undefined");

    }

}
