package com.bruce.pc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtil {
    @Test
   public void testRead() {
        TestUtil testUtil = new TestUtil();
        String s = testUtil.readResource("blog_feed.xml");
        System.out.print(s);
    }

    public String readResource(String filename) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputreader = new InputStreamReader(is);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        char c;
        try {
            while ((line = buffreader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return sb.toString();
    }
}
