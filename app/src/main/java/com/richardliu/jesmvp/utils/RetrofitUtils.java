package com.richardliu.jesmvp.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitUtils {

    private static final String TAG = RetrofitUtils.class.getSimpleName();
    public static final MediaType MULTIPART = MediaType.parse("multipart/form-data");
    public static final MediaType TEXT = MediaType.parse("text/plain");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FILE = MediaType.parse("application/otcet-stream");

    public static MultipartBody getMultipartBody(List<File> files, String fileParams, Map<String, String> keySet) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (int i = 0; i < files.size(); i++) {
            RequestBody requestBody = RequestBody.create(MULTIPART, files.get(i));
            builder.addFormDataPart(fileParams + i, files.get(i).getName(), requestBody);
        }
        for (String str : keySet.keySet()) {
            builder.addFormDataPart(str, keySet.get(str));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    public static MultipartBody getMultipartBody(Map<String, String> keySet) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String str : keySet.keySet()) {
            builder.addFormDataPart(str, keySet.get(str));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    public static RequestBody fileToRequestBody(File file) {
        return RequestBody.create(FILE, file);
    }

    public static RequestBody stringToTextBody(String value) {
        return RequestBody.create(TEXT, value);
    }

    public static RequestBody stringToJsonBody(String jsonStr) {
        return RequestBody.create(JSON, jsonStr);
    }
}