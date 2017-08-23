package com.example.helper;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DefaultServiceKeyGetterTest {
    private DefaultServiceKeyGetter defaultServiceKeyGetter;

    @Before
    public void setUp() throws Exception {
        defaultServiceKeyGetter = new DefaultServiceKeyGetter();
    }

    @Test
    public void test_getServiceKey_returnsText_weather() throws Exception {
        String key1 = defaultServiceKeyGetter.getServiceKey("天気");
        String key2 = defaultServiceKeyGetter.getServiceKey("てんき");
        String key3 = defaultServiceKeyGetter.getServiceKey("テンキ");
        String key4 = defaultServiceKeyGetter.getServiceKey("天気教えて");
        String key5 = defaultServiceKeyGetter.getServiceKey("weather");


        assertThat(key1, equalTo("weather"));
        assertThat(key2, equalTo("weather"));
        assertThat(key3, equalTo("weather"));
        assertThat(key4, equalTo("weather"));
        assertThat(key5, equalTo("weather"));
    }

    @Test
    public void test_getServiceKey_returnsText_default() throws Exception {
        String key1 = defaultServiceKeyGetter.getServiceKey("");
        String key2 = defaultServiceKeyGetter.getServiceKey("教えて！天気！");


        assertThat(key1, equalTo("default"));
        assertThat(key2, equalTo("default"));
    }
}