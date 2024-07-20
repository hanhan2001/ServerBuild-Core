package me.xiaoying.serverbuild.script.scripts;

import me.xiaoying.serverbuild.script.Script;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Script console
 */
public class Console implements Script {
    @Override
    public String getName() {
        return "console";
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

        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), stringBuilder.toString());
    }

    @Override
    public boolean processFirst() {
        return false;
    }
}