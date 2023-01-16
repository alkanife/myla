package fr.alkanife.myla;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent readyEvent) {
        if (Myla.updateCommands) {
            Myla.getLogger().info("Updating commands");
            updateCommands();
        }

        Myla.getLogger().info("Connected!");
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
        SlashCommandData myla = Commands.slash("myla", "Hi!").addOption(OptionType.STRING, "args", "optional", false);
        SlashCommandData blush = Commands.slash("blush", "(⩾﹏⩽) Reply with a random 'blush' GIF.");
        SlashCommandData cookie = Commands.slash("cookie", "Nom nom nom. Reply with a random 'cookie' GIF.");
        SlashCommandData cry = Commands.slash("cry", "ಥ﹏ಥ Reply with a random 'cry' GIF.");
        SlashCommandData headpat = Commands.slash("headpat", "Wholesome. Reply with a random 'headpat' GIF.");
        SlashCommandData hehe = Commands.slash("hehe", "NYEH HEH HEH. Reply with a random 'hehe/smug' GIF.");
        SlashCommandData hi = Commands.slash("hi", "👋🏻 Reply with a random 'hi/wave' GIF.");
        SlashCommandData hug = Commands.slash("hug", "(◠﹏◠) Reply with a random 'hug' GIF.");
        SlashCommandData idk = Commands.slash("idk", "¯\\_(ツ)_/¯ Reply with a random 'idk' GIF.");
        SlashCommandData kiss = Commands.slash("kiss", "( ˘ ³˘)♥ Reply with a random 'kiss' GIF.");
        SlashCommandData laugh = Commands.slash("laugh", "Ah ah. Reply with a random 'laugh' GIF.");
        SlashCommandData meme = Commands.slash("meme", "Reply with a random meme.");
        SlashCommandData notlikethis = Commands.slash("notlikethis", "(ಥ⌣ಥ) Reply with a random 'notlikethis' GIF.");
        SlashCommandData party = Commands.slash("party", "┌(ㆆ㉨ㆆ)ʃ Reply with a random 'party' GIF.");
        SlashCommandData pout = Commands.slash("pout", "Mh. Reply with a random 'pout' GIF.");
        SlashCommandData punch = Commands.slash("punch", "(ง '̀-'́)ง Reply with a random 'punch' GIF.");
        SlashCommandData slap = Commands.slash("slap", "Nicen't. Reply with a random 'slap' GIF.");
        SlashCommandData smile = Commands.slash("smile", "(ᵔᴥᵔ) Reply with a random 'smile' GIF.");
        SlashCommandData wink = Commands.slash("wink", "Wink wink. Reply with a random 'wink' GIF.");
        SlashCommandData pray = Commands.slash("pray", "🙏🏻 Reply with a random 'pray' GIF.");
        SlashCommandData hide = Commands.slash("hide", "┻┳|･ ω･) Reply with a random 'hide' GIF.");

        Myla.getJDA().updateCommands().addCommands(myla, blush, cookie, cry, headpat, hehe, hi, hug, idk, kiss, laugh, meme,
                notlikethis, party, pout, punch, slap, smile, wink, pray, hide).queue();
    }

}
