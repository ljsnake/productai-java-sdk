## ProductAI® SDK for PHP

[![Latest Stable Version](https://poser.pugx.org/malong/productai/v/stable)](https://packagist.org/packages/malong/productai)
[![License](https://img.shields.io/github/license/MalongTech/productai-php-sdk.svg)](https://github.com/MalongTech/productai-php-sdk/blob/master/LICENSE)
[![Travis CI Build Status](https://travis-ci.org/MalongTech/productai-php-sdk.svg?branch=master)](https://travis-ci.org/MalongTech/productai-php-sdk)
[![Code Coverage](https://codecov.io/gh/MalongTech/productai-php-sdk/branch/master/graph/badge.svg)](https://codecov.io/gh/MalongTech/productai-php-sdk)

ProductAI® SDKs enable using ProductAI® APIs easily in the programming language of your choice. You can use our PHP SDK to send image queries and maintain your datasets.

### Install

```shell
composer require malong/productai
```

### Testing

Create a file named ```tests/config.inc.php``` and define constants as follows:

```php
<?php

define('ACCESS_KEY_ID', '');
define('SECRET_KEY', '');
define('IMAGE_SET_ID', '');

define('SERVICE_TYPE_SEARCH', '');
define('SERVICE_ID_SEARCH', '');
define('SERVICE_TYPE_CLASSIFY', '');
define('SERVICE_ID_CLASSIFY', '');
define('SERVICE_TYPE_DETECT', '');
define('SERVICE_ID_DETECT', '');
```

Run tests:

```shell
vendor/bin/phpunit --bootstrap=tests/config.inc.php tests/
```

### Usage

#### Initialize

```php
use ProductAI;

$product_ai = new ProductAI\API($access_key_id, $secret_key, $language);
```

```$language```: Default is ```en-US```. The language of return results. NOT all services support this argument.

#### Search image using URL

```php
$result = $product_ai->searchImage($service_type, $service_id, $url, $loc, $tags, $count);
```

```$loc```: Optional, default is the entire image. An area of the image which you want to search. The format is ```[$x, $y, $w, $h]```.

```$tags```: Optional, default is ```[]```. The keywords which you want to search.

```$count```: Optional, default is 20. The number of results that between 0 and 100. Public services do NOT support this argument.

#### Search image using file

```php
$result = $product_ai->searchImage($service_type, $service_id, '@'.$filename, $loc, $tags, $count);
```

#### Search image using raw image

```php
$result = $product_ai->searchImage($service_type, $service_id, file_get_contents($filename), $loc, $tags, $count);
```

#### Search image using upload form

```php
$result = $product_ai->searchImage($service_type, $service_id, '#'.$form_name, $loc, $tags, $count);
```

#### Classify the contents of a image

```php
$result = $product_ai->classifyImage($service_type, $service_id, $image, $loc);
```

The ```$image``` argument accepts the same type as the image search.

#### Detect the objects of a image

```php
$result = $product_ai->detectImage($service_type, $service_id, $image, $loc);
```

The ```$image``` argument accepts the same type as the image search.

#### Upload a image to image set

```php
$result = $product_ai->addImageToSet($set_id, $image_url, 'optional meta', [
    'optional tag 1',
    'optional tag 2',
]);
```

#### Upload images to image set using URLs

```php
$result = $product_ai->addImagesToSet($set_id, [
    [
        $image_url_1,
        'optional meta 1',
        [
            'optional tag 1',
            'optional tag 2',
        ],
    ],
    [
        $image_url_2,
        'optional meta 2',
        [
            'optional tag 3',
            'optional tag 4',
        ],
    ],
]);
```

#### Upload images to image set using a CSV file

```php
$result = $product_ai->addImagesToSet($set_id, $filename);
```

#### Remove images from image set using URLs

```php
$result = $product_ai->removeImagesFromSet($set_id, [
    $image_url_1,
    $image_url_2,
]);
```

#### Remove images from image set using a CSV file

```php
$result = $product_ai->removeImagesFromSet($set_id, $filename);
```

#### Image color analysis

```php
$result = $product_ai->imageColorAnalysis($image, $type, $granularity, $return_type, $loc);
```

```$type```: Analysis type. ```everything``` all colors on the whole image, ```foreground``` foreground colors or ```person_outfit``` the colors of person outfit.

```$granularity```: Analysis granularity. ```major``` major colors, ```detailed``` detailed colors or ```dominant``` the dominant color.

```$return_type```: The return type of colors. ```basic```, ```w3c```, ```ncs``` or ```cncs```.

```$loc```: Optional.

#### General request

```php
$result = $product_ai->generalRequest($service_type, $service_id, $image, $args);
```

The ```$image``` argument accepts the same type as the image search.

```$args```: The arguments which will be appended to request body.

## 中文说明

ProductAI® SDK 提供了 API 请求封装与签名验证功能，用户可以轻松使用 PHP SDK 完成 API 的调用。

### 安装

推荐中国大陆用户使用[国内镜像](https://pkg.phpcomposer.com)。

```shell
composer require malong/productai
```

### 测试

创建文件 ```tests/config.inc.php``` 并定义如下常量：

```php
<?php

define('ACCESS_KEY_ID', '');
define('SECRET_KEY', '');
define('IMAGE_SET_ID', '');

define('SERVICE_TYPE_SEARCH', '');
define('SERVICE_ID_SEARCH', '');
define('SERVICE_TYPE_CLASSIFY', '');
define('SERVICE_ID_CLASSIFY', '');
define('SERVICE_TYPE_DETECT', '');
define('SERVICE_ID_DETECT', '');
```

运行测试：

```shell
vendor/bin/phpunit --bootstrap=tests/config.inc.php tests/
```

### 使用

#### 初始化

```php
use ProductAI;

$product_ai = new ProductAI\API($access_key_id, $secret_key, $language);
```

```$language```: 默认为 ```en-US```。返回结果的语言，不是所有服务都支持此参数。

#### 使用图像 URL 搜索

```php
$result = $product_ai->searchImage($service_type, $service_id, $url, $loc, $tags, $count);
```

```$loc```: 可选，默认为整张图片。用于搜索的图片区域，格式为 ```[$x, $y, $w, $h]```。

```$tags```: 可选，默认为 ```[]```。用于筛选搜索结果的标签。

```$count```: 可选，默认为 20。 设置返回结果的数量，值为 0 到 100，公共服务不支持此参数。

#### 使用图像文件搜索

```php
$result = $product_ai->searchImage($service_type, $service_id, '@'.$filename, $loc, $tags, $count);
```

#### 使用图像字符串搜索

```php
$result = $product_ai->searchImage($service_type, $service_id, file_get_contents($filename), $loc, $tags, $count);
```

#### 使用通过表单上传的图像搜索

```php
$result = $product_ai->searchImage($service_type, $service_id, '#'.$form_name, $loc, $tags, $count);
```

#### 对图像内容分类

```php
$result = $product_ai->classifyImage($service_type, $service_id, $image, $loc);
```

```$image``` 参数接受的类型与图像搜索一致。

#### 识别图像中的物体

```php
$result = $product_ai->detectImage($service_type, $service_id, $image, $loc);
```

```$image``` 参数接受的类型与图像搜索一致。

#### 上传一张图片到数据集

```php
$result = $product_ai->addImageToSet($set_id, $image_url, 'optional meta', [
    'optional tag 1',
    'optional tag 2',
]);
```

#### 上传多张图片到数据集

```php
$result = $product_ai->addImagesToSet($set_id, [
    [
        $image_url_1,
        'optional meta 1',
        [
            'optional tag 1',
            'optional tag 2',
        ],
    ],
    [
        $image_url_2,
        'optional meta 2',
        [
            'optional tag 3',
            'optional tag 4',
        ],
    ],
]);
```

#### 使用 CSV 文件上传多张图片到数据集

```php
$result = $product_ai->addImagesToSet($set_id, $filename);
```

#### 从数据集删除多张图片

```php
$result = $product_ai->removeImagesFromSet($set_id, [
    $image_url_1,
    $image_url_2,
]);
```

#### 使用 CSV 文件从数据集删除多张图片

```php
$result = $product_ai->removeImagesFromSet($set_id, $filename);
```

#### 图片色彩分析

```php
$result = $product_ai->imageColorAnalysis($image, $type, $granularity, $return_type, $loc);
```

```$type```: 分析类型，```everything``` 全图颜色、```foreground``` 前景颜色 或 ```person_outfit``` 人物服饰颜色。

```$granularity```: 分析粒度，```major``` 主要颜色、```detailed``` 所有颜色 或 ```dominant``` 最显著单色。

```$return_type```: 返回颜色类型，```basic```、```w3c```、```ncs``` 或 ```cncs```。

```$loc```: 可选。

#### 通用请求

```php
$result = $product_ai->generalRequest($service_type, $service_id, $image, $args);
```

```$image``` 参数接受的类型与图像搜索一致。

```$args```: 直接附加到 request body 里的参数。
