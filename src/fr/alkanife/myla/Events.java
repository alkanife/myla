package fr.alkanife.myla;

import fr.alkanife.myla.commands.Handler;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent readyEvent) {
        Myla.getLogger().info("Connected to Discord");

        //commands(readyEvent);

        Stats.update();

        Myla.getLogger().info("Done!");
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Myla.getHandler().handle(event);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Stats.update();
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        Stats.update();
    }

    private void commands(ReadyEvent readyEvent) {

        /*Guild guild = readyEvent.getJDA().getGuildById("");

        if (guild == null) {
            Myla.getLogger().error("Invalid guild");
            return;
        }*/

        CommandData myla = new CommandData("myla", "Need help? A bug? A feedback?")
                .addOption(OptionType.STRING, "args", "optional", false);

        CommandData blush = new CommandData("blush", "(⩾﹏⩽) Reply with a random 'blush' GIF.");
        CommandData cookie = new CommandData("cookie", "Nom nom nom. Reply with a random 'cookie' GIF.");
        CommandData cry = new CommandData("cry", "ಥ﹏ಥ Reply with a random 'cry' GIF.");
        CommandData headpat = new CommandData("headpat", "Wholesome. Reply with a random 'headpat' GIF.");
        CommandData hehe = new CommandData("hehe", "NYEH HEH HEH. Reply with a random 'hehe/smug' GIF.");
        CommandData hi = new CommandData("hi", "👋🏻 Reply with a random 'hi/wave' GIF.");
        CommandData hug = new CommandData("hug", "(◠﹏◠) Reply with a random 'hug' GIF.");
        CommandData idk = new CommandData("idk", "¯\\_(ツ)_/¯ Reply with a random 'idk' GIF.");
        CommandData kiss = new CommandData("kiss", "( ˘ ³˘)♥ Reply with a random 'kiss' GIF.");
        CommandData laugh = new CommandData("laugh", "Ah ah. Reply with a random 'laugh' GIF.");
        CommandData meme = new CommandData("meme", "Reply with a random meme.");
        CommandData notlikethis = new CommandData("notlikethis", "(ಥ⌣ಥ) Reply with a random 'notlikethis' GIF.");
        CommandData party = new CommandData("party", "┌(ㆆ㉨ㆆ)ʃ Reply with a random 'party' GIF.");
        CommandData pout = new CommandData("pout", "Mh. Reply with a random 'pout' GIF.");
        CommandData punch = new CommandData("punch", "(ง '̀-'́)ง Reply with a random 'punch' GIF.");
        CommandData slap = new CommandData("slap", "Nicen't. Reply with a random 'slap' GIF.");
        CommandData smile = new CommandData("smile", "(ᵔᴥᵔ) Reply with a random 'smile' GIF.");
        CommandData wink = new CommandData("wink", "Wink wink. Reply with a random 'wink' GIF.");
        CommandData pray = new CommandData("pray", "🙏🏻 Reply with a random 'pray' GIF.");

        Myla.getJda().updateCommands().addCommands(myla, blush, cookie, cry, headpat, hehe, hi, hug, idk, kiss, laugh, meme,
                notlikethis, party, pout, punch, slap, smile, wink, pray).queue();
    }

}
