package fr.bloup.runz.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RunzCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("runz")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
                sender.sendMessage("§7[§bRunz§7] §fVersion 1.0");
                sender.sendMessage("§7[§bRunz§7] §fCreated by §6bloup§f/§6bloupyi");
            }
            return true;
        }
        return false;
    }

}
