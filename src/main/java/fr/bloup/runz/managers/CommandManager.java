package fr.bloup.runz.managers;

import fr.bloup.runz.Runz;
import fr.bloup.runz.commands.CommandTabCompleter;
import fr.bloup.runz.commands.RunzCommand;


public class CommandManager {
    Runz main = Runz.instance();

    public void setCommand(){
        main.getCommand("runz").setExecutor(new RunzCommand());
        main.getCommand("runz").setTabCompleter(new CommandTabCompleter());
    }
}
