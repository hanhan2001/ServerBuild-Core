package me.xiaoying.serverbuild.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleModuleManager implements ModuleManager {
    private final List<Module> knownModules = new ArrayList<>();

    @Override
    public void registerModule(Module module) {
        if (this.knownModules.contains(module))
            return;

        this.knownModules.add(module);
    }

    @Override
    public void unregisterModule(Module module) {
        if (!this.knownModules.contains(module))
            return;
        module.onDisable();
        module.disable();
        this.knownModules.remove(module);
    }

    @Override
    public void unregisterModules() {
        this.disableModules();
        this.knownModules.clear();
    }

    @Override
    public List<Module> getKnownModules() {
        return Collections.emptyList();
    }

    @Override
    public void enableModules() {
        this.knownModules.forEach(module -> {
            module.onEnable();
            module.enable();
        });
    }

    @Override
    public void disableModules() {
        this.knownModules.forEach(module -> {
            module.onDisable();
            module.disable();
        });
    }
}