package com.nit.booksmanagementsystem.utils;

/**
 * Result类是一个通用的结果类，用于封装操作结果和相关的数据。
 *
 * @param <T> 结果数据的类型
 */
public class Result<T> {
    private int code; // 结果代码
    private T data; // 结果数据

    /**
     * 默认构造函数，创建一个空的Result对象。
     */
    public Result() {
    }

    /**
     * 构造函数，创建一个包含结果代码和数据的Result对象。
     *
     * @param code 结果代码
     * @param data 结果数据
     */
    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 获取结果代码。
     *
     * @return 结果代码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置结果代码。
     *
     * @param code 结果代码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取结果数据。
     *
     * @return 结果数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置结果数据。
     *
     * @param data 结果数据
     */
    public void setData(T data) {
        this.data = data;
    }
}