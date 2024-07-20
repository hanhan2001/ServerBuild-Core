package me.xiaoying.serverbuild.module;

import me.xiaoying.serverbuild.core.SBPlugin;
import me.xiaoying.serverbuild.file.File;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final List<Listener> listeners = new ArrayList<>();
    private final List<File> files = new ArrayList<>();

    /**
     * Get name of Module
     *
     * @return String
     */
    public abstract String getName();

    /**
     * Get alias name of Model
     *
     * @return String
     */
    public abstract String getAliasName();

    /**
     * Get Listeners
     *
     * @return ArrayList
     */
    public List<Listener> getListeners() {
        return this.listeners;
    }

    /**
     * Register listener
     *
     * @param listener Listener
     */
    public void registerListener(Listener listener) {
        if (this.listeners.contains(listener))
            return;

        this.listeners.add(listener);
    }

    /**
     * Register file
     *
     * @param file File
     */
    public void registerFile(File file) {
        if (this.files.contains(file))
            return;

        this.files.add(file);
        file.load();
    }

    public abstract void init();

    public void enable() {
        // register listeners
        this.listeners.forEach(listener ->  Bukkit.getPluginManager().registerEvents(listener, SBPlugin.getInstance()));
    }

    public void disable() {
        // unregister listeners
        this.listeners.forEach(HandlerList::unregisterAll);
        // unregister files
        this.files.forEach(File::disable);
    }

    public abstract void onEnable();
    public abstract void onDisable();
}