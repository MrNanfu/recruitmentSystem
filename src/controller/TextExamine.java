package controller;


import java.util.*;

import com.sun.org.apache.xerces.internal.xs.StringList;
import face.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 人脸检测与属性分析
 */
public class TextExamine{

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String detect(String text) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
        String rs = "";
        try {
            //创建一个字节数组
//            byte[] content = new byte[(int)(examine.length())];
//
//            String encodedString = Base64Util.encode(content);
//            Map<String, Object> map = new HashMap<>();
//            map.put("content", examine);
            String form = "content=" + text;
//            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.9d8c23b1f7d01d1b70390a42dfaba06f.2592000.1570120964.282335-17172118";

            String result = HttpUtil.post(url, accessToken, "application/x-www-form-urlencoded",form);
            JSONObject jsonObject = new JSONObject (result);
            JSONObject jsonResult = jsonObject.getJSONObject ("result");
            JSONArray jsonReview = jsonResult.getJSONArray ("review");
            JSONArray jsonReject = jsonResult.getJSONArray ("reject");
            List<String> review = new ArrayList<> ();
            List<String> reject = new ArrayList<> ();
            for(int i = 0;i < jsonReview.length ();i++){
                JSONObject temp = jsonReview.getJSONObject (i);
                int labelNum = temp.getInt ("label");
                String label = null;
                switch(labelNum){
                    case 1 : label = "暴恐违禁";break;
                    case 2 : label = "文本色情";break;
                    case 3 : label = "政治敏感";break;
                    case 4 : label = "恶意推广";break;
                    case 5 : label = "低俗谩骂";break;

                }
                String info = label + "指数" + String.valueOf (temp.getDouble ("score"));
                review.add (info);
            }
            for(int i = 0;i < jsonReject.length ();i++){
                JSONObject temp = jsonReject.getJSONObject (i);
                int labelNum = temp.getInt ("label");
                String label = null;
                switch(labelNum){
                    case 1 : label = "暴恐违禁";break;
                    case 2 : label = "文本色情";break;
                    case 3 : label = "政治敏感";break;
                    case 4 : label = "恶意推广";break;
                    case 5 : label = "低俗谩骂";break;

                }
                String info = label + "指数" + String.valueOf (temp.getDouble ("score"));
                reject.add (info);
            }
            for(int i = 0;i < review.size ();i++){
                rs += "建议审核    "+ review.get (i) + ":";
            }
            for(int i = 0;i < reject.size ();i++){
                rs += "建议删除    " + reject.get (i) + ":";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}