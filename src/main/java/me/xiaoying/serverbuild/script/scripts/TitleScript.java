package me.xiaoying.serverbuild.script.scripts;

import me.xiaoying.serverbuild.script.Script;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Script title
 */
public class TitleScript implements Script {
    @Override
    public String getName() {
        return "title";
    }

    @Override
    public void performCommand(Player player, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        Player findPlayer;

        if (args.length == 0) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c错误的命令格式，应当为 &etitle [player] title:subtitle"));
            return;
        }

        findPlayer = Bukkit.getServer().getPlayer(args[0]);
        if (findPlayer == null)
            findPlayer = player;

        for (int i = 0; i < args.length; i++) {
            if (findPlayer != null && i == 0)
                continue;

            stringBuilder.append(args[i]);

            if (i == args.length - 1)
                break;

            stringBuilder.append(" ");
        }
        String title;
        String subtitle = null;
        if (stringBuilder.toString().contains(":")) {
            String[] split = stringBuilder.toString().split(":");
            title = split[0];
            subtitle = split[1];
        } else
            title = stringBuilder.toString();

        assert findPlayer != null;
        findPlayer.sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public boolean processFirst() {
        return false;
    }
}