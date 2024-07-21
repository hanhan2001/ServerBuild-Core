package me.xiaoying.serverbuild.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * Command SubCommand
 */
public abstract class SubCommand {
    private final Map<String, List<RegisteredCommand>> registeredCommands = new HashMap<>();

    /**
     * Register new command
     *
     * @param subCommand SubCommand
     */
    public void registerCommand(SubCommand subCommand) {
        me.xiaoying.serverbuild.command.Command command = subCommand.getClass().getAnnotation(me.xiaoying.serverbuild.command.Command.class);

        if (command == null) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eFined some command(" + subCommand.getClass().getName() + ") don't use Command annotation, please check your code!"));
            return;
        }

        for (String s : command.values()) {
            List<RegisteredCommand> list = new ArrayList<>();
            for (int i : command.length())
                list.add(new RegisteredCommand(i, subCommand));

            this.registeredCommands.put(s, list);
        }
    }

    /**
     * Perform command
     *
     * @param sender Sender
     * @param args Command's functions
     */
    public abstract void performCommand(CommandSender sender, String[] args);

    /**
     * Perform tab<br>
     * If you don't want sender get command help message, you can override this method and return empty list
     *
     * @param commandSender Sender
     * @param command Command
     * @param s Command head
     * @param strings Command's functions
     * @return ArrayList
     */
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>(registeredCommands.keySet());
        if (strings.length == 1) {
            List<String> conditionList = new ArrayList<>();
            for (String s1 : list) {
                if (!s1.startsWith(strings[0]))
                    continue;
                conditionList.add(s1);
            }

            if (conditionList.size() == 0)
                return list;
            return conditionList;
        }

        List<RegisteredCommand> registeredCommand = this.registeredCommands.get(strings[0]);
        if (registeredCommand == null)
            return new ArrayList<>();

        strings = new ArrayList<>(Arrays.asList(strings).subList(1, strings.length)).toArray(new String[0]);
        for (RegisteredCommand registeredCommand1 : registeredCommand) {
            List<String> l;
            if ((l = registeredCommand1.getSubCommand().onTabComplete(commandSender, command, s, strings)) == null)
                return null;

            return l;
        }
        return new ArrayList<>();
    }
}