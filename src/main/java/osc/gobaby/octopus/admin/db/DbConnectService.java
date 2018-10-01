package osc.gobaby.octopus.admin.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import osc.gobaby.octopus.admin.db.entity.DbConnect;
import osc.gobaby.octopus.admin.db.repository.DbConnectMapper;

@Service
public class DbConnectService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DbConnectMapper dbConnectMapper;

    public synchronized boolean init(DbConnect dbConnect) {

        if (!DbConnectUtils.isCreatedDbConnectMetaFile()) {
            //basicDataSource init
            BasicDataSource basicDataSource = context.getBean(BasicDataSource.class);
            if (basicDataSource == null || StringUtils.isBlank(basicDataSource.getDriverClassName())) {
                BasicDataSource newDataSource = DbConnectUtils.createBasicDataSource(dbConnect);
                basicDataSource.setDriverClassName(newDataSource.getDriverClassName());
                basicDataSource.setUrl(newDataSource.getUrl());
                basicDataSource.setUsername(newDataSource.getUsername());
                basicDataSource.setPassword(newDataSource.getPassword());
            }

            //ddl - create db / table / insert admin user
            basicDataSource.setUrl(DbConnectUtils.createDbUrlForNoDb(dbConnect));
            boolean isCreatedDatabase = createDatabase();
            boolean isChoicedDb = choiceDb();

            basicDataSource.setUrl(DbConnectUtils.createDbUrl(dbConnect));

            boolean isCreatedTable = createTable();
            boolean isInsertAdminUser = insertAdminUser();

            //file init
            if(isCreatedDatabase && isCreatedTable && isInsertAdminUser) {
                DbConnectUtils.createDbConnectMetaFile(dbConnect);
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean createDatabase() {
        try {
            dbConnectMapper.createDb();
            return true;
        } catch (Exception e){
            if(e.getMessage().contains("exists")){
                return true;
            }
        }

        return false;
    }

    public boolean choiceDb() {
        try {
            dbConnectMapper.choiceDb();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean createTable() {
        try {
            boolean isCreatedUserTable = createUserTable();
            boolean isCreatedServerTable = createServerTable();
            boolean isCreatedQueryTable = createQueryTable();

            return isCreatedUserTable && isCreatedServerTable && isCreatedQueryTable ? true : false;
        } catch (Exception e){
            return false;
        }
    }

    public boolean insertAdminUser() {
        try {
            dbConnectMapper.insertAdminUser();
            return true;
        } catch (Exception e){
            if(e.getMessage().contains("Duplicate")){
                return true;
            }
            return false;
        }
    }

    private boolean createUserTable() {
        try {
            dbConnectMapper.createUserTable();
            return true;
        } catch (Exception e){
            if(e.getMessage().contains("exists")){
                return true;
            }
        }

        return false;
    }

    private boolean createServerTable() {
        try {
            dbConnectMapper.createServerTable();
            return true;
        } catch (Exception e){
            if(e.getMessage().contains("exists")){
                return true;
            }
        }

        return false;
    }

    private boolean createQueryTable() {
        try {
            dbConnectMapper.createQueryTable();
            return true;
        } catch (Exception e){
            if(e.getMessage().contains("exists")){
                return true;
            }
        }

        return false;
    }
}
