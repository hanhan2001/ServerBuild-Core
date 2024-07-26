package me.xiaoying.serverbuild.script.scripts;

import me.xiaoying.serverbuild.script.Script;
import org.bukkit.entity.Player;

public class LogScript implements Script {
    @Override
    public String getName() {
        return "log";
    }

    @Override
    public void performCommand(Player player, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);

            if (i == args.length - 1)
                break;

            stringBuilder.append(" ");
        }
    }

    @Override
    public boolean processFirst() {
        return false;
    }
}