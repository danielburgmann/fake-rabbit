package de.db8.os.sample.fakerabbit.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * A simple controller replying with an HTML page containing a hello message for a given name.
 *
 * Format of answer can be changed by parameter format.
 */

@Controller
public class HelloController {

    @RequestMapping(value = {"/hello.html","/hello"}, method = RequestMethod.GET)
    public String helloHtml(@RequestParam(name = "name", required = false, defaultValue = Hello.WORLD) String name,
                        @RequestParam(name = "color", required = false, defaultValue = "white") String color,
                        Model model) {

        model.addAttribute("hello", new Hello(name));
        model.addAttribute("color", color);

        return "hello.html";
    }

    @RequestMapping(value = "/hello.txt", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String helloTxt(@RequestParam(name = "name", required = false, defaultValue = Hello.WORLD) String name) {
        return new Hello(name).getMessage();
    }

    @RequestMapping(value = "/hello.json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Hello helloJson(@RequestParam(name = "name", required = false, defaultValue = Hello.WORLD) String name) {
        return new Hello(name);
    }

}
