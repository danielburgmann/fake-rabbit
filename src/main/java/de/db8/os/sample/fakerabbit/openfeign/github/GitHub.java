package de.db8.os.sample.fakerabbit.openfeign.github;

import de.db8.os.sample.fakerabbit.openfeign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "github", url = "https://api.github.com", configuration = FeignConfiguration.class)
public interface GitHub {
    @GetMapping(value = "/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@RequestParam("owner") String owner, @RequestParam("repo") String repo);

    @GetMapping(value = "/repos/{owner}/{repo}/issues")
    List<Issue> issues(@RequestParam("owner") String owner, @RequestParam("repo") String repo);

}