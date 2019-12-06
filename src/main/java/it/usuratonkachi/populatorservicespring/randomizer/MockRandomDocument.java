package it.usuratonkachi.populatorservicespring.randomizer;

import it.usuratonkachi.populatorservicespring.data.entity.Doc;
import it.usuratonkachi.populatorservicespring.data.entity.InternalDoc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MockRandomDocument {

    private static Random random = new Random();
    private static List<String> mimetypes = List.of(
            "application/pdf",
            "image/bmp",
            "text/csv",
            "image/gif",
            "application/msword",
            "image/gif",
            "text/html",
            "image/jpeg",
            "image/png",
            "text/plain",
            "application/zip"
    );

    private static Map<String, String> docType = Map.of(
            "123e4567-e89b-12d3-a456-426655440000", "documento_informatico",
            "123e4567-e89b-12d3-a456-426655440001", "posta_elettronica",
            "123e4567-e89b-12d3-a456-426655440002", "fattura",
            "123e4567-e89b-12d3-a456-426655440003", "notifica",
            "123e4567-e89b-12d3-a456-426655440004", "lotto",
            "123e4567-e89b-12d3-a456-426655440005", "contratto",
            "123e4567-e89b-12d3-a456-426655440006", "registro_protocollo",
            "123e4567-e89b-12d3-a456-426655440007", "protocollo_amministrativo",
            "123e4567-e89b-12d3-a456-426655440008", "decreto_regionale",
            "123e4567-e89b-12d3-a456-426655440009", "delibere"
    );

    public static Doc generateDoc(String username){
        int randStatus = random.nextInt(5);
        int randMime = random.nextInt(mimetypes.size());
        String randType = docType.keySet().stream().skip(random.nextInt(docType.size())).findFirst().orElse("123e4567-e89b-12d3-a456-426655440000");
        LocalDateTime uploaddate = between();

        return (Doc) new Doc()
                .id(UUID.randomUUID().toString())
                .username(username)
                .doctypeid(randType)
                .doctypename(docType.get(randType))
                .mimetype(mimetypes.get(randMime))
                .size(ThreadLocalRandom.current().nextLong(100) + 1)
                .status(randStatus == 0 ? 0 : 1)
                .uploaddate(uploaddate)
                .archiveddate(randStatus == 0 ? null : between(uploaddate))
                .filename(UUID.randomUUID().toString() + "." + mimetypes.get(randMime).split("/")[1]);
    }

    public static InternalDoc generateInternalDoc(String username){
        int randStatus = random.nextInt(5);
        int randMime = random.nextInt(mimetypes.size());
        String randType = docType.keySet().stream().skip(random.nextInt(docType.size())).findFirst().orElse("123e4567-e89b-12d3-a456-426655440000");
        LocalDateTime uploaddate = between();

        return (InternalDoc) new InternalDoc()
                .id(UUID.randomUUID().toString())
                .username(username)
                .doctypeid(randType)
                .doctypename(docType.get(randType))
                .mimetype(mimetypes.get(randMime))
                .size(ThreadLocalRandom.current().nextLong(100) + 1)
                .status(randStatus == 0 ? 0 : 1)
                .uploaddate(uploaddate)
                .archiveddate(randStatus == 0 ? null : between(uploaddate))
                .filename(UUID.randomUUID().toString() + "." + mimetypes.get(randMime).split("/")[1]);
    }

    public static LocalDateTime between(LocalDateTime uplodDate) {
        return between(uplodDate.toEpochSecond(ZoneOffset.ofHours(1)), LocalDateTime.of(2025, 1, 1, 0, 0, 0).toInstant(ZoneOffset.ofHours(1)).getEpochSecond());
    }

    private static LocalDateTime between() {
        long startSeconds = LocalDateTime.of(2019, 1, 1, 0, 0, 0).toInstant(ZoneOffset.ofHours(1)).getEpochSecond();
        long endSeconds = LocalDateTime.of(2025, 1, 1, 0, 0, 0).toInstant(ZoneOffset.ofHours(1)).getEpochSecond();
        return between(startSeconds, endSeconds);
    }

    public static LocalDateTime between(long startInclusive, long endExclusive) {
        long random = ThreadLocalRandom.current().nextLong(startInclusive, endExclusive);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(random), ZoneId.systemDefault());
    }

}
