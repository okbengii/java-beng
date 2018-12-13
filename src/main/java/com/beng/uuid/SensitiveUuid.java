package com.beng.uuid;

import java.util.UUID;

/**
 * uuid: 本地生成，没有网络开销
 * 
 * @why 为什么要用uuid？
 * @reason 定义唯一的批次号
 * @focus mysql id 主键 分布式策略 设置步长; 8-4-4-4-12
 * @author apple
 */

public class SensitiveUuid {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}
