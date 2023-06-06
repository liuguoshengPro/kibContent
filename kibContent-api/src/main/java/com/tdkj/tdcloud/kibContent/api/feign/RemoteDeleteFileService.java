package com.tdkj.tdcloud.kibContent.api.feign;

import com.tdkj.tdcloud.common.core.constant.SecurityConstants;
import com.tdkj.tdcloud.common.core.constant.ServiceNameConstants;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteDeleteFileService",value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteDeleteFileService {



	@DeleteMapping("/sys-file/deleteFileJob")
	public R deleteFile(@RequestBody ContentLibFile contentLibFile);

}
