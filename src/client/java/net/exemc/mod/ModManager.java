package net.exemc.mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModManager {

    public static ModManager INSTANCE = null;

    private Map<String, Mod> modMap;

    public ModManager() {
        modMap = new HashMap<>();
        INSTANCE = this;
    }

    public void add(Mod mod) {
        modMap.put(mod.getName(), mod);
    }

    public Mod get(String name) {
        return modMap.get(name);
    }

    public List<Mod> mods() {
        return new ArrayList<>(modMap.values());
    }
}
