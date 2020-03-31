package de.db8.os.sample.fakerabbit.openfeign;

import de.db8.os.sample.fakerabbit.openfeign.github.Contributor;
import de.db8.os.sample.fakerabbit.openfeign.github.Default;
import de.db8.os.sample.fakerabbit.openfeign.github.GitHub;
import de.db8.os.sample.fakerabbit.openfeign.github.Issue;
import de.db8.os.sample.fakerabbit.openfeign.hello.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A stupid wrapper hiding the feign client before the controller.
 */
@Service
public class FeignService {

    private final GitHub gitHub;
    private final Hello hello;

    @Autowired
    public FeignService(GitHub gitHub, Hello hello) {
        this.gitHub = gitHub;
        this.hello = hello;
    }

    public List<Contributor> gitHubContributors(String owner, String repo) {
        String pOwner = null == owner ? Default.OWNER : owner;
        String pRepo = null == repo ? Default.REPO : repo;
        return gitHub.contributors(pOwner, pRepo);
    }

    public List<Issue> gitHubIssues(String owner, String repo) {
        String pOwner = null == owner ? Default.OWNER : owner;
        String pRepo = null == repo ? Default.REPO : repo;
        return gitHub.issues(pOwner, pRepo);
    }

    public String hello(String name) {
        String pName = null == name ? Default.OWNER : name;
        return hello.hello(pName).getMessage();
    }


}
