package me.xiaoying.serverbuild.scheduler;

import me.xiaoying.serverbuild.core.SBPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Scheduler<br>
 * Default type of scheduler is SYNC_DELAY<br><br>
 * Type
 * <ul>
 *     <li>SYNC_DELAY</li>
 *     <li>SYNC_REPEAT</li>
 *     <li>ASYNC_DELAY</li>
 *     <li>SYNC_REPEAT</li>
 * </ul>
 */
public abstract class Scheduler {
    private final Runnable runnable;
    private final Type type;
    private long delay = 0L;
    private int id;

    /**
     * Constructor
     *
     * @param name name of scheduler
     * @param runnable Runnable
     */
    public Scheduler(String name, Runnable runnable) {
        this.runnable = runnable;
        this.type = Type.SYNC_DELAY;
    }

    /**
     * Constructor
     *
     * @param runnable Runnable
     * @param type Scheduler's type
     */
    public Scheduler(String name, Runnable runnable, Scheduler.Type type) {
        this.runnable = runnable;
        this.type = type;
    }

    /**
     * Constructor
     *
     * @param runnable Runnable
     * @param type Scheduler's type
     * @param delay Delay
     */
    public Scheduler(String name, Runnable runnable, Scheduler.Type type, long delay) {
        this.runnable = runnable;
        this.type = type;
        this.delay = delay;
    }

    /**
     * Get id of scheduler
     *
     * @return Long
     */
    public long getId() {
        return this.id;
    }

    /**
     * Set id of scheduler
     *
     * @param id Integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get handle of scheduler
     *
     * @return Runnable
     */
    public Runnable getRunnable() {
        return this.runnable;
    }

    /**
     * Get type of scheduler
     *
     * @return Scheduler.Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Get delay seconds of scheduler
     *
     * @return Long
     */
    public long getDelay() {
        return this.delay;
    }

    /**
     * Run scheduler
     */
    public void run() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();;
        switch (this.type) {
            case SYNC_DELAY:
                this.id = scheduler.scheduleSyncDelayedTask(SBPlugin.getInstance(), this.runnable, this.delay);
                break;
            case SYNC_REPEAT:
                this.id = scheduler.scheduleSyncRepeatingTask(SBPlugin.getInstance(), this.runnable, 0, this.delay);
                break;
            case ASYNC_DELAY:
                this.id = scheduler.runTaskLaterAsynchronously(SBPlugin.getInstance(), this.runnable, this.delay).getTaskId();
                break;
            case ASYNC_REPEAT:
                this.id = scheduler.runTaskLater(SBPlugin.getInstance(), this.runnable, this.delay).getTaskId();
                break;
        }
    }

    /**
     * Stop scheduler
     */
    public void stop() {
        try { Bukkit.getScheduler().cancelTask(this.id); } catch (Exception e) {}
    }

    public enum Type {
        SYNC_REPEAT,
        SYNC_DELAY,
        ASYNC_REPEAT,
        ASYNC_DELAY;
    }
}