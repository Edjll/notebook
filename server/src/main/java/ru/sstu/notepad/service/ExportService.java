package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sstu.notepad.model.record.RecordBody;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExportService {
    @Value("${export.filename}")
    private String filename;
    private final RecordService recordService;

    public String export() throws IOException {
        String workingDir = System.getProperty("user.dir");
        FileOutputStream fos = new FileOutputStream(filename);
        try (DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));) {
            outStream.writeUTF(recordService.getAll().stream()
                    .map(RecordBody::toString)
                    .reduce((s, s2) -> s + " " + s2)
                    .orElse(""));
        }
        return workingDir + "\\" + filename;
    }
}
