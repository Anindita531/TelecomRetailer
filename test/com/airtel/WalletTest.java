package com.airtel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WalletTest {
    private Wallet wallet;

    @BeforeEach
    void setUp() { wallet = new Wallet(100); }

    @Test
    void testDeposit() {
        wallet.deposit(50, 1);
        assertEquals(150, wallet.getBalance());
    }

    @Test
    void testPaySuccess() {
        boolean result = wallet.pay(50, "Recharge", 1);
        assertTrue(result);
        assertEquals(50, wallet.getBalance());
    }

    @Test
    void testPayFail() {
        boolean result = wallet.pay(200, "Bill", 1);
        assertFalse(result);
        assertEquals(100, wallet.getBalance());
    }
}
