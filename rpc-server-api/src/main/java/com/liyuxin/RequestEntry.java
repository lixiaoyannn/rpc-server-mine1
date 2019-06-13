package com.liyuxin;

import java.io.Serializable;

/**
 * 创建者:小䶮
 */
public class RequestEntry implements Serializable{
    private String className;
    private String methodName;
    private Object[] args;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
