package cn.itcast.mp.mysqllock;

import cn.itcast.mp.tasklock.LockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Administrator
 */
@Component
public class MysqlDistributedLock {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlDistributedLock(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
@Autowired
    LockMapper lockMapper;
    public String tryLock(String lockName, long timeoutSeconds) throws SQLIntegrityConstraintViolationException , DuplicateKeyException {
        String lockValue = UUID.randomUUID().toString();
       // long expirationTime = System.currentTimeMillis() + timeoutSeconds * 1000;
       // Timestamp expirationTimestamp = new Timestamp(expirationTime);
        LocalDateTime localDateTime= new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        System.out.println("当前时间"+localDateTime);

      int i=  lockMapper.acquireLock(lockName,lockValue,localDateTime);


//        int updatedRows = jdbcTemplate.update(
//                "INSERT INTO distributed_lock (lock_name, locked_by, locked_time) VALUES (?,?,?) " +
//                        "ON DUPLICATE KEY UPDATE locked_by =?, locked_time =? " +
//                        "WHERE lock_name =? AND locked_by IS NULL OR locked_time <?)",
//                lockName, lockValue, expirationTime,
//                lockValue, expirationTime, lockName, expirationTime);
        return i > 0? lockValue : null;
    }

    public boolean releaseLock(String lockName, String lockValue) {
        int updatedRows = jdbcTemplate.update(
                "DELETE FROM distributed_lock WHERE lock_name =? AND locked_by =?",
                lockName, lockValue);
        return updatedRows > 0;
    }


        public String  GetProcessId() {
            // 获取当前运行的进程 ID
            String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

            System.out.println("当前进程 ID: " + processId);
            return  processId;

    }

}