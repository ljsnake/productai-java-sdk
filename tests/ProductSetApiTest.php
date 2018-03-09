<?php

namespace ProductAI\Tests;

use PHPUnit\Framework\TestCase;
use ProductAI\ProductSetApi;
use ProductAI\SearchServiceManagementApi;

class ProductSetApiTest extends TestCase {

    private $productSetApi;
    private $searchServiceApi;
    private $product_search_test_prefix;

    protected function setUp()
    {
        $this->productSetApi = new ProductSetApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->productSetApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->searchServiceApi = new SearchServiceManagementApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->searchServiceApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->product_search_test_prefix = defined('PRODUCT_SEARCH_TEST_PREFIX') && PRODUCT_SEARCH_TEST_PREFIX
            ? PRODUCT_SEARCH_TEST_PREFIX : 'php_test_prd_';

        $this->cleanUpServices();
        $this->cleanUpProductSets();
    }

    protected function tearDown()
    {
        $this->cleanUpServices();
        $this->cleanUpProductSets();
    }

    private function cleanUpServices() {
        $services = $this->searchServiceApi->getServices();
        foreach ($services['results'] as $x) {
            if (substr($x['name'], 0, strlen($this->product_search_test_prefix)) === $this->product_search_test_prefix) {
                $this->searchServiceApi->removeService($x['id']);
            }
        }
    }

    private function cleanUpProductSets()
    {
        $product_sets = $this->productSetApi->getProductSets();
        foreach ($product_sets['results'] as $x) {
            if (substr($x['name'], 0, strlen($this->product_search_test_prefix)) === $this->product_search_test_prefix) {
                $this->productSetApi->deleteProductSet($x['id']);
            }
        }
    }

    private function decorateTestDataName($name) {
        return $this->product_search_test_prefix."$name";
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->productSetApi->notExistsMethod();
    }

    public function testCreateProductSet() {
        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $expected = count($response['results']);

        $name_v1 = $this->decorateTestDataName('n1');
        $response = $this->productSetApi->createProductSet($name_v1, 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame(1 + $expected, count($response['results']));

        $response = $this->productSetApi->getProductSet($this->productSetID);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame($this->productSetID, $response['id']);
        $name = $response['name'];
        $this->assertSame($name_v1, $name);
        $description = $response['description'];
        $this->assertSame('desc', $description);

        $name_v2 = $this->decorateTestDataName('n2');
        $response = $this->productSetApi->updateProductSet($this->productSetID, $name_v2, 'desc1');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);

        $response = $this->productSetApi->getProductSet($this->productSetID);
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame($this->productSetID, $response['id']);
        $name = $response['name'];
        $this->assertSame($name_v2, $name);
        $description = $response['description'];
        $this->assertSame('desc1', $description);

        $response = $this->productSetApi->deleteProductSet($this->productSetID);
        $this->assertSame(204, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = null;

        $response = $this->productSetApi->getProductSets();
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->assertSame($expected, count($response['results']));
    }

    public function testCreateProductSetService() {
        $response = $this->searchServiceApi->getServices();
        $this->assertSame(200, $this->searchServiceApi->curl_info['http_code']);
        $expected = count($response['results']);

        $name_v1 = $this->decorateTestDataName('n1');
        $response = $this->productSetApi->createProductSet($name_v1, 'desc');
        $this->assertSame(200, $this->productSetApi->curl_info['http_code']);
        $this->productSetID = $response['id'];

        $serviceName = $this->decorateTestDataName('n1');
        $response = $this->productSetApi->createService($this->productSetID, $serviceName, 'material');
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
        $name_v1 = $this->decorateTestDataName('n1');
        $response = $this->productSetApi->createProductSet($name_v1, 'desc');
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
        $name_v1 = $this->decorateTestDataName('n1');
        $response = $this->productSetApi->createProductSet($name_v1, 'desc');
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
