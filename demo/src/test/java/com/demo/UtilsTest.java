package com.demo;

import com.sdh2.demo.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void convertDate() {
        assertEquals("01/01/2021", Utils.convertDate("2021-01-01T00:00:00.000Z"));
        assertEquals("01/01/2021", Utils.convertDate("2021-01-01T00:00:00.000"));
        assertEquals("01/01/2021", Utils.convertDate("2021-01-01"));
    }


}