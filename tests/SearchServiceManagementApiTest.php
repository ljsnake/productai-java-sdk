<?php

namespace ProductAI\Tests;

use PHPUnit\Framework\TestCase;
use ProductAI\SearchServiceManagementApi;
use ProductAI\API;

class SearchServiceManagementApiTest extends TestCase {

    private $searchServiceManagementApi;
    private $imageSetApi;
    private $product_search_test_prefix;

    protected function setUp()
    {
        $this->searchServiceManagementApi = new SearchServiceManagementApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->searchServiceManagementApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->imageSetApi = new API(ACCESS_KEY_ID, SECRET_KEY);
        $this->imageSetApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->product_search_test_prefix = defined('PRODUCT_SEARCH_TEST_PREFIX') && PRODUCT_SEARCH_TEST_PREFIX
            ? PRODUCT_SEARCH_TEST_PREFIX : 'php_test_prd_';

        $this->cleanUpServices();
        $this->cleanUpImageSets();
    }

    protected function tearDown()
    {
        $this->cleanUpServices();
        $this->cleanUpImageSets();
    }

    private function cleanUpServices() {
        $services = $this->searchServiceManagementApi->getServices();
        foreach ($services['results'] as $x) {
            if (substr($x['name'], 0, strlen($this->product_search_test_prefix)) === $this->product_search_test_prefix) {
                $this->searchServiceManagementApi->removeService($x['id']);
            }
        }
    }

    private function cleanUpImageSets()
    {
        $image_sets = $this->imageSetApi->getImageSets();
        foreach ($image_sets['results'] as $x) {
            if (substr($x['name'], 0, strlen($this->product_search_test_prefix)) === $this->product_search_test_prefix) {
                $this->imageSetApi->removeImageSet($x['id']);
            }
        }
    }

    private function decorateTestDataName($name) {
        return $this->product_search_test_prefix."$name";
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->searchServiceManagementApi->notExistsMethod();
    }

    public function testCreateService() {
        $name_v1 = $this->decorateTestDataName('n1');
        $response = $this->imageSetApi->createImageSet($name_v1, 'desc1');
        $this->assertSame(200, $this->imageSetApi->curl_info['http_code']);
        $imageSetID = $response['id'];

        $response = $this->searchServiceManagementApi->getServices();
        $this->assertSame(200, $this->searchServiceManagementApi->curl_info['http_code']);
        $expected = count($response['results']);

        $response = $this->imageSetApi->createService($imageSetID, $name_v1, 'material');
        $this->assertSame(200, $this->imageSetApi->curl_info['http_code']);

        $this->serviceID = $response['id'];

        $response = $this->searchServiceManagementApi->getServices();
        $this->assertSame(200, $this->searchServiceManagementApi->curl_info['http_code']);
        $this->assertSame(1 + $expected, count($response['results']));

        $response = $this->searchServiceManagementApi->getService($this->serviceID);
        $this->assertSame(200, $this->searchServiceManagementApi->curl_info['http_code']);
        $this->assertSame($this->serviceID, $response['id']);

        $response = $this->searchServiceManagementApi->removeService($this->serviceID);
        $this->assertSame(204, $this->searchServiceManagementApi->curl_info['http_code']);
        $this->serviceID = null;

        $response = $this->searchServiceManagementApi->getServices();
        $this->assertSame(200, $this->searchServiceManagementApi->curl_info['http_code']);
        $this->assertSame($expected, count($response['results']));
    }
}
