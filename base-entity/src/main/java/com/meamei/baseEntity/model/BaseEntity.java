package com.meamei.baseEntity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 */
@Data
public abstract class BaseEntity implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "id")
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, length = 20, isNull = false, isKey = true, isAutoIncrement = true)
    private Long id;

    /**
     * 创建日期
     */
    @javax.persistence.Column(name = "create_date", updatable = false)
    @Column(name = "create_date", type = MySqlTypeConstant.DATETIME, isNull = false)
    private Date createDate;

    /**
     * 修改日期
     */
    @javax.persistence.Column(name = "modify_date")
    @Column(name = "modify_date", type = MySqlTypeConstant.DATETIME, isNull = false)
    private Date modifyDate;

    /**
     * 逻辑删除
     */
    @JsonIgnore
    @LogicDelete
    @javax.persistence.Column(name = "is_deleted")
    @Column(name = "is_deleted", type = MySqlTypeConstant.INT, isNull = false)
    private Integer isDeleted;

    /**
     * 重写hashCode方法
     *
     * @return HashCode
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += getId() != null ? getId().hashCode() * 31 : 0;
        return hashCode;
    }

    /**
     * equals方法
     *
     * @param obj 对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        return getId() != null && getId().equals(other.getId());
    }
}
