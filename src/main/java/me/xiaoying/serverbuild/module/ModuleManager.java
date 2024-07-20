package me.xiaoying.serverbuild.module;

import java.util.List;

public interface ModuleManager {
    void registerModule(Module module);

    void unregisterModule(Module module);

    void unregisterModules();

    List<Module> getKnownModules();

    void enableModules();

    void disableModules();
}