package fr.alkanife.myla.commands;

import fr.alkanife.myla.Lang;
import fr.alkanife.myla.Myla;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class CommandHandler {

    private static Map<String, MylaCommand> commands = new HashMap<>();
    public static int executed = 0;

    public static Collection<MylaCommand> getCommands() {
        return commands.values();
    }

    public static void registerCommands(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                method.setAccessible(true);

                MylaCommand mylaCommand = new MylaCommand(command.name(), object, method);

                commands.put(command.name(), mylaCommand);
            }
        }
    }

    public static void redirect(SlashCommandEvent slashCommandEvent) {
        try {
            MylaCommand command = getCommand(slashCommandEvent.getName().toLowerCase(Locale.ROOT));

            if (command == null)
                return;

            Parameter[] parameters = command.getMethod().getParameters();

            if (parameters.length != 1)
                return;

            if (parameters[0].getType().equals(SlashCommandEvent.class))
                command.getMethod().invoke(command.getObject(), slashCommandEvent);

            executed++;
        } catch (Exception exception) {
            Myla.getLogger().error("-----------------------");
            Myla.getLogger().error("Failed to execute a command");
            Myla.getLogger().error("Command name: " + slashCommandEvent.getName());
            Myla.getLogger().error("sub group " + slashCommandEvent.getSubcommandGroup());
            Myla.getLogger().error("sub name " + slashCommandEvent.getSubcommandName());

            for (OptionMapping optionMapping : slashCommandEvent.getOptions())
                Myla.getLogger().error("Option " + optionMapping.getName() + " -> " + optionMapping.getAsString());

            Myla.getLogger().error("Executed by " + slashCommandEvent.getUser().getName() + " (" + slashCommandEvent.getUser().getId() + ")");
            Myla.getLogger().error("In " + slashCommandEvent.getTextChannel().getName() + " (" + slashCommandEvent.getTextChannel().getId() + ")");

            Myla.getLogger().error(exception.getCause().getMessage(), exception);

            Myla.getLogger().error("-----------------------");

            slashCommandEvent.reply(Lang.t("command-error")).setEphemeral(true).queue();
        }
    }

    public static MylaCommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}