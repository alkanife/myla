package fr.alkanife.myla;

import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String absolutePath() {
        return Paths.get("").toAbsolutePath().toString();
    }

    public static long getUpDays(){
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        return TimeUnit.MILLISECONDS.toDays(uptime);
    }
}

