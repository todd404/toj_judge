package com.todd.judger;

import com.todd.judger.util.MyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SpringBootTest
class JudgerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void downloadFileTest() throws IOException {
        File file = new File(  "temp/answer.txt");
        file.createNewFile();
        MyUtils.downloadFile("http://localhost:5000/1.txt", file.getPath());
    }

    @Test
    void trimTest(){
        String line = "\n";
        String result = line.trim();
        return;
    }

}
