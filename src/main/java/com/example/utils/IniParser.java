package com.example.utils;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class IniParser {
    private static IniParser instance;
    private final HashMap<String, HashMap<String, String>> sections;

    private IniParser() {
        this.sections = new HashMap<>();
    }

    public static synchronized IniParser getInstance() {
        if (instance == null) {
            instance = new IniParser();
        }
        return instance;
    }

    public void parse(File file) throws ConfigurationException {
        Configurations configs = new Configurations();
        INIConfiguration iniConfig = configs.ini(file);

        for (String sectionName : iniConfig.getSections()) {
            HashMap<String, String> section = new HashMap<>();

            Iterator<String> keyIterator = iniConfig.getSection(sectionName).getKeys();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                String value = iniConfig.getSection(sectionName).getProperty(key).toString();
                section.put(key, value);
            }
            sections.put(sectionName, section);
        }
    }

    public HashMap<String, String> getSection(String sectionName) {
        return sections.get(sectionName);
    }

    public String getValue(String input) {
        String[] parts = input.split("/");
        String sectionName = parts[0];
        String keyName = parts[1];
        HashMap<String, String> section = getSection(sectionName);
        return section.get(keyName);
    }
}