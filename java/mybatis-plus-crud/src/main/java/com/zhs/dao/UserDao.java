package com.zhs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
