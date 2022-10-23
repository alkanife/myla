package fr.alkanife.myla;

import fr.alkanife.myla.Myla;
import fr.alkanife.myla.Stats;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent readyEvent) {
        /*Myla.getLogger().info("Updating commands");
        updateCommands();*/

        Myla.getLogger().info("Connected and ready!");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent slashCommandInteractionEvent) {
        Myla.getCommandHandler().handleSlash(slashCommandInteractionEvent);
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent guildJoinEvent) {
        Stats.update();
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent guildLeaveEvent) {
        Stats.update();
    }

    private void updateCommands() {

    }

}
