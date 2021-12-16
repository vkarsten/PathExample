import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PathExample1Test {

    @Test
    public void pathExists() throws Exception{
        // Retrieving files
        Path path = Paths.get("realFile.txt");
        assertThat(Files.exists(path)).isTrue();

        path = Paths.get("notRealFile.txt");
        assertThat(Files.exists(path)).isFalse();
    }

}