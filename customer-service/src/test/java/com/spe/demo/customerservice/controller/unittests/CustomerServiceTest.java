package com.spe.demo.customerservice.controller.unittests;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spe.demo.customerservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerService.class, secure = false)
public class CustomerServiceTest {
	//@Mock
	//CustomerRep customeServiceMock;
	
	//@InjectMock
}
