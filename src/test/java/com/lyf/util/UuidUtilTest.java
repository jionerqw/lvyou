package com.lyf.util;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UuidUtilTest {

    @Test
    public void getUuid() {
        for (int i = 0; i <10 ; i++) {
            String uuid = UuidUtil.getUuid();
            System.out.println(uuid);
        }
    }
}