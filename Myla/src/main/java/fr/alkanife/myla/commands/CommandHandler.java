package fr.alkanife.myla.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandHandler {

    private Map<String, BotCommand> commands = new HashMap<>();

    public Collection<BotCommand> getCommands() {
        return commands.values();
    }

    public BotCommand getCommand(String commandName) {
        return commands.get(commandName);
    }

    public void registerCommands(Object... objects) {
        for (Object o : objects)
            registerCommand(o);
    }

    public void registerCommand(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                method.setAccessible(true);

                BotCommand botCommand = new BotCommand(command.name(), object, method);

                commands.put(command.name(), botCommand);
            }
        }
    }

    public void handleSlash(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        try {
            BotCommand botCommand = getCommand(slashCommandInteractionEvent.getName().toLowerCase(Locale.ROOT));

            if (botCommand == null)
                return;

            Parameter[] parameters = botCommand.getMethod().getParameters();

            if (parameters.length != 1)
                return;

            if (parameters[0].getType().equals(SlashCommandInteractionEvent.class)) {
                botCommand.getMethod().invoke(botCommand.getObject(), slashCommandInteractionEvent);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
