package ru.gb.patterns.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class MultimediaServiceImpl implements MultimediaService {

    private static final Logger logger = LoggerFactory.getLogger(MultimediaServiceImpl.class);

    private final MultimediaRepository multimediaRepository;

    @Value("${multimedia.storage.path}")
    private String storagePath;

    public MultimediaServiceImpl(MultimediaRepository multimediaRepository) {
        this.multimediaRepository = multimediaRepository;
    }

    @Override
    public Optional<MultimediaDto> getMultimediaById(long id) {
        return multimediaRepository.findById(id)
                .map(multimedia ->
                        new MultimediaDto(
                                multimedia.toBuilder().contentType("content"),
                                Paths.get(
                                        storagePath,
                                        String.valueOf(multimedia.toBuilder().storageFileName("filename")))))
                .filter(multimedia -> Files.exists(multimedia.getPath()))
                .map(multimedia -> {
                    try {
                        multimedia.setData(Files.readAllBytes(multimedia.getPath()));
                        return multimedia;
                    } catch (IOException ex) {
                        logger.error("Can't read file", ex);
                        throw new RuntimeException(ex);
                    }
                });
    }

    @Override
    public String create(byte[] multimedia) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(storagePath, fileName))) {
            outputStream.write(multimedia);
        } catch (IOException e) {
            logger.error("Can't write to file", e);
            throw new RuntimeException(e);
        }
        return fileName;
    }

    @Override
    public void deleteById(Long id) {
        multimediaRepository.deleteById(id);
    }
}
