package com.ingenico;

import com.ingenico.dto.AccountDto;
import com.ingenico.dto.TransferDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferIntegrationTest {

    @Autowired
    WebTestClient webClient;

    AccountDto validAccountDto;
    AccountDto otherValidAccountDto;
    AccountDto invalidBalAccountDto;

    @Before
    public void setup() {
        validAccountDto = new AccountDto();
        validAccountDto.setName("Facundo");
        validAccountDto.setBalance(new BigDecimal(100));

        otherValidAccountDto = new AccountDto();
        otherValidAccountDto.setName("Santiago");
        otherValidAccountDto.setBalance(new BigDecimal(200));

        invalidBalAccountDto = new AccountDto();
        invalidBalAccountDto.setName("Manu");
        invalidBalAccountDto.setBalance(new BigDecimal(-100));
    }

    @Test
    public void createValidAccountTest() {
        AccountDto expected = Cloner.getCloneFrom(validAccountDto);

        webClient.post().uri("/account")
                .syncBody(validAccountDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AccountDto.class)
                .isEqualTo(expected);
    }

    @Test
    public void createAccountInvalidBalance() {
        webClient.post().uri("/account")
                .syncBody(invalidBalAccountDto)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Balance cannot be negative");
    }


    @Test
    public void transferOkTest() {
        EntityExchangeResult<AccountDto> accountDto1 = webClient.post().uri("/account")
                .syncBody(validAccountDto)
                .exchange()
                .expectBody(AccountDto.class)
                .returnResult();

        EntityExchangeResult<AccountDto> accountDto2 = webClient.post().uri("/account")
                .syncBody(otherValidAccountDto)
                .exchange()
                .expectBody(AccountDto.class)
                .returnResult();

        TransferDto transferDto = new TransferDto();
        transferDto.setAccountFrom(accountDto1.getResponseBody().getId());
        transferDto.setAccountTo(accountDto2.getResponseBody().getId());
        transferDto.setMoney(new BigDecimal(10));

        TransferDto expected = Cloner.getCloneFrom(transferDto);

        webClient.post().uri("/transfer")
                .syncBody(transferDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TransferDto.class)
                .isEqualTo(expected);
    }

    @Test
    public void transferAccountNotFoundTest() {
        TransferDto transferDto = new TransferDto();
        transferDto.setAccountFrom(9999L);
        transferDto.setAccountTo(1L);
        transferDto.setMoney(new BigDecimal(10));

        webClient.post().uri("/transfer")
                .syncBody(transferDto)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Account ID 9999 not found");
    }

    @Test
    public void transferOverdrawnAccountTest() {
        EntityExchangeResult<AccountDto> accountDto1 = webClient.post().uri("/account")
                .syncBody(validAccountDto)
                .exchange()
                .expectBody(AccountDto.class)
                .returnResult();

        EntityExchangeResult<AccountDto> accountDto2 = webClient.post().uri("/account")
                .syncBody(otherValidAccountDto)
                .exchange()
                .expectBody(AccountDto.class)
                .returnResult();

        TransferDto transferDto = new TransferDto();
        transferDto.setAccountFrom(accountDto1.getResponseBody().getId());
        transferDto.setAccountTo(accountDto2.getResponseBody().getId());
        transferDto.setMoney(accountDto1.getResponseBody().getBalance().add(new BigDecimal(1)));

        webClient.post().uri("/transfer")
                .syncBody(transferDto)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Money to transfer exceeds balance of account");
    }

    @Test
    public void transferConflictTest() {
/** TODO **/
    }
}