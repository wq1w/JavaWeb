package com.nit.booksmanagementsystem.utils;

import java.security.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonUtil类提供了图书管理系统的实用方法和常量
 */
public class CommonUtil {
    // 用于存储登录状态、身份和用户 ID 的变量
    private static boolean isLogin = false;
    private static String identity = null;
    private static int userId;

    /**
     * 返回登录状态
     *
     * @return 如果用户已登录则返回true，否则返回false
     */
    public static boolean getIsLogin() {
        return isLogin;
    }

    /**
     * 设置登录状态
     *
     * @param isLogin 登录状态，true表示用户已登录，false表示用户未登录。
     */
    public static void setIsLogin(boolean isLogin) {
        CommonUtil.isLogin = isLogin;
    }

    /**
     * 返回身份信息
     *
     * @return 身份信息。
     */
    public static String getIdentity() {
        return identity;
    }

    /**
     * 设置身份信息
     *
     * @param identity 身份信息
     */
    public static void setIdentity(String identity) {
        CommonUtil.identity = identity;
    }

    /**
     * 返回用户ID
     *
     * @return 用户ID
     */
    public static int getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public static void setUserId(int userId) {
        CommonUtil.userId = userId;
    }

    // 存储不同角色的权限信息
    public static final Map<String, List<String[]>> permissions = new HashMap<>();

    static {
        // 用户权限
        List<String[]> userPermissions = new ArrayList<>();
        userPermissions.add(new String[]{"图书查询", "/router?page=book"});
        userPermissions.add(new String[]{"借阅记录", "/router?page=borrowing"});
        userPermissions.add(new String[]{"个人信息", "/router?page=user_profile"});

        // 管理员权限
        List<String[]> adminPermissions = new ArrayList<>();
        adminPermissions.add(new String[]{"用户管理", "/router?page=admin_user_management"});
        adminPermissions.add(new String[]{"添加用户", "/router?page=admin_add_user"});
        adminPermissions.add(new String[]{"类别管理", "/router?page=admin_book_type_management"});
        adminPermissions.add(new String[]{"添加类别", "/router?page=admin_book_type_insert"});
        adminPermissions.add(new String[]{"图书管理", "/router?page=admin_book_management"});
        adminPermissions.add(new String[]{"添加图书", "/router?page=admin_add_book"});
        adminPermissions.add(new String[]{"借阅记录", "/router?page=borrowing"});
        adminPermissions.add(new String[]{"个人信息", "/router?page=user_profile"});
        // 将权限信息存储在 permissions 中
        permissions.put(Identity.user, userPermissions);
        permissions.put(Identity.admin, adminPermissions);
    }

    /**
     * 获取时间的字符串表示。
     *
     * @param time 时间戳。
     * @return 格式化后的时间字符串。
     */
    public String getTime(Timestamp time) {
        return DateFormat.getDateTimeInstance().format(time);
    }
}