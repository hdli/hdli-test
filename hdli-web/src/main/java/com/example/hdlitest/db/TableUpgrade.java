package com.example.hdlitest.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author luyi
 * @date 2021/5/6 9:05 下午
 */
@Component
public class TableUpgrade {

    private static Logger log = LoggerFactory.getLogger(TableUpgrade.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void start() {
        checkAndCreateFlywayTable();
    }


    public boolean checkAndCreateFlywayTable() {
        try(Connection connection = dataSource.getConnection()){
            try(Statement statement = connection.createStatement()){
                return statement.execute("CREATE TABLE IF NOT EXISTS `flyway_schema_history` (\n" +
                        "    `installed_rank` INT NOT NULL,\n" +
                        "    `version` VARCHAR(50),\n" +
                        "    `description` VARCHAR(200) NOT NULL,\n" +
                        "    `type` VARCHAR(20) NOT NULL,\n" +
                        "    `script` VARCHAR(1000) NOT NULL,\n" +
                        "    `checksum` INT,\n" +
                        "    `installed_by` VARCHAR(100) NOT NULL,\n" +
                        "    `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                        "    `execution_time` INT NOT NULL,\n" +
                        "    `success` BOOLEAN  NOT NULL,\n" +
                        "    \n" +
                        "    CONSTRAINT `flyway_schema_history_pk`PRIMARY KEY (`installed_rank`)\n" +
                        ") ENGINE=InnoDB");
            }
        } catch (SQLException throwables) {
            log.error("创建flyway_schema_history失败", throwables);
            return false;
        }
    }
}
