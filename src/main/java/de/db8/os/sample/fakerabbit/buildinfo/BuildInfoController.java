package de.db8.os.sample.fakerabbit.buildinfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BuildInfoController {


    private final BuildInfoService buildInfoService;

    @Autowired
    public BuildInfoController(BuildInfoService buildInfoService) {
        this.buildInfoService = buildInfoService;
    }

    @RequestMapping(value = "/buildInfo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BuildInfo displayBuildInfo() {
        return buildInfoService.getBuildInfo();
    }
}
