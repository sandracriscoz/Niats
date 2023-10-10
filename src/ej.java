import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class ej {
    public static void main(String[] args) throws IOException {
        try (Stream <Path> rutas = Files.walk(Path.of("/tmp/niats"))){
             rutas.skip(1)
                    .sorted(Comparator.reverseOrder())
                    .forEach(ruta -> {
                        if (Files.isRegularFile(ruta)) {
                            try {
                                Files.move(ruta, Path.of("/tmp/niats").resolve(ruta.getFileName()));
                                System.out.println("Fichero movido");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (Files.isDirectory(ruta)) {
                            try {
                                Files.delete(ruta);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Directorio borrado");
                        }
                    })
                ;}
         }
    }