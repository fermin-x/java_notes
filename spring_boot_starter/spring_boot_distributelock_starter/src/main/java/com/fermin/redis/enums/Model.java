package com.fermin.redis.enums;

/**
 * redis集群模式
 */
public enum Model {
    /*哨兵*/
    SENTINEL,
    /*主从*/
    MASTERSLAVE,
    /*单例*/
    SINGLE,
    /*集群*/
    CLUSTER,
    /*云托管模式*/
    REPLICATED
}
