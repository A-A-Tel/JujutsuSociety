package com.anthony.bot.command.commands;

import com.anthony.bot.command.ICommand;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Profile implements ICommand {

    @Override
    public String getName() {
        return "profile";
    }

    @Override
    public String getDescription() {
        return "Apply with your profile";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.INTEGER, "age", "Tell us your age", true),
                new OptionData(OptionType.INTEGER, "level", "Tell us your level", true)
        );
    }

    @Override
    public DefaultMemberPermissions getPermission() {
        return DefaultMemberPermissions.ENABLED;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        int age = event.getOption("age").getAsInt();
        int level = event.getOption("level").getAsInt();

        if (event.getChannelIdLong() != 1323324965122211870L) {

            event.reply("Fuck off brother, wrong channel you nitwit.").queue();
        } else {

            MessageChannel channel = event.getGuild().getTextChannelById(1323341181563834409L);

            channel.sendMessage("<@&1306721051337822299> This person just applied:" +
                    "\r\n    Name: " + event.getUser().getAsMention() +
                    "\r\n    Age: " + age +
                    "\r\n    Level: " + level
            ).queue();
            event.reply("Yo wsp, go to <#1323312261246488617> for your roles alr? We will approve you soon.").queue();
        }
    }
}
