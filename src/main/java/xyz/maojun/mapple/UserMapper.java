package xyz.maojun.mapple;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.maojun.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
