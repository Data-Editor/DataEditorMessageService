package com.niek125.messageservice.utils;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileNotFoundException;

import static com.niek125.messageservice.utils.PemUtils.readPublicKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
public class PemUtilsTests {

    @Test
    public void fileNotFoundExceptionTest(){
        assertThrows(FileNotFoundException.class,
                () -> readPublicKeyFromFile("fy23yfv3u7ityfg", "RSA"));
    }

    @Test
    public void wrongAlgTest(){
        assertDoesNotThrow(
                () -> readPublicKeyFromFile("src/test/resources/PublicKey.pem", "IDK"));
    }
}
