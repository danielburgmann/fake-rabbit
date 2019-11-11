package de.db8.os.sample.fakerabbit.log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


@Controller
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    private enum LogLevel {
        trace,
        debug,
        info,
        warn,
        error
    }

    @RequestMapping(value = {"/log", "/log.html"}, method = RequestMethod.GET)
    public String log(@RequestParam(name = "level", required = false, defaultValue = "info") String level,
                          @RequestParam(name = "message", required = false, defaultValue = "Hello Log!") String message,
                          Model model) {

        LogLevel logLevel;
        try {
            logLevel = LogLevel.valueOf(level);
        }
        catch (IllegalArgumentException e) {
            logLevel = LogLevel.error;
            message = "Unknown log level for message: " + message;
        }

        switch (logLevel) {
            case trace: log.trace(message); break;
            case debug: log.debug(message); break;
            case info: log.info(message); break;
            case warn: log.warn(message); break;
            case error: log.error(message); break;
        }

        LogEntry logEntry = new LogEntry(level, message);

        String[] levelStrings = Arrays.stream(LogLevel.values()).map(Enum::toString).toArray(String[]::new);

        model.addAttribute("logEntry", logEntry);
        model.addAttribute("logLevels", levelStrings);

        return "log.html";
    }
}
