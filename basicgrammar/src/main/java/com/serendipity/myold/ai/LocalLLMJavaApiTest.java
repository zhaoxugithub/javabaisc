package com.serendipity.myold.ai;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LocalLLMJavaApiTest {

    public static void main(String[] args) {
        LocalLLMJavaApiTest test = new LocalLLMJavaApiTest();
        test.onceChat();
    }


    private void onceChat() {
        try {
            // 设置请求的URL
            URL url = new URL("http://192.168.150.129:11434/api/chat");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");

            // 创建JSON格式的请求体
            String jsonInputString = "{" + "\"model\": \"deepseek-r1:1.5b\", " + "\"messages\": [\n" + "    { \"role\": \"user\", \"content\": \"天空为什么是蓝色的?\" }\n" + "  ], " + "\"stream\": false " + // 是否流式返回结果，默认为true,流式返回结果
                    "}";

            // 将请求体写入输出流
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 读取响应
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 创建一个ObjectMapper实例
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder fullResponse = new StringBuilder();

            // 处理响应
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    System.out.println("responseLine: " + responseLine);

                    // 解析每一行JSON响应
                    JsonNode jsonNode = objectMapper.readTree(responseLine);
                    // 获取"message"中的"content"字段的值并拼接
                    if (jsonNode.has("message") && jsonNode.get("message").has("content")) {
                        fullResponse.append(jsonNode.get("message").get("content").asText());
                    }
                    // 检查"done"字段的值
                    if (jsonNode.has("done") && jsonNode.get("done").asBoolean()) {
                        break; // 如果"done"为true，结束循环
                    }
                }
            }

            // 输出拼接后的完整响应
            System.out.println("Full Response: " + fullResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
