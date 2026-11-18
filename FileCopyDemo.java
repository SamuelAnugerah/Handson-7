import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileCopyDemo {

    public static void main(String[] args) {

        Path source = Paths.get("report.txt");
        Path backup = Paths.get("backup/report.txt");
        Path log = Paths.get("log.txt");

        try {
            Files.write(source,
                    List.of("Report Generated", "Status: OK"));

            System.out.println("File report berhasil dibuat.");

            if (Files.notExists(backup.getParent())) {
                Files.createDirectories(backup.getParent());
            }

            if (Files.exists(source)) {

                Files.copy(source, backup,
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Backup berhasil dibuat.");

                Files.writeString(log,
                        LocalDateTime.now() +
                        " - Backup berhasil dibuat.\n",
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);

            } else {

                System.out.println("File sumber tidak ditemukan.");

                Files.writeString(log,
                        LocalDateTime.now() +
                        " - Gagal: File sumber tidak ditemukan.\n",
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {

            System.out.println("Terjadi kesalahan: " +
                    e.getMessage());
        }
    }
}