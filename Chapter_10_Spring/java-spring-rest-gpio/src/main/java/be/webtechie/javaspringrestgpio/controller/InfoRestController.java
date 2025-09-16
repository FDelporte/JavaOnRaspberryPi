package be.webtechie.javaspringrestgpio.controller;

import be.webtechie.javaspringrestgpio.manager.Pi4JManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides a REST interface to expose board info.
 */
@RestController
@RequestMapping("info")
public class InfoRestController {
    private final Pi4JManager pi4JManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public InfoRestController(Pi4JManager pi4JManager) {
        this.pi4JManager = pi4JManager;
    }

    /**
     * Get the platform info.
     */
    @GetMapping(path = "platform", produces = "application/json")
    public Map<String, String> getPlatform() {
        Map<String, String> map = new TreeMap<>();

        var platform = pi4JManager.getPlatform();

        try {
            map.put("id", platform.getId());
        } catch (NullPointerException ex) {
            map.put("id", "UNKNOWN");
        }

        map.put("name", platform.name());
        map.put("description", platform.getDescription());

        return map;
    }

    /**
     * Get the hardware info.
     */
    @GetMapping(path = "board", produces = "application/json")
    public Map<String, String> getBoardInfo() {
        Map<String, String> map = new TreeMap<>();

        var boardInfo = pi4JManager.getBoardInfo();
        var boardModel = boardInfo.getBoardModel();

        map.put("CPU", boardModel.getCpu().getLabel());
        map.put("BoardModel", boardModel.getName());
        map.put("JavaVersion", boardInfo.getJavaInfo().getVendorVersion());
        map.put("OS", boardInfo.getOperatingSystem().getName());
        map.put("OSArchitecture", boardInfo.getOperatingSystem().getArchitecture());

        return map;
    }
}
