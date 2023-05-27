package com.github.leandrochp.consumerservice.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestUtils {

    public static String readFile(String fileName) throws IOException {
        File file = getFile(fileName);
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    private static File getFile(String fileName) throws IOException {
        return new ClassPathResource(String.format("/samples/requests/%s.json", fileName)).getFile();
    }

}
