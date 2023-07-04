package com.tdkj.tdcloud.kibContent.controller;

import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.common.security.annotation.Inner;
import com.tdkj.tdcloud.kibContent.entity.EmailSender;
import com.tdkj.tdcloud.kibContent.service.PlantMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@Tag(description  = "mailSender", name =  "邮件发送")
@RequestMapping("/mailSender")
public class MailSenderController {

	@Resource
	private PlantMailService qqMailService;


	@Inner(value = false)
	@Operation(summary = "邮件发送", description = "邮件发送")
	@PostMapping("/getEmailCode")
	public R getEmailCode(EmailSender emailSender){
//		return userService.sendVerificationCode(email);
//		userService.sendSimpleMail(email, "test simple mail2", " 邮件收到了吗");
		return qqMailService.sendSimpleMail(emailSender);
	}
}
