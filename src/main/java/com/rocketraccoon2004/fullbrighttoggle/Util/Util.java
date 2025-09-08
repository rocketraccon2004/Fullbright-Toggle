package com.rocketraccoon2004.fullbrighttoggle.Util;

import com.rocketraccoon2004.fullbrighttoggle.fullbrighttoggle;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private static final String configFilePath = Minecraft.getMinecraft().gameDir + File.separator + "config" + File.separator + fullbrighttoggle.MOD_ID;
    private static final File configFile = new File(configFilePath);
    private static final String stateConfigFilePath = configFilePath + File.separator + "laststate.txt";
    private static final File stateConfigFile = new File(stateConfigFilePath);

    public static void saveLastStateToConfig(boolean fullBright) {
        if (!configFile.isDirectory() || !stateConfigFile.isFile()) {
            boolean ignored = configFile.mkdirs();
        }

        try {
            PrintWriter configWriter = new PrintWriter(stateConfigFilePath, String.valueOf(StandardCharsets.UTF_8));

            configWriter.print("fullbright=" + (fullBright ? 1 : 0));
            configWriter.close();
        }
        catch (IOException ignored) {}
    }

    public static boolean wasLastStateFullBright() {
        if (!configFile.isDirectory() || !stateConfigFile.isFile()) {
            return false;
        }

        try {
            String stateConfigContent = new String(Files.readAllBytes(Paths.get(stateConfigFilePath))).trim();
            return stateConfigContent.replace("fullbright=", "").equals("1");
        }
        catch (IOException ignored) { }

        return false;
    }
}