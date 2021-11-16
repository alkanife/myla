package fr.alkanife.myla.commands;

import fr.alkanife.botcommons.Command;
import fr.alkanife.botcommons.Utils;
import fr.alkanife.myla.Gifs;
import fr.alkanife.myla.Myla;
import fr.alkanife.botcommons.Lang;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Locale;

public class Commands {

    @Command(name = "myla")
    public void myla(SlashCommandEvent slashCommandEvent) {

        if (Myla.getCreatorID() == null) {
            slashCommandEvent.reply(Lang.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        if (!slashCommandEvent.getUser().getId().equals(Myla.getCreatorID())) {
            slashCommandEvent.reply(Lang.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        OptionMapping args = slashCommandEvent.getOption("args");

        if (args == null) {
            slashCommandEvent.reply(Lang.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        String command = args.getAsString().toLowerCase(Locale.ROOT);

        switch (command) {
            case "reboot":
                slashCommandEvent.reply("Rebooting").queue(msg -> {
                    Myla.getLogger().info("Shutdown by " + slashCommandEvent.getUser().getName());
                    Myla.getJda().shutdownNow();
                    System.exit(0);
                });
                break;

            case "reload":
                try {
                    Lang.load();
                } catch (Exception e) {
                    slashCommandEvent.reply("Error while loading translations").setEphemeral(true).queue();
                    Myla.getLogger().error("Failing @ loading translations", e);
                }

                if (!Gifs.count()) {
                    slashCommandEvent.reply("Error while requesting gif count").setEphemeral(true).queue();
                    break;
                }

                slashCommandEvent.reply("Reload success").setEphemeral(true).queue();
                break;

            case "status":
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Status");
                embed.setDescription("Version: " + Myla.getVersion() + "\n\n" +
                        "Guilds: " + Myla.getJda().getGuilds().size() + "\n" +
                        "Updays: " + Utils.getUpDays() + "\n" +
                        "Total GIF count: " + Gifs.getTotalCount() + "\n" +
                        "Translations: " + Lang.getTranslations().size() + "\n" +
                        "Commands: " + Myla.getHandler().getCommands().size() + "\n" +
                        "Executed commands: " + Handler.executed);
                embed.setThumbnail(Myla.getJda().getSelfUser().getAvatarUrl());
                slashCommandEvent.replyEmbeds(embed.build()).setEphemeral(true).queue();
                break;

            default:
                slashCommandEvent.reply("Valid args: status, reboot, reload").setEphemeral(true).queue();
                break;
        }
    }

    @Command(name = "blush")
    public void blush(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("blush", Gifs.getBlushCount())).queue();
    }

    @Command(name = "cookie")
    public void cookie(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("cookie", Gifs.getCookieCount())).queue();
    }

    @Command(name = "cry")
    public void cry(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("cry", Gifs.getCryCount())).queue();
    }

    @Command(name = "headpat")
    public void headpat(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("headpat", Gifs.getHeadpatCount())).queue();
    }

    @Command(name = "hehe")
    public void hehe(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hehe", Gifs.getHeheCount())).queue();
    }

    @Command(name = "hi")
    public void hi(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hi", Gifs.getHiCount())).queue();
    }

    @Command(name = "hug")
    public void hug(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hug", Gifs.getHugCount())).queue();
    }

    @Command(name = "idk")
    public void idk(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("idk", Gifs.getIdkCount())).queue();
    }

    @Command(name = "kiss")
    public void kiss(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("kiss", Gifs.getKissCount())).queue();
    }

    @Command(name = "laugh")
    public void laugh(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("laugh", Gifs.getLaughCount())).queue();
    }

    @Command(name = "meme")
    public void meme(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("meme", Gifs.getMemeCount())).queue();
    }

    @Command(name = "notlikethis")
    public void notlikethis(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("notlikethis", Gifs.getNotlikethisCount())).queue();
    }

    @Command(name = "party")
    public void party(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("party", Gifs.getPartyCount())).queue();
    }

    @Command(name = "pout")
    public void pout(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("pout", Gifs.getPoutCount())).queue();
    }

    @Command(name = "punch")
    public void punch(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("punch", Gifs.getPunchCount())).queue();
    }

    @Command(name = "slap")
    public void slap(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("slap", Gifs.getSlapCount())).queue();
    }

    @Command(name = "smile")
    public void smile(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("smile", Gifs.getSmileCount())).queue();
    }

    @Command(name = "wink")
    public void wink(SlashCommandEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("wink", Gifs.getWinkCount())).queue();
    }

}
