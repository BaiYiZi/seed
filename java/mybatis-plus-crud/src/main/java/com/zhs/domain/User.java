package com.zhs.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("yonghuxinxi")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("xingMing")
    private String XingMing;
    @TableField("miMa")
    private String MiMa;
    @TableField("diZhi")
    private String DiZhi;
    @TableField("aiHao")
    private String AiHao;
}
