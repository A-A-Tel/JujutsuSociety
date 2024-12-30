package com.anthony.bot.command;

import com.anthony.bot.Main;
import com.anthony.bot.command.commands.Kill;
import com.anthony.bot.command.commands.Profile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public class CommandManager {

    private List<ICommand> commands = List.of(new Kill(), new Profile());

    public void LoadCommandsGlobal() {
        for (Guild guild : Main.JDA.getGuilds()) {
            for (ICommand command : commands) {
                guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).setDefaultPermissions(command.getPermission()).queue();
            }
        }
    }

    public void loadCommandsSingle(Guild guild) {
        for (ICommand command : commands) {
            guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).setDefaultPermissions(command.getPermission()).queue();
        }
    }

    public void execute(SlashCommandInteractionEvent event) {
        for (ICommand command : commands) {
            if (event.getName().equals(command.getName())) {
                command.execute(event);
            }
        }
    }
}
