package com.xyj.myproject.service;

import com.xyj.myproject.entity.SysUser;

import java.util.List;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author XuYinjie
 * @since 2023-08-29 15:22:19
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser selectByName(String userName);

    /**
     * 根据用户手机号查询用户
     *
     * @param phoneNumb 手机号
     * @return 用户对象
     */
    SysUser selectByPhoneNumb(String phoneNumb);

}