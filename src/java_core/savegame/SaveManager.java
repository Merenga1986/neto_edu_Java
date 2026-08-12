package java_core.savegame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Задача 2. Сохранение.
 * Сериализует объекты GameProgress в файлы, упаковывает их в zip-архив
 * и удаляет исходные файлы сохранений вне архива.
 */
public class SaveManager {

    /**
     * Сохраняет объект GameProgress в файл по указанному пути.
     *
     * @param filePath полный путь к файлу сохранения, например
     *                 "/Users/admin/Games/savegames/save1.dat"
     * @param progress сохраняемый объект
     */
    public void saveGame(String filePath, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Сохранение записано: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении " + filePath + ": " + e.getMessage());
        }
    }

    /**
     * Упаковывает список файлов в один zip-архив.
     *
     * @param zipFilePath   полный путь к создаваемому архиву,
     *                      например ".../savegames/zip.zip"
     * @param filesToZip    список полных путей запаковываемых файлов
     */
    public void zipFiles(String zipFilePath, List<String> filesToZip) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String filePath : filesToZip) {
                File fileToZip = new File(filePath);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла в архив " + filePath + ": " + e.getMessage());
                }
            }
            System.out.println("Архив создан: " + zipFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    /**
     * Удаляет файлы сохранений, не лежащие в архиве.
     */
    public void deleteFiles(List<String> filePaths) {
        for (String path : filePaths) {
            File file = new File(path);
            if (file.delete()) {
                System.out.println("Удалён файл: " + path);
            } else {
                System.out.println("Не удалось удалить файл: " + path);
            }
        }
    }

    public static void main(String[] args) {
        String savegamesDir = "./Games/savegames";

        // 1. Создаём три экземпляра GameProgress
        GameProgress progress1 = new GameProgress(100, 3, 1, 12.5);
        GameProgress progress2 = new GameProgress(80, 5, 2, 45.0);
        GameProgress progress3 = new GameProgress(60, 2, 3, 100.75);

        SaveManager manager = new SaveManager();

        // 2. Сохраняем объекты в папку savegames
        String save1 = savegamesDir + "/save1.dat";
        String save2 = savegamesDir + "/save2.dat";
        String save3 = savegamesDir + "/save3.dat";

        manager.saveGame(save1, progress1);
        manager.saveGame(save2, progress2);
        manager.saveGame(save3, progress3);

        List<String> saveFiles = new ArrayList<>(List.of(save1, save2, save3));

        // 3. Упаковываем файлы сохранений в архив
        String zipPath = savegamesDir + "/zip.zip";
        manager.zipFiles(zipPath, saveFiles);

        // 4. Удаляем файлы сохранений вне архива
        manager.deleteFiles(saveFiles);
    }
}
