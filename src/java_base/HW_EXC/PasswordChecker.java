package java_base.HW_EXC;

public class PasswordChecker {
    private int minLength = -1;       // -1 означает «не задано»
    private int maxRepeats = -1;      // -1 означает «не задано»

    /** Устанавливает минимальную длину пароля. */
    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException(
                    "Минимальная длина не может быть отрицательной: " + minLength);
        }
        this.minLength = minLength;
    }

    /** Устанавливает максимально допустимое количество повторений символа подряд. */
    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException(
                    "Максимальное количество повторений должно быть положительным: " + maxRepeats);
        }
        this.maxRepeats = maxRepeats;
    }

    /**
     * Проверяет пароль по заданным критериям.
     *
     * @return true — пароль подходит, false — не подходит.
     * @throws IllegalStateException если хотя бы одна настройка не была задана.
     */
    public boolean verify(String password) {
        if (minLength == -1 || maxRepeats == -1) {
            throw new IllegalStateException(
                    "Чекер не настроен: необходимо вызвать setMinLength() и setMaxRepeats()");
        }

        // Критерий 1: минимальная длина
        if (password.length() < minLength) {
            return false;
        }

        // Критерий 2: максимальное количество повторений символа подряд
        int count = 1;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                count++;
                if (count > maxRepeats) {
                    return false;
                }
            } else {
                count = 1;
            }
        }

        return true;
    }
}
