package fr.alkanife.myla.commands;

import fr.alkanife.myla.Gifs;
import fr.alkanife.myla.Myla;
import fr.alkanife.myla.TranslationsLoader;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Locale;

public class Commands {

    @Command(name = "myla")
    public void myla(SlashCommandInteractionEvent slashCommandEvent) {

        if (Myla.getConfig().getAdmin_id() == null) {
            slashCommandEvent.reply(Myla.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        if (!slashCommandEvent.getUser().getId().equals(Myla.getConfig().getAdmin_id())) {
            slashCommandEvent.reply(Myla.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        OptionMapping args = slashCommandEvent.getOption("args");

        if (args == null) {
            slashCommandEvent.reply(Myla.t("myla-command")).setEphemeral(true).queue();
            return;
        }

        String command = args.getAsString().toLowerCase(Locale.ROOT);

        switch (command) {
            case "reboot":
                slashCommandEvent.reply("Rebooting").setEphemeral(true).queue(msg -> {
                    Myla.getLogger().info("Shutdown by " + slashCommandEvent.getUser().getName());
                    Myla.getJDA().shutdownNow();
                    System.exit(0);
                });
                break;

            case "reload":
                try {
                    TranslationsLoader translationsLoader = new TranslationsLoader(true);
                    Myla.setTranslations(translationsLoader.getTranslations());
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

            case "count":
                String count = "blush: " + Gifs.getBlushCount() + "\n" +
                        "cookie: " + Gifs.getCookieCount() + "\n" +
                        "cry: " + Gifs.getCryCount() + "\n" +
                        "headpat: " + Gifs.getHeadpatCount() + "\n" +
                        "hehe: " + Gifs.getHeheCount() + "\n" +
                        "hi: " + Gifs.getHiCount() + "\n" +
                        "hug: " + Gifs.getHugCount() + "\n" +
                        "idk: " + Gifs.getIdkCount() + "\n" +
                        "kiss: " + Gifs.getKissCount() + "\n" +
                        "laugh: " + Gifs.getLaughCount() + "\n" +
                        "meme: " + Gifs.getMemeCount() + "\n" +
                        "notlikethis: " + Gifs.getNotlikethisCount() + "\n" +
                        "party: " + Gifs.getPartyCount() + "\n" +
                        "pout: " + Gifs.getPoutCount() + "\n" +
                        "punch: " + Gifs.getPunchCount() + "\n" +
                        "slap: " + Gifs.getSlapCount() + "\n" +
                        "smile: " + Gifs.getSmileCount() + "\n" +
                        "wink: " + Gifs.getWinkCount() + "\n" +
                        "pray: " + Gifs.getPrayCount();

                slashCommandEvent.reply(count).setEphemeral(true).queue();
                break;

            default:
                slashCommandEvent.reply("Valid args: count, reboot, reload").setEphemeral(true).queue();
                break;
        }
    }

    @Command(name = "blush")
    public void blush(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("blush", Gifs.getBlushCount())).queue();
    }

    @Command(name = "cookie")
    public void cookie(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("cookie", Gifs.getCookieCount())).queue();
    }

    @Command(name = "cry")
    public void cry(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("cry", Gifs.getCryCount())).queue();
    }

    @Command(name = "headpat")
    public void headpat(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("headpat", Gifs.getHeadpatCount())).queue();
    }

    @Command(name = "hehe")
    public void hehe(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hehe", Gifs.getHeheCount())).queue();
    }

    @Command(name = "hi")
    public void hi(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hi", Gifs.getHiCount())).queue();
    }

    @Command(name = "hug")
    public void hug(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("hug", Gifs.getHugCount())).queue();
    }

    @Command(name = "idk")
    public void idk(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("idk", Gifs.getIdkCount())).queue();
    }

    @Command(name = "kiss")
    public void kiss(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("kiss", Gifs.getKissCount())).queue();
    }

    @Command(name = "laugh")
    public void laugh(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("laugh", Gifs.getLaughCount())).queue();
    }

    @Command(name = "meme")
    public void meme(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("meme", Gifs.getMemeCount())).queue();
    }

    @Command(name = "notlikethis")
    public void notlikethis(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("notlikethis", Gifs.getNotlikethisCount())).queue();
    }

    @Command(name = "party")
    public void party(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("party", Gifs.getPartyCount())).queue();
    }

    @Command(name = "pout")
    public void pout(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("pout", Gifs.getPoutCount())).queue();
    }

    @Command(name = "punch")
    public void punch(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("punch", Gifs.getPunchCount())).queue();
    }

    @Command(name = "slap")
    public void slap(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("slap", Gifs.getSlapCount())).queue();
    }

    @Command(name = "smile")
    public void smile(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("smile", Gifs.getSmileCount())).queue();
    }

    @Command(name = "wink")
    public void wink(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("wink", Gifs.getWinkCount())).queue();
    }

    @Command(name = "pray")
    public void pray(SlashCommandInteractionEvent slashCommandEvent) {
        slashCommandEvent.reply(Gifs.get("pray", Gifs.getPrayCount())).queue();
    }

}