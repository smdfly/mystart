package cn.itcast.mp.tasklock;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.Map;

public interface LockMapper {
//
//    @Select("SELECT COUNT(*) FROM distributed_lock WHERE lock_name = #{lockName}")
//    int countLock(String lockName);
//
  //  @Insert("INSERT INTO distributed_lock (lock_name, locked_time) VALUES (#{lockName}, NOW()) " +
   //         "ON DUPLICATE KEY UPDATE locked_time = NOW()")
@Insert("INSERT INTO distributed_lock (lock_name, locked_by, locked_time) VALUES (#{lockName},#{locked_by},#{locked_time}) ")
    int acquireLock(String lockName, String locked_by, LocalDateTime locked_time);


@Insert("INSERT INTO user (username,password,email) VALUES (#{username},#{password},#{email})")
int addUser(String username,String password,String email);

//
//    @Insert("DELETE FROM distributed_lock WHERE lock_name = #{lockName}")
//    int releaseLock(String lockName);

    @Select("select * from product_stock where id = #{id} for update")
    Map selectForLock(@Param("id") Integer id);

    @Select("select * from product_stock where id = #{id}")
    Map selectForID(@Param("id") Integer id);
    @Update("UPDATE product_stock SET version=version+1 WHERE id= #{id} AND version= #{version} ")
    int updateID(@Param("id") Integer id,@Param("version") Integer version);



}