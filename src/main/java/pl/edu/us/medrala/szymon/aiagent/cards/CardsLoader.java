package pl.edu.us.medrala.szymon.aiagent.cards;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class CardsLoader {

    public static byte[] getImageBytes(String imageName) {
        try {
            return Files.readAllBytes(Path.of(ResourceUtils.getFile("classpath:cards/" + imageName).getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
