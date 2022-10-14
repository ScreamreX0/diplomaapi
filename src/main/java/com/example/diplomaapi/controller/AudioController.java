package com.example.diplomaapi.controller;

import com.example.diplomaapi.constants.CommonConstants;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/audio")
public class AudioController {

    @GetMapping("/test")
    public String test() {
        return "Hello world";
    }

    @GetMapping("/play/{audio_name}")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> playMedia(
            @PathVariable("audio_name") String audio_name,
            @RequestHeader(value = "Range", required = false) String rangeHeader
    ) throws IOException {
        try {
            StreamingResponseBody responseStream;
            String filePathString = CommonConstants.AUDIO_SOURCE_PATH + audio_name;
            Path filePath = Paths.get(filePathString);
            long fileSize = Files.size(filePath);
            byte[] buffer = new byte[1024];
            final HttpHeaders responseHeaders = new HttpHeaders();

            responseHeaders.add("Content-Type", "audio/mp3");
            responseHeaders.add("Content-Length", Long.toString(fileSize));
            responseStream = os -> {
                RandomAccessFile file = new RandomAccessFile(filePathString, "r");
                try (file) {
                    long pos = 0;
                    file.seek(pos);
                    while (pos < fileSize - 1) {
                        file.read(buffer);
                        os.write(buffer);
                        pos += buffer.length;
                    }
                    os.flush();
                } catch (Exception ignored) {
                }
            };

            return new ResponseEntity<StreamingResponseBody>(responseStream, responseHeaders, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
