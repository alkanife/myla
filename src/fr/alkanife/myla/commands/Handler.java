package fr.alkanife.myla.commands;

import fr.alkanife.botcommons.CommandHandler;
import fr.alkanife.botcommons.Lang;
import fr.alkanife.myla.Myla;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Handler extends CommandHandler {

    public static int executed = 0;

    @Override
    public void success(SlashCommandEvent slashCommandEvent) {
        executed++;
    }

    @Override
    public void fail(SlashCommandEvent slashCommandEvent, Exception e) {
        Myla.getLogger().error("-----------------------");
        Myla.getLogger().error("Failed to execute a command");
        Myla.getLogger().error("Command name: " + slashCommandEvent.getName());
        Myla.getLogger().error("sub group " + slashCommandEvent.getSubcommandGroup());
        Myla.getLogger().error("sub name " + slashCommandEvent.getSubcommandName());

        for (OptionMapping optionMapping : slashCommandEvent.getOptions())
            Myla.getLogger().error("Option " + optionMapping.getName() + " -> " + optionMapping.getAsString());

        Myla.getLogger().error("Executed by " + slashCommandEvent.getUser().getName() + " (" + slashCommandEvent.getUser().getId() + ")");
        Myla.getLogger().error("In " + slashCommandEvent.getTextChannel().getName() + " (" + slashCommandEvent.getTextChannel().getId() + ")");

        Myla.getLogger().error(e.getCause().getMessage(), e);

        Myla.getLogger().error("-----------------------");

        slashCommandEvent.reply(Lang.t("command-error")).setEphemeral(true).queue();
    }
}