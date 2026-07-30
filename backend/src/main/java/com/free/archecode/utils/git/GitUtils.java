package com.free.archecode.utils.git;

public interface GitUtils {
    /**
     * Проверяет на существование Git-репозитория. Использует Git команду
     * "git ls-remote --exit-code -h".
     * Используется HTTPS ссылка.
     * @param link
     * @return 0 - found, 1 - not found, 2 - error (in system)
     */
    public byte isGitRepositoryExists(String link);
}
