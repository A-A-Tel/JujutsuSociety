package com.anthony.bot;

import com.anthony.bot.command.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        Guild guild = event.getGuild();

        // Set the server stat channel correctly



        guild.getVoiceChannelById(1307076340675248170L).getManager().setName("Total Members: " + getRoleMembersAmount(guild.getRoleById(1306668411446628450L))).queue();
        guild.getVoiceChannelById(1307076344332419114L).getManager().setName("Jujutsu Tech: " + getRoleMembersAmount(guild.getRoleById(1323314326106275880L))).queue();
        guild.getVoiceChannelById(1307076348035989705L).getManager().setName("Mikaboshi Syndicate: " + getRoleMembersAmount(guild.getRoleById(1323314375511248996L))).queue();
        guild.getVoiceChannelById(1323783618803273819L).getManager().setName("Mechamaru: " + getRoleMembersAmount(guild.getRoleById(1306668411518193874L))).queue();
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }


    }

    private int getRoleMembersAmount(Role role) {

        if (role == null) return 0;

        List<Member> members =role.getGuild().getMembers();

        int memberAmount = 0;
        for (Member member : members) {
            if (member.getRoles().contains(role)) {
                memberAmount++;
            }
        }
        return memberAmount;
    }
}