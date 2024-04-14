package net.fragger.creatoroverlays.util.config;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class COConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    private final List<Pair> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair) {
        configsList.add(keyValuePair);
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() +  "\n";
    }
    public void removeKeyValuePairs() {
        configsList.clear();
        configContents = "";
    }

    public void updateKeyValuePair(Pair<String, ?> keyValuePair) {
        configContents = "";
        for (int i = 0; i < configsList.size(); i++) {
            if (configsList.get(i).getFirst().equals(keyValuePair.getFirst())) {
                configsList.set(i, keyValuePair);
            }
            configContents += configsList.get(i).getFirst() + "=" + configsList.get(i).getSecond() + "\n";
        }
    }
    public Object getValue(String key) {
        Object o = null;
        for (int i = 0; i < configsList.size(); i++) {
            if (configsList.get(i).getFirst().equals(key)) {
                o = configsList.get(i).getSecond();
            }
        }
        return o;
    }
    @Override
    public String get(String namespace) {
        return configContents;
    }
}
