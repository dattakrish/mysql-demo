package com.kd.personal.mysql;

import com.kd.personal.mysql.service.Network;
import com.kd.personal.mysql.service.ServiceClass;
import com.kd.personal.mysql.service.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceClassTest {
    @InjectMocks
    private ServiceClass service;
    @Mock
    private Network network;
    @Mock
    private UserDao userDao;

    @Test
    void testWithExceptionFromNetwork(){
        when(network.add()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> service.add());
    }

    @Test
    void testWithExceptionFromUserDao(){
        when(userDao.add()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> service.add());
    }

    @Test
    void testWithPositiveResult(){
        when(network.add()).thenReturn("Inside add method of Network");
        when(userDao.add()).thenReturn("Inside add method of UserDao");
        String response = service.add();
        assertTrue("Inside add method of UserDaoInside add method of Network".equals(response));
    }
}
