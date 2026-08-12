package java_core.installer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задача 1. Установка.
 * Создаёт структуру папок и файлов игры внутри уже существующей папки Games
 * и пишет лог создания в файл temp/temp.txt.
 */
public class Installer {

    // Путь к папке Games — поменяйте под свою ОС/расположение.
    // Например, "/Users/admin/Games" (macOS/Linux) или "D:/Games" (Windows).
    private final String basePath;
    private final StringBuilder log = new StringBuilder();

    public Installer(String basePath) {
        this.basePath = basePath;
    }

    public void install() {
        // 1. В папке Games создаём директории: src, res, savegames, temp
        File src = createDir(basePath + "/src");
        File res = createDir(basePath + "/res");
        createDir(basePath + "/savegames");
        File temp = createDir(basePath + "/temp");

        // 2. В каталоге src создаём две директории: main, test
        File main = createDir(src.getPath() + "/main");
        createDir(src.getPath() + "/test");

        // 3. В подкаталоге main создаём два файла: Main.java, Utils.java
        createFile(main.getPath() + "/Main.java");
        createFile(main.getPath() + "/Utils.java");

        // 4. В каталог res создаём три директории: drawables, vectors, icons
        createDir(res.getPath() + "/drawables");
        createDir(res.getPath() + "/vectors");
        createDir(res.getPath() + "/icons");

        // 5. В директории temp создаём файл temp.txt и пишем туда лог
        String tempTxtPath = temp.getPath() + "/temp.txt";
        createFile(tempTxtPath);
        writeLog(tempTxtPath);
    }

    /**
     * Создаёт директорию по указанному пути и записывает результат в лог.
     */
    private File createDir(String path) {
        File dir = new File(path);
        boolean created = dir.mkdir();
        log.append("Директория ").append(path)
                .append(created ? " успешно создана" : " НЕ была создана (возможно, уже существует)")
                .append(System.lineSeparator());
        return dir;
    }

    /**
     * Создаёт файл по указанному пути и записывает результат в лог.
     */
    private File createFile(String path) {
        File file = new File(path);
        try {
            boolean created = file.createNewFile();
            log.append("Файл ").append(path)
                    .append(created ? " успешно создан" : " НЕ был создан (возможно, уже существует)")
                    .append(System.lineSeparator());
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(path)
                    .append(": ").append(e.getMessage())
                    .append(System.lineSeparator());
        }
        return file;
    }

    /**
     * Записывает накопленный лог в файл temp.txt.
     */
    private void writeLog(String tempTxtPath) {
        try (FileWriter writer = new FileWriter(tempTxtPath)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Не удалось записать лог: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Укажите свой путь к заранее созданной вручную папке Games
        String basePath = "./Games";
        new Installer(basePath).install();
        System.out.println("Установка завершена. Лог смотрите в " + basePath + "/temp/temp.txt");
    }
}
