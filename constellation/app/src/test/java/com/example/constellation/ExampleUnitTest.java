package com.example.constellation;

import android.content.Context;

import com.example.constellation.utils.AssetsUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void jsonTest(){
        AssetsUtils.getJsonFromAssets(null, "/assets/xzcontent/xzcontent.json");
    }
}