package de.db8.os.sample.fakerabbit.openfeign;

import de.db8.os.sample.fakerabbit.openfeign.github.Contributor;
import de.db8.os.sample.fakerabbit.openfeign.github.Default;
import de.db8.os.sample.fakerabbit.openfeign.github.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *  The controller using the FeignService wrapping the usage of the generated OpenFeign clients.
 */
@Controller
public class FeignController {

    private final FeignService feignService;

    @Autowired
    public FeignController(FeignService feignService) {
        this.feignService = feignService;
    }

    // the usage page

    @RequestMapping(value = "/feign", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String feign() {
        return "openfeign.html";
    }

    // fake-rabbit's own hello api

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello(
            @RequestParam(name = "name", required = false, defaultValue = Default.OWNER) String name
    ) {
        return feignService.hello(name);
    }

    // github api examples

    @RequestMapping(value = "/feign/github/contributors", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String gitHubContributors(
            @RequestParam(name = "owner", required = false, defaultValue = Default.OWNER) String owner,
            @RequestParam(name = "repo", required = false, defaultValue = Default.REPO) String repo
            ) {
        List<Contributor> contributors = feignService.gitHubContributors(owner, repo);
        StringBuilder sb = new StringBuilder();
        contributors.forEach(it -> sb.append(it.getLogin())
                .append(" (")
                .append(it.getContributions())
                .append(")\n"));
        return sb.toString();
    }

    @RequestMapping(value = "/feign/github/issues", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String githubIssues(
            @RequestParam(name = "owner", required = false, defaultValue = Default.OWNER) String owner,
            @RequestParam(name = "repo", required = false, defaultValue = Default.REPO) String repo
    ) {
        List<Issue> issues = feignService.gitHubIssues(owner, repo);
        StringBuilder sb = new StringBuilder("--------------\n--- Issues ---\n--------------\n\n");
        issues.forEach(it -> sb.append("-----\n- ")
                .append(it.getTitle())
                .append("-\n-----\n")
                .append("\n")
                .append(it.getBody())
                .append(")\n\n"));
        return sb.toString();
    }

}
