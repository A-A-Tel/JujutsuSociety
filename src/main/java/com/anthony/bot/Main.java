package com.anthony.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main extends ListenerAdapter {

    public static JDA JDA = JDABuilder.createDefault(Token.BOT)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .enableIntents(GatewayIntent.GUILD_MEMBERS)
            .addEventListeners(new Main())
            .build();

    public static void main(String[] args) {}
}