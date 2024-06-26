package ru.nivanov.conway;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class ConfigurationFactory {
    private static final Map<String, Configuration> configurationMap = new HashMap<>();

    static {
//        configurationMap.put("dot", new DotConfiguration());
        configurationMap.put("gliders_by_the_dozen", new OscillatorConfiguration());
    }

    private ConfigurationFactory(){}

    static Set<String> getConfigurationNames(){
        return configurationMap.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    static Configuration getConfiguration(String name){
        return configurationMap.get(name);
    }
}
