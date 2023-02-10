package ru.netology.javaqamnv.bonus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {
    @ParameterizedTest
    //Не понятно что за переменная массива value, в задании и в лекции нигде не рассказывали как работать с этим кодом
    //В задании сказано, что нужно взять проект с калькултором бонусов, поискал в лекциях,
    //заданиях и листингах кода, но названий как в задании нигде нет (либо я не до конца искал
    //Если я правильно понял, то первое значение в массиве value- это название автотеста,
    //но как это название использовать- нет инфо. Искал в нете так же, но пока не успешно (может мало искал).
    /*  @CsvSource(
      value={
          "'registered user, bonus under limit',100060,true,30",
          "'registered user, bonus over limit',100000060,true,500",
          "'unregistered user, bonus under limit',1000,false,10",
          "'unregistered user, bonus over limit',1000000,false,500",
          "'registered user, bonus under border limit',16700,true,500",
          "'registered user, bonus over border limit',16666,true,499",
          "'registered user, bonus exect border limit',16667,true,500",
          "'unregistered user, bonus under border limit',50100,false,500",
          "'unregistered user, bonus over border limit',49999,false,499",
          "'unregistered user, bonus exect border limit',50000,false,500",
          "'registered user, non amount',0,true,0",
          "'unregistered user, non amount',0,false,0"
      }
     )*/
    @CsvFileSource(resources = "/data.csv")
        //в лекциях везде указывается параметр files = "_", в задании написано resources = "_" (что это означает?)
        // ниже предположил, что т.к. в файле 4 параметра,то тест должен так же принимать 4 параметра (но как его выводить- не понятно
    void calculateBonus(String testName, long amount, boolean registered, long expected) {
        BonusService service = new BonusService();
        long actual = service.calculate(amount, registered);
        assertEquals(expected, actual);
    }

    //закомментировал старый код
    /*@Test
    void shouldCalculateForRegisteredAndUnderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1000;
        boolean registered = true;
        long expected = 30;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000;
        boolean registered = true;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    //новые тесты
    @Test
    void shouldCalculateNoRegisteredAndUnderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1000;
        boolean registered = false;
        long expected = 10;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateNoRegisteredAndOverLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 1_000_000;
        boolean registered = false;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndUnderBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 16_700; //при данной сумме без учета лимита должен быть бонус 501
        boolean registered = true;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 16_666;
        boolean registered = true;
        long expected = 499;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 16_667;
        boolean registered = true;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateNoRegisteredAndUnderBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 50_100; //при данной сумме без учета лимита должен быть бонус 501
        boolean registered = false;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateNoRegisteredAndOverBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 49_999;
        boolean registered = false;
        long expected = 499;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateNoRegisteredAndBorderLimit() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 50_000;
        boolean registered = false;
        long expected = 500;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndAmountIsNull() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 0;
        boolean registered = true;
        long expected = 0;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateNoRegisteredAndAmountIsNull() {
        BonusService service = new BonusService();

        // подготавливаем данные:
        long amount = 0;
        boolean registered = false;
        long expected = 0;

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }
*/
}
