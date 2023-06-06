package com.tdkj.tdcloud.kibContent.config;

import com.tdkj.tdcloud.common.core.constant.SecurityConstants;
import com.tdkj.tdcloud.common.security.annotation.Inner;
import com.tdkj.tdcloud.kibContent.api.feign.RemoteDeleteFileService;
import com.tdkj.tdcloud.kibContent.entity.ContentDownloadApply;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import com.tdkj.tdcloud.kibContent.mapper.ContentDownloadApplyMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class TimingDeleteFileTask {

	@Resource
	private RemoteDeleteFileService remoteDeleteFileService;
	@Resource
	private ContentDownloadApplyMapper contentDownloadApplyMapper;

//	@Scheduled(cron = "0 0/1 * * * ?")按分钟
	@Scheduled(cron = "0 0 0 * * ?")
	public void run() {
		// 定时任务要执行的逻辑代码
		List<ContentDownloadApply> contentDownloadApplyList = contentDownloadApplyMapper.selectContentDownload();
		if (contentDownloadApplyList.size()>0){
			contentDownloadApplyList.stream().forEach(contentDownloadApply -> {
				if (contentDownloadApply.getIsCollect()==0 && "1".equals(contentDownloadApply.getAuditStatus())){
					if (contentDownloadApply.getAuditTime()!=null){
						Long days = getDays(contentDownloadApply.getAuditTime());
						if (days >= 3){
							ContentLibFile contentLibFile = new ContentLibFile();
							contentLibFile.setBucketName("kib-data");
							contentLibFile.setFileName(contentDownloadApply.getDownloadUrl());
							remoteDeleteFileService.deleteFile(contentLibFile);
							contentDownloadApplyMapper.updateDownloadApplyUrlExpire("",Long.valueOf(contentDownloadApply.getId()));
							System.out.println("-----------定时任务"+getDays(contentDownloadApply.getAuditTime()));
						}
					}
				}
			});
		}
		//remoteDeleteFileService.deleteFile();

	}

	public static Long getDays(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		long oldTime=cal.getTimeInMillis();
		long nowTime=System.currentTimeMillis();
		long days=(nowTime-oldTime)/(1000*60*60*24);//天数
//		long hours=((nowTime-oldTime)%(1000*60*60*24))/(1000*60*60);//小时数
//		long minutes=(((nowTime-oldTime)%(1000*60*60*24))%(1000*60*60))/(1000*60);//分钟数
//		long seconds=((((nowTime-oldTime)%(1000*60*60*24))%(1000*60*60))%(1000*60))/1000;//秒数
		return days;
	}
}
