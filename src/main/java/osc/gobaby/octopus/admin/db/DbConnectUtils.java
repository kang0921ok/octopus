package osc.gobaby.octopus.admin.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp.BasicDataSource;
import osc.gobaby.octopus.admin.db.entity.DbConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DbConnectUtils {
    public static final String DB_NAME = "octopus";

    public static final String DB_META_FILE_NAME = "octopus.db";

    public static BasicDataSource createBasicDataSource(DbConnect dbConnect) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl(DbConnectUtils.createDbUrl(dbConnect));
        basicDataSource.setUsername(dbConnect.getUserName());
        basicDataSource.setPassword(dbConnect.getPassword());

        return basicDataSource;
    }

    public static String createDbUrl(DbConnect dbConnect) {
        return "jdbc:mysql://" + dbConnect.getIp() + ":" + dbConnect.getPort() + "/" + DB_NAME + "?useUnicode=yes&characterEncoding=UTF-8";
    }

    public static String createDbUrlForNoDb(DbConnect dbConnect) {
        return "jdbc:mysql://" + dbConnect.getIp() + ":" + dbConnect.getPort() + "?useUnicode=yes&characterEncoding=UTF-8";
    }

    public static boolean isCreatedDbConnectMetaFile(){
        File file = new File(DB_META_FILE_NAME);
        return file.exists();
    }

    public static void createDbConnectMetaFile(DbConnect dbConnect) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String dbConnectStr = objectMapper.writeValueAsString(dbConnect);

            FileOutputStream output = new FileOutputStream(DB_META_FILE_NAME);
            output.write(dbConnectStr.getBytes());
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbConnect findDbConnectMetaFile() {
        try {
            FileInputStream input = new FileInputStream(DB_META_FILE_NAME);
            byte[] buffer = new byte[input.available()];
            while (input.read(buffer) != -1) {

            }
            String dbConnectStr = new String(buffer);

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(dbConnectStr, DbConnect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
