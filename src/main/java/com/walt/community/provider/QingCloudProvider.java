package com.walt.community.provider;

import com.qingstor.sdk.config.EnvContext;
import com.qingstor.sdk.constants.QSConstant;
import com.qingstor.sdk.exception.QSException;
import com.qingstor.sdk.request.RequestHandler;
import com.qingstor.sdk.service.Bucket;
import com.qingstor.sdk.utils.QSSignatureUtil;
import com.walt.community.exception.CustomizeErrorCode;
import com.walt.community.exception.CustomizeException;
import com.walt.community.exception.ICustomizeErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: walt1012
 * @date: 2020/8/22
 */
@Service
public class QingCloudProvider {

    @Value("${qstore.access_key_id}")
    private String accessKeyId;

    @Value("${qstore.secret_access_key}")
    private String secretAccessKey;

    @Value("${qstore.zone-name}")
    private String zoneName;

    @Value("${qstore.bucket-name}")
    private String bucketName;

    /**
     * 上传文件
     */
    public String upload(String objectKey, InputStream inputStream) throws CustomizeException {
        String url = null;
        EnvContext env = new EnvContext(accessKeyId, secretAccessKey);
        Bucket bucket = new Bucket(env, zoneName, bucketName);

        //最终上传到对象存储的文件显示的文件名称
        Bucket.PutObjectInput input = new Bucket.PutObjectInput();
        input.setBodyInputStream(inputStream);
        RequestHandler reqHandler;
        try {
            reqHandler = bucket.putObjectAsyncRequest(objectKey, input,
                    output -> {
                        if (output.getStatueCode() != 201) {
                            System.out.println("Message = " + output.getMessage());
                            System.out.println("RequestId = " + output.getRequestId());
                            System.out.println("Code = " + output.getCode());
                            System.out.println("StatueCode = " + output.getStatueCode());
                            System.out.println("Url = " + output.getUrl());
                        }
                        System.exit(0);
                    });
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
            String gmtTime = QSSignatureUtil.formatGmtDate(instance.getTime());

            //验证需要这个Date header
            reqHandler.getBuilder().setHeader(QSConstant.HEADER_PARAM_KEY_DATE, gmtTime);
            String strToSignature = null;
            strToSignature = reqHandler.getStringToSignature();
            String serverAuthorization = QSSignatureUtil.generateSignature(env.getSecretAccessKey(),
                    strToSignature);
            reqHandler.setSignature(env.getAccessKeyId(), serverAuthorization);
            //异步发送
            reqHandler.sendAsync();
            long expiresTime = new Date().getTime() / 1000 + 60 * 60 * 24; // Expired in 600 seconds(10 minutes).
            url = bucket.GetObjectSignatureUrl(objectKey, expiresTime);
        } catch (QSException e) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }
        return url;
    }

}