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

define('ACCESS_KEY_ID_FOR_PRODUCT_SEARCH', '');
define('SECRET_KEY_FOR_PRODUCT_SEARCH', '');
define('SERVICE_ID_FOR_PRODUCT_SEARCH', '');
```

Run tests:

```shell
vendor/bin/phpunit --bootstrap=tests/config.inc.php tests/
```

### Usage

Please read [ProductAI Developers Documentation](https://developers.productai.com/en/)

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

define('ACCESS_KEY_ID_FOR_PRODUCT_SEARCH', '');
define('SECRET_KEY_FOR_PRODUCT_SEARCH', '');
define('SERVICE_ID_FOR_PRODUCT_SEARCH', '');
```

运行测试：

```shell
vendor/bin/phpunit --bootstrap=tests/config.inc.php tests/
```

### 使用

请阅读[ProductAI开发者文档](https://developers.productai.cn/zh/)
