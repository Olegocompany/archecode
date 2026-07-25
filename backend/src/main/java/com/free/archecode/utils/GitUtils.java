package com.free.archecode.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GitUtils {

    /**
     * Проверяет на существование Git-репозитория. Использует Git команду
     * "git ls-remote --exit-code -h".
     * Используется HTTPS ссылка.
     * @param link
     * @return 0 - found, 1 - not found, 2 - error (maybe in system)
     */
    public byte isGitRepositoryExists(String link)
    {
        if (!link.startsWith("https://")) {
            link = "https://" + link;
        }

        ProcessBuilder pb = new ProcessBuilder("git", "ls-remote", "--exit-code", "-h", link);
        pb.redirectErrorStream(true);
        pb.environment().put("GIT_TERMINAL_PROMPT", "0");

        try {
            Process p = pb.start();
            p.waitFor(10, TimeUnit.SECONDS);

            if (p.exitValue() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 2;
        }
    }
}
