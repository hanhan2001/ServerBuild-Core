package me.xiaoying.serverbuild.scheduler;

public abstract class Scheduler {
    private final Runnable runnable;
    private final Type type;
    private long delay = 0L;

    /**
     * Constructor
     *
     * @param runnable Runnable
     */
    public Scheduler(Runnable runnable) {
        this.runnable = runnable;
        this.type = Type.SYNC_DELAY;
    }

    /**
     * Constructor
     *
     * @param runnable Runnable
     * @param type Scheduler's type
     */
    public Scheduler(Runnable runnable, Scheduler.Type type) {
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
    public Scheduler(Runnable runnable, Scheduler.Type type, long delay) {
        this.runnable = runnable;
        this.type = type;
        this.delay = delay;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public Type getType() {
        return this.type;
    }

    public long getDelay() {
        return this.delay;
    }

    public enum Type {
        SYNC_REPEAT,
        SYNC_DELAY,
        ASYNC_REPEAT,
        ASYNC_DELAY;
    }
}