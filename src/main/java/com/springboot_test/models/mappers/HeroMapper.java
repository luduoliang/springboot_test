package com.springboot_test.models.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
 
import com.springboot_test.models.Hero;
 
@Mapper
public interface HeroMapper {
 
    @Select("select * from hero ")
    List<Hero> findAll();
     
    @Insert(" insert into hero ( name, hp ) values (#{name}, #{hp}) ")
    public int save(Hero hero); 
     
    @Delete(" delete from hero where id= #{id} ")
    public void delete(int id);
         
    @Select("select * from hero where id= #{id} ")
    public Hero get(int id);
       
    @Update("update hero set name=#{name}, hp=#{hp} where id=#{id} ")
    public int update(Hero hero); 
 
}