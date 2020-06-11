package com.hc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DeptControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void upload1() {
        Resource resource = new FileSystemResource("E:\\图片\\课程LOGO\\android.png");
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("fileName", resource);
        Boolean res = restTemplate.postForObject("/fileUpload", multiValueMap, Boolean.class);
        System.out.println(res);
    }

    @Test
    public void upload2() {
        Resource resource = new FileSystemResource("E:\\图片\\课程LOGO\\android.png");
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("fileName", resource);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(multiValueMap);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange("/fileUpload", HttpMethod.POST, httpEntity, Boolean.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void download() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("/d/download3", HttpMethod.GET, formEntity, byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(new String(response.getBody()));
        }
    }


//    //    请求头信息测试
//    @Test
//    public void getHeader() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token", "xxxxxx");
//        HttpEntity formEntity = new HttpEntity(headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET, formEntity, ActResult.class, urlVariables);
//        Assert.assertEquals(result.getBody().getCode(), 0);
//    }
////    PUT请求测试
//    @Test
//    public void putHeader() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxxx");
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("username","lake");
//        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/putHeader", HttpMethod.PUT,formEntity,ActResult.class);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }
//
//
////    DELETE请求测试
//
//    @Test
//    public void delete() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxx");
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("username","lake");
//        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE,formEntity,ActResult.class,urlVariables);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }
//
////    异步请求
////- 异步调用要使用AsyncRestTemplate。 它是RestTemplate的扩展，提供了异步http请求处理的一种机制，通过返回ListenableFuture对象生成回调机制，以达到异步非阻塞发送http请求
//
//    public String asyncReq(){
//        String url = "http://localhost:8080/jsonAsync";
//        ListenableFuture<ResponseEntity<JSONObject>> future = asyncRestTemplate.getForEntity(url, JSONObject.class);
//        future.addCallback(new SuccessCallback<ResponseEntity<JSONObject>>() {
//            public void onSuccess(ResponseEntity<JSONObject> result) {
//                System.out.println(result.getBody().toJSONString());
//            }
//        }, new FailureCallback() {
//            public void onFailure(Throwable ex) {
//                System.out.println("onFailure:"+ex);
//            }
//        });
//        return "this is async sample";
//    }


}
