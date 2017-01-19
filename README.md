# productai-java-sdk
ProductAI SDK for Java http://productai.cn/

# 安装
mvn test

# 快速入门
## use image URL to search

```java
    Map<String, String> form = new HashMap<String, String>();
    form.put("request_method", "post");                 // 默认 的请求方法 post
    form.put("service_type", "your_service_type");      // require: 你的服务类型 可在 我的服务-测试服务 页面中查看
    form.put("access_key_id", "your_access_key_id");    // require: 你的用户配置 access_key_id
    form.put("secret_key", "your_secret_key");          // require: 你的用户配置 secret_key
    form.put("service_id", "your_service_id");          // require: 你的服务ID
    form.put("image_url", "your_image_url");            // require: 你的测试图片链接地址
    //form.put("loc", "0-0-1-1");                         // option: (选填)图片标框的位置信息
    
    String response_content = submit_form_to_search(form);
    System.out.println(response_content);
```


## upload image to search

```java
        Map<String, String> form = new HashMap<String, String>();
        Map<String, String> files = new HashMap<String, String>();
        form.put("request_method", "post");                 // 默认 的请求方法 post
        form.put("service_type", "your_service_type");      // require: 你的服务类型 可在 我的服务-测试服务 页面中查看
        form.put("access_key_id", "your_access_key_id");    // require: 你的用户配置 access_key_id
        form.put("secret_key", "your_secret_key");          // require: 你的用户配置 secret_key
        form.put("service_id", "your_service_id");          // require: 你的服务ID
        files.put("search", "some_paths/your_image.jpg");   // require: 你的本地图片的路径
        //form.put("loc", "0-0-1-1");                       // option: (选填)图片标框的位置信息
        //form.put("count", "1");                           // option: (选填) 设置总数限制
        String response_content = submit_file_to_search(form, files);
        System.out.println(response_content);
```

## add a new image to image_set for search
```java
        Map<String, String> form = new HashMap<String, String>();
        form.put("request_method", "post");                 // 默认 的请求方法 post
        form.put("access_key_id", "your_access_key_id");    // require: 你的用户配置 access_key_id
        form.put("secret_key", "your_secret_key");          // require: 你的用户配置 secret_key
        form.put("pai_user_id", "your_user_id");            // require: 你的用户ID
        form.put("image_set_id", "your_image_set_id");      // require: 你的数据集ID
        form.put("image_url", "your_image_url");            // require: 你的测试图片链接
        //form.put("meta", "your_message");                 // option: (选填)附加信息

        String response_content = add_image_to_image_set(form);
        System.out.println(response_content);
```

## delete some images
```java
        Map<String, String> form = new HashMap<String, String>();
        form.put("access_key_id", "your_access_key_id");        // require: 你的用户配置 access_key_id
        form.put("secret_key", "your_secret_key");              // require: 你的用户配置 secret_key
        form.put("pai_user_id", "your_user_id");                // require: 你的用户ID
        form.put("image_set_id", "your_image_set_id");          // require: 你的数据集ID
        form.put("file_path", "some_paths/your_csv_file.csv");  // require: 你的CSV文件的路径
        
        String response_content = delete_image_by_file(form);
        System.out.println(response_content);
```



## add some images

```java
        Map<String, String> form = new HashMap<String, String>();
        form.put("access_key_id", "your_access_key_id");        // require: 你的用户配置 access_key_id
        form.put("secret_key", "your_secret_key");              // require: 你的用户配置 secret_key
        form.put("pai_user_id", "your_user_id");                // require: 你的用户ID
        form.put("image_set_id", "your_image_set_id");          // require: 你的数据集ID
        form.put("file_path", "some_paths/your_csv_file.csv");  // require: 你的CSV文件的路径
        
        String response_content = add_image_by_file(form);
        System.out.println(response_content);
```

