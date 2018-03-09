<?php

namespace ProductAI\Tests;

use PHPUnit\Framework\TestCase;
use ProductAI\ProductSetApi;
use ProductAI\SearchServiceManagementApi;

class ProductSetApiTest extends TestCase {

    private $productSetApi;

    private $searchServiceApi;

    private $productSetID;

    private $serviceID;

    protected function setUp()
    {
        $this->productSetApi = new ProductSetApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->productSetApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->searchServiceApi = new SearchServiceManagementApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->searchServiceApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->productSetID = null;
        $this->serviceID = null;
    }

    protected function tearDown()
    {
        if (!is_null($this->serviceID)) {
            $this->searchServiceApi->removeService($this->serviceID);
        }

        if (!is_null($this->productSetID)) {
            $this->productSetApi->deleteProductSet($this->productSetID);
        }
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->productSetApi->notExistsMethod();
    }

    public function testCreateProductSet() {
        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(0, count($response['results']));

        $response = $this->productSetApi->createProductSet('name', 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(1, count($response['results']));

        $response = $this->productSetApi->getProductSet($this->productSetID);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame($this->productSetID, $response['id']);
        $name = $response['name'];
        $this->assertSame('name', $name);
        $description = $response['description'];
        $this->assertSame('desc', $description);

        $response = $this->productSetApi->updateProductSet($this->productSetID, 'name1', 'desc1');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProductSet($this->productSetID);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame($this->productSetID, $response['id']);
        $name = $response['name'];
        $this->assertSame('name1', $name);
        $description = $response['description'];
        $this->assertSame('desc1', $description);

        $response = $this->productSetApi->deleteProductSet($this->productSetID);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = null;

        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(0, count($response['results']));
    }

    public function testCreateProductSetService() {
        $response = $this->searchServiceApi->getServices();
        $this->assertSame(200, $this->searchServiceApi->curl_info['http_code']);
        $expected = count($response['results']);

        $response = $this->productSetApi->createProductSet('name', 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $response = $this->productSetApi->createService($this->productSetID, 'productSetCreate', 'material');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->serviceID = $response['id'];

        $response = $this->searchServiceApi->getServices();
        $this->assertSame(200, $this->searchServiceApi->curl_info['http_code']);
        $this->assertSame(1 + $expected, count($response['results']));

        $response = $this->searchServiceApi->getService($this->serviceID);
        $this->assertSame(200, $this->searchServiceApi->curl_info['http_code']);
        $this->assertSame($this->serviceID, $response['id']);

        $response = $this->searchServiceApi->removeService($this->serviceID);
        $this->assertSame(204, $this->searchServiceApi->curl_info['http_code']);
        $this->serviceID = null;

        $response = $this->productSetApi->deleteProductSet($this->productSetID);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = null;

        $response = $this->searchServiceApi->getServices();
        $this->assertSame(200, $this->searchServiceApi->curl_info['http_code']);
        $this->assertSame($expected, count($response['results']));
    }

    public function testProductSetManagement() {
        $response = $this->productSetApi->createProductSet('name', 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $response = $this->productSetApi->getProducts($this->productSetID, ['p1']);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(0, count($response['results']));
        
        $productID = 'p1';
        $price = 1.00;
        $keywords = 'fox dog pig';
        $imageUrl = 'https://a.jpg';
        $metadata = '';
        $tags = 'sofa|table';
        $response = $this->productSetApi->addProduct($this->productSetID, $productID, $price, $keywords, $imageUrl, $metadata, $tags);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProducts($this->productSetID, ['p1']);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(1, count($response['results']));

        $response = $this->productSetApi->deleteProductsByProductIDs($this->productSetID, ['p1']);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProducts($this->productSetID, ['p1']);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(0, count($response['results']));

        $response = $this->productSetApi->deleteProductSet($this->productSetID);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = null;
    }

    public function testProductSetBatchManagement() {
        $response = $this->productSetApi->createProductSet('name', 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $response = $this->productSetApi->addProducts($this->productSetID, './tests/data.json');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProducts($this->productSetID, ['1231', '1234']);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(2, count($response['results']));

        $response = $this->productSetApi->deleteProducts($this->productSetID, './tests/data.json');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProducts($this->productSetID, ['1231', '1234']);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(0, count($response['results']));

        $response = $this->productSetApi->deleteProductSet($this->productSetID);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = null;
    }
}