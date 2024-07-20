package me.xiaoying.serverbuild.script.scripts;

import me.xiaoying.serverbuild.script.Script;
import org.bukkit.entity.Player;

/**
 * Script send
 */
public class SendScript implements Script {
    @Override
    public String getName() {
        return "send";
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

        player.sendMessage(stringBuilder.toString());
    }

    @Override
    public boolean processFirst() {
        return false;
    }
}