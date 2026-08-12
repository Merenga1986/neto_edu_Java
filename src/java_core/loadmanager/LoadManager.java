package java_core.loadmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java_core.savegame.GameProgress;

/**
 * Задача 3 (необязательная). Загрузка.
 * Распаковывает архив с сохранениями и десериализует объект GameProgress.
 */
public class LoadManager {

    /**
     * Распаковывает zip-архив в указанную папку.
     *
     * @param zipFilePath  путь к архиву, например ".../savegames/zip.zip"
     * @param destDirPath  папка, куда распаковывать, например ".../savegames"
     */
    public void openZip(String zipFilePath, String destDirPath) {
        File destDir = new File(destDirPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File outFile = new File(destDir, entry.getName());

                try (FileOutputStream fos = new FileOutputStream(outFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) >= 0) {
                        fos.write(buffer, 0, length);
                    }
                }
                zis.closeEntry();
                System.out.println("Распакован файл: " + outFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }

    /**
     * Десериализует файл сохранения в объект GameProgress.
     *
     * @param filePath путь к файлу сохранения, например ".../savegames/save2.dat"
     * @return восстановленный объект GameProgress, либо null при ошибке
     */
    public GameProgress openProgress(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении сохранения " + filePath + ": " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String savegamesDir = "./Games/savegames";
        String zipPath = savegamesDir + "/zip.zip";

        LoadManager loadManager = new LoadManager();

        // 1. Распаковываем архив
        loadManager.openZip(zipPath, savegamesDir);

        // 2. Десериализуем один из файлов сохранений
        GameProgress progress = loadManager.openProgress(savegamesDir + "/save2.dat");

        // 3. Выводим состояние сохранённой игры
        System.out.println(progress);
    }
}
