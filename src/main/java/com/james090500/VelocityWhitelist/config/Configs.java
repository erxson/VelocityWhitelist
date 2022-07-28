package com.james090500.VelocityWhitelist.config;

import com.james090500.VelocityWhitelist.VelocityWhitelist;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Configs {

    @Getter private static Config config;
    public static ArrayList<String> whitelist = new ArrayList<String>();;
    private static Path configFile;
    private static Path whitelistFile;
    public static void loadConfigs(VelocityWhitelist velocityWhitelist) {
        configFile = Path.of(velocityWhitelist.getDataDirectory() + "/config.toml");
        whitelistFile = Path.of(velocityWhitelist.getDataDirectory() + "/whitelist.yml");

        //Create data directory
        if(!velocityWhitelist.getDataDirectory().toFile().exists()) {
            velocityWhitelist.getDataDirectory().toFile().mkdir();
        }

        //Load the config.toml to memory
        if(!configFile.toFile().exists()) {
            try (InputStream in = VelocityWhitelist.class.getResourceAsStream("/config.toml")) {
                Files.copy(in, configFile);
            } catch (Exception e) {
                velocityWhitelist.getLogger().error("Error loading config.toml");
                e.printStackTrace();
            }
        }
        config = new Toml().read(configFile.toFile()).to(Config.class);

        //Load whitelist players to memory (if any)
        if(whitelistFile.toFile().exists()) {
            try (InputStreamReader ignored = new InputStreamReader(new FileInputStream(whitelistFile.toFile()), StandardCharsets.UTF_8)) {
                Scanner scan = new Scanner(whitelistFile);
                whitelist.clear();
                while(scan.hasNext()) {
                    whitelist.add(scan.next());
                }
            } catch (Exception e) {
                velocityWhitelist.getLogger().error("Error loading whitelist.yml");
                e.printStackTrace();
            }
        }
    }

    /**
     * Save the config
     */
    public static void saveConfig(VelocityWhitelist velocityWhitelist) {
        try {
            new TomlWriter().write(config, configFile.toFile());
        } catch (Exception e) {
            velocityWhitelist.getLogger().error("Error writing config.toml");
            e.printStackTrace();
        }
    }

    /**
     * Save the whitelist file
     */
    public static void saveWhitelist(VelocityWhitelist velocityWhitelist) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(String.valueOf(whitelistFile))));
            for (String s : whitelist) {
                out.println(s);
            }
            out.close();
        } catch (Exception e) {
            velocityWhitelist.getLogger().error("Error writing whitelist.yml");
            e.printStackTrace();
        }
    }

    /**
     * The main config
     */
    public static class Config {

        @Getter @Setter
        private boolean enabled;
        @Getter
        private final String message;

        public Config(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Panel{" +
                "enabled='" + enabled + '\'' +
                ", message='" + message + '\'' +
            '}';
        }
    }
}