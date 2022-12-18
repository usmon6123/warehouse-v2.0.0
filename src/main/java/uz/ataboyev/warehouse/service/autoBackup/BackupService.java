//package uz.ataboyev.warehouse.service.autoBackup;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Date;
//
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class BackupService {
//
//
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${backup.path}")
//    private String backupPath;
//
//
//
//    @Scheduled(fixedDelay = 1000 * 60 * 60 * 8) // har 8 soatda ishlaydi
//    public void backup() {
//        try {
//            log.info("______________Start backup______________");
//
//            String fileName = new Date() + "-warehouse.bak";
//
//            fileName = fileName.replace(" ", "-");
//
//            String[] split = dbUrl.split("/");
//
//            String dbDatabase = split[split.length-1]; // warehouse
//
//            String[] strings = {"pg_dump", "-Fc", "--dbname=postgresql://" + dbUsername + ":" + dbPassword + "@127.0.0.1:5432/" + dbDatabase + "", "-f", backupPath + fileName};
//
//            String s = run(strings);
//
//            getBackUpAndSentToTelegramBot(fileName);
//
//            log.info("______________Stop backup______________");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getBackUpAndSentToTelegramBot(String fileName) {
//        try {
//
//            File file = ResourceUtils.getFile(backupPath + fileName);
//
//            sendTelegramBot(file);
//
//            log.info("SUCCESS UPLOAD");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void sendTelegramBot(File file) {
//        // TODO: 30.11.2022
//
//        String fileName = file.getName();
//        log.info("___________________ fileName _____________ {}", fileName);
//        log.info("___________________ start _____________ {}", new Date());
////        amazonS3.putObject(
////                new PutObjectRequest(
////                        bucketName,
////                        fileName,
////                        file
////                )
////        );
//        log.info("___________________ end _____________ {}", new Date());
//
//    }
//
//    private String run(String[] commands) throws IOException, InterruptedException {
//
//        ProcessBuilder pb = new ProcessBuilder(commands);
//        Process process = pb.start();
//        BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        String lineError;
//        StringBuilder s = new StringBuilder();
//        while ((lineError = errorStream.readLine()) != null) {
//            s.append(lineError);
//        }
//
//        errorStream.close();
//        process.waitFor();
//        return s.toString();
//    }
//
//
//}
