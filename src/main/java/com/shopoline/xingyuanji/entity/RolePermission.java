package com.shopoline.xingyuanji.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuty
 * @since 2019-03-07
 */
@TableName("role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private Long roleId;
    @TableField("permission_id")
    private Long permissionId;


    public Long getRoleId() {
        return roleId;
    }

    public RolePermission setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public RolePermission setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        "roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
