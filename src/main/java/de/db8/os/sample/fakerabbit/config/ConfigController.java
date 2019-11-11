package de.db8.os.sample.fakerabbit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfigController {

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping(value = {"/config", "/config.html"}, method = RequestMethod.GET)
    public String displayConfig(Model model) {
        String message = configService.getConfigMessage();
        String configLocation = configService.getConfigLocation();

        model.addAttribute("message", message);
        model.addAttribute("configLocation", configLocation);

        return "config.html";
    }

}
