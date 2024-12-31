package com.anthony.bot.command.commands;

import com.anthony.bot.command.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Kill implements ICommand {
    @Override
    public String getName() {
        return "kill-bot";
    }

    @Override
    public String getDescription() {
        return "Kills the bot to re-instate everything.";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public DefaultMemberPermissions getPermission() {
        return DefaultMemberPermissions.DISABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Goodbye, I'll be back in a minute.").queue();
        delay();
        System.exit(0);
    }

    private void delay() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }
    }
}
