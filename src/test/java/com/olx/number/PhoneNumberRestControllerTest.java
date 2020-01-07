package com.olx.number;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PhoneNumberRestController.class)
class PhoneNumberRestControllerTest {

	@Test
	public void shouldUploadFile() throws Exception {
		String fileName = "South_African_Mobile_Numbers.csv";
		FileInputStream fi1eInputStream = new FileInputStream(new File(fileName));

		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName,
			"text/plain", fi1eInputStream);

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/phone-number")
			.file("file", mockMultipartFile.getBytes())
			.characterEncoding("UTF-8"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());

	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private PhoneNumberService phoneNumberService;

}