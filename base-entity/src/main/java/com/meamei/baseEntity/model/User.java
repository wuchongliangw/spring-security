package com.meamei.baseEntity.model;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;


/**
 * @author mm013
 * @create 2019-11-11 11:27:30
 * @description: 用户表
 */
@Data
@javax.persistence.Table(name = "t_user")
@Table(name = "t_user")
public class User extends BaseEntity{

    @javax.persistence.Column(name = "account")
    @Column(name = "account", type = MySqlTypeConstant.VARCHAR, comment = "账号")
    private String account;

    @javax.persistence.Column(name = "password")
    @Column(name = "password", type = MySqlTypeConstant.VARCHAR, comment = "密码")
    private String password;

    @javax.persistence.Column(name = "is_locked")
    @Column(name = "is_locked", type = MySqlTypeConstant.INT, comment = "是否锁定")
    private Boolean isLocked;

    @javax.persistence.Column(name = "login_failure_count")
    @Column(name = "login_failure_count", type = MySqlTypeConstant.INT, comment = "连续登录失败次数")
    private Integer loginFailureCount;

    @javax.persistence.Column(name = "locked_date")
    @Column(name = "locked_date", type = MySqlTypeConstant.DATETIME, comment = "锁定日期")
    private Date lockedDate;

    @javax.persistence.Column(name = "login_date")
    @Column(name = "login_date", type = MySqlTypeConstant.DATETIME, comment = "最后登录日期")
    private Date loginDate;
    
    @javax.persistence.Column(name = "is_first_login")
    @Column(name = "is_first_login", type = MySqlTypeConstant.INT, length = 1, comment = "用户是否在店铺审核通过后第一次登录")
    private Boolean isFirstLogin;
}
