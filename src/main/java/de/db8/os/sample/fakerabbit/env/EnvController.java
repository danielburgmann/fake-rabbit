package de.db8.os.sample.fakerabbit.env;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A controller giving information about the environment the application is running in.
 */

@Controller
public class EnvController {

    @RequestMapping(value = {"/env", "env.html"}, method = RequestMethod.GET)
    public String envHtml(Model model) {
        model.addAttribute("env", getEnvVars());
        model.addAttribute("networkInfo", getNetworkInfo());
        model.addAttribute("localDateTime", getLocalDateTimeInfo());
        return "env.html";
    }

    @RequestMapping(value = "/env.txt", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String envTxt() {
        StringBuilder sb = new StringBuilder("# Current application environment\n");
        sb.append("\n");

        sb.append("# Local date time: ");
        sb.append(getLocalDateTimeInfo());
        sb.append("\n");
        sb.append("\n");

        sb.append("# Environment variables\n");
        getEnvVars().forEach((key,value) -> {
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("\n");
        });
        sb.append("\n");

        sb.append("# Network interfaces\n");
        getNetworkInfo().forEach((nic, ips) -> {
            sb.append(nic);
            sb.append("\n");

            ips.forEach(ip -> {
                sb.append("    ");
                sb.append(ip);
                sb.append("\n");
            });
        });
        sb.append("\n");

        sb.append("###\n");

        return sb.toString();
    }

    private static Map<String,String> getEnvVars() {
        //pack into TreeMap for automatic sorting
        return new TreeMap<>(System.getenv());
    }

    // inspired by https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java#20418809
    private static Map<String, List<String>> getNetworkInfo() {
        Map<String, List<String>> map = new TreeMap<>();

        try {
            for (Enumeration nics = NetworkInterface.getNetworkInterfaces(); nics.hasMoreElements(); ) {
                NetworkInterface nic = (NetworkInterface) nics.nextElement();

                List<String> ips = new LinkedList<>();
                for (Enumeration addresses = nic.getInetAddresses(); addresses.hasMoreElements(); ) {
                    InetAddress address = (InetAddress) addresses.nextElement();
                    ips.add(address.toString());
                }

                map.put(nic.getDisplayName(), ips);
            }
        } catch (SocketException e) {
            map.put("exception", Collections.singletonList(e.getMessage()));
        }

        return map;
    }

    private static String getLocalDateTimeInfo() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
    }
}
