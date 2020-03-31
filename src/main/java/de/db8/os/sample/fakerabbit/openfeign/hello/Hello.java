package de.db8.os.sample.fakerabbit.openfeign.hello;

import de.db8.os.sample.fakerabbit.openfeign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hello", url = "http://localhost:8080", configuration = FeignConfiguration.class)
public interface Hello {
    @GetMapping(value = "/hello.json")
    HelloResponse hello(@RequestParam("name") String name);
}
