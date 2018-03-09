<?php

namespace ProductAI\Tests;

use PHPUnit\Framework\TestCase;
use ProductAI\SearchServiceManagementApi;
use ProductAI\API;

class SearchServiceManagementApiTest extends TestCase {

    const ACCESS_KEY_ID = '';

    const SECRET_KEY = '';
    
    private $searchServiceManagementApi;

    private $imageSetApi;

    private $imageSetID;

    private $serviceID;

    protected function setUp()
    {
        $this->searchServiceManagementApi = new SearchServiceManagementApi(ACCESS_KEY_ID, SECRET_KEY);
        $this->searchServiceManagementApi->curl_opt[CURLOPT_TIMEOUT] = 120;

        $this->imageSetApi = new API(ACCESS_KEY_ID, SECRET_KEY);
        $this->imageSetApi->curl_opt[CURLOPT_TIMEOUT] = 120;
        $response = $this->imageSetApi->createImageSet('name1', 'desc1');
        $this->imageSetID = $response['id'];
        $this->serviceID = null;
    }

    protected function tearDown()
    {
        if (!is_null($this->serviceID)) {
            $this->searchServiceManagementApi->removeService($this->serviceID);
        }
        $this->imageSetApi->removeImageSet($this->imageSetID);
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->searchServiceManagementApi->notExistsMethod();
    }

    public function testCreateService() {
        $response = $this->searchServiceManagementApi->getServices();
        $this->assertSame(200, $this->searchServiceManagementApi->curl_info['http_code']);
        $expected = count($response['results']);

        $response = $this->imageSetApi->createService($this->imageSetID, 'name', 'material');
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