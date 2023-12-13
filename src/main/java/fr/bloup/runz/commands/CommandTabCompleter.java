package fr.bloup.runz.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("runz")) {
            if (args.length == 1) {
                List<String> list = new ArrayList<>();
                list.add("info");
                return list;
            }
        }
        return null;
    }
}
