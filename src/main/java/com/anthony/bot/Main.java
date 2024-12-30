package com.anthony.bot;

import com.anthony.bot.command.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

public class Main extends ListenerAdapter {

    public static JDA JDA = JDABuilder.createDefault(Token.BOT)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .enableIntents(GatewayIntent.GUILD_MEMBERS)
            .addEventListeners(new Main())
            .build();

    public static void main(String[] args) {}

    //// Beginning Event Listener ////

    private CommandManager command = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        command.LoadCommandsGlobal();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        command.execute(event);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (!event.isFromGuild()) return;

        if (event.getChannel().getIdLong() == 1323324965122211870L) {
            if (event.getAuthor().isBot()) delay(300000);
            event.getMessage().delete().queue();
        }
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }


    }
}