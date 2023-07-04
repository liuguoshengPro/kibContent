package com.tdkj.tdcloud.kibContent.config;

import com.tdkj.tdcloud.admin.api.entity.SysUser;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.entity.EmailSender;
import com.tdkj.tdcloud.kibContent.entity.User;
import com.tdkj.tdcloud.kibContent.mapper.ContentDownloadApplyMapper;
import com.tdkj.tdcloud.kibContent.service.PlantMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 实现qq邮箱发送
 */
@Service
public class PlantMailServiceImpl implements PlantMailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private JavaMailSenderImpl sender;

	@Value("${spring.mail.username}")
	private String from;

	private static Properties pro;

	@Resource
	private RedisTemplate redisTemplate;

	@Resource
	private ContentDownloadApplyMapper contentDownloadApplyMapper;


	/**
	 * 发送文本邮件
	 * @param to
	 * @param subject
	 * @param content
	 */

	static {
		pro = System.getProperties(); // 下面各项缺一不可
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.ssl.enable", "true");
		pro.put("mail.smtp.ssl.protocols", "TLSv1.2");
		pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	}

	@Override
	public R sendSimpleMail(EmailSender emailSender) {
		sender.setJavaMailProperties(pro);

		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from); // 发送人
			helper.setTo(emailSender.getToEmail()); // 收件人
			helper.setSubject("【生物多样性科学数据服务平台】"); // 标题
			// TODO: 使用安全的随机数生成器生成验证码
			Random rand = new Random();
			int num = rand.nextInt(900000) + 100000;
			Date currentDate = new Date();
			SimpleDateFormat y = new SimpleDateFormat("yyyy");
			SimpleDateFormat m = new SimpleDateFormat("MM");
			SimpleDateFormat d = new SimpleDateFormat("dd");
			if ("submitApply".equals(emailSender.getEmailType())){
				String textContent = "亲爱的管理员先生, 您好！\n" +
						"我在生物多样性科学数据服务平台中申请了一条下载数据，\n" +
						"请您有空前往服务平台完成审核，谢谢！\n" +
						"此邮件由系统自动发出,请勿直接回复。\n" +
						"如果在使用中遇到问题,请发邮件到 jintao@mail.kib.ac.cn ,我们将尽快回复。\n" +
						"感谢您的访问,祝您使用愉快!\n" +
						"科技信息中心\n" +
						""+y.format(currentDate)+"年"+m.format(currentDate)+"月"+d.format(currentDate)+"日";
//			helper.setText(String.format(textContent, num)); // 内容
				helper.setText(textContent); // 内容
			}
			if ("download".equals(emailSender.getEmailType())){
				String textContent = "亲爱的先生, 您好！\n" +
						"你好在生物多样性科学数据服务平台中下载数据已完成，\n" +
						"请您有空前往服务平台完成后续操作，谢谢！\n" +
						"此邮件由系统自动发出,请勿直接回复。\n" +
						"如果在使用中遇到问题,请发邮件到 jintao@mail.kib.ac.cn ,我们将尽快回复。\n" +
						"感谢您的访问,祝您使用愉快!\n" +
						"科技信息中心\n" +
						""+y.format(currentDate)+"年"+m.format(currentDate)+"月"+d.format(currentDate)+"日";
//			helper.setText(String.format(textContent, num)); // 内容
				helper.setText(textContent); // 内容
			}

			sender.send(message);
			logger.info("邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送邮件时发生异常！", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
			e.printStackTrace();
		}

		return R.ok("发送成功");

	}

}

