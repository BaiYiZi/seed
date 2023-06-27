package com.zhs.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //id字段自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String xingMing;
    private String miMa;
    private String diZhi;
    private String aiHao;
}
