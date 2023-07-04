package com.tdkj.tdcloud.kibContent.service;

import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.entity.EmailSender;

import javax.mail.MessagingException;

public interface PlantMailService {

	public R sendSimpleMail(EmailSender emailSender);

	//public R userRegister(EmailSender emailSender);
}

