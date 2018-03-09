<?php

namespace ProductAI\Tests;

use PHPUnit\Framework\TestCase;
use ProductAI\SearchApi;
use UnexpectedValueException;

class SearchApiTest extends TestCase {

    private $searchApi;

    protected function setUp()
    {
        $this->searchApi = new SearchApi(ACCESS_KEY_ID_FOR_PRODUCT_SEARCH, SECRET_KEY_FOR_PRODUCT_SEARCH);
        $this->searchApi->curl_opt[CURLOPT_TIMEOUT] = 120;
    }

    protected function tearDown()
    {
    }

    private function parse($response) {
        foreach ($response['results'] as $x) {
            print("id={$x['product_id']},price={$x['price']},keywords={$x['keywords']}\n");
        }
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->searchApi->notExistsMethod();
    }

    public function testSearchUrl() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg'
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);
    }

    public function testCount() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'count' => 13 ]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);
        $this->assertSame(13, count($response['results']));
    }

    public function testPage() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'count' => 2, 'page' => 1 ]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);
        $this->assertSame(2, count($response['results']));
        
        $expected = $response['results'][1];

        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'count' => 1, 'page' => 2 ]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);
        $this->assertSame(1, count($response['results']));
        $this->assertSame($expected['product_id'], $response['results'][0]['product_id']);
    }    

    public function testKeywords() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'keywords' => '北欧' ]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);

        foreach ($response['results'] as $x) {
            $pos = strpos($x['keywords'], '北欧');
            $this->assertTrue($pos !== false);
        }        
    }

    public function testMinPrice() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'min_price' => 10000.0]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);

        foreach ($response['results'] as $x) {
            $this->assertTrue(floatval($x['price']) > 10000.0);
        }
    }

    public function testMaxPrice() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'max_price' => 10000.0]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);

        foreach ($response['results'] as $x) {
            $this->assertTrue(floatval($x['price']) <= 10000.0);
        }
    }

    public function testMinAndMaxPrice() {
        $response = $this->searchApi->queryProduct(
            SERVICE_ID_FOR_PRODUCT_SEARCH,
            'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
            [ 'max_price' => 15000.0, 'min_price' => 10000.0 ]
        );

        $this->assertSame(200, $this->searchApi->curl_info['http_code']);
        $this->assertArrayHasKey('results', $response);

        foreach ($response['results'] as $x) {
            $this->assertTrue(floatval($x['price']) > 10000.0);
            $this->assertTrue(floatval($x['price']) <= 15000.0);
        }
    }

    public function testInvalidPrice() {
        $throw = false;
        try {
            $response = $this->searchApi->queryProduct(
                SERVICE_ID_FOR_PRODUCT_SEARCH,
                'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
                [ 'max_price' => 10000.0, 'min_price' => 15000.0 ]
            );
        } catch (UnexpectedValueException $e) {
            $throw = true;
        }
        $this->assertTrue($throw);

        $throw = false;
        try {
            $response = $this->searchApi->queryProduct(
                SERVICE_ID_FOR_PRODUCT_SEARCH,
                'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
                [ 'max_price' => -2.0 ]
            );
        } catch (UnexpectedValueException $e) {
            $throw = true;
        }
        $this->assertTrue($throw);

        $throw = false;
        try {
            $response = $this->searchApi->queryProduct(
                SERVICE_ID_FOR_PRODUCT_SEARCH,
                'https://img14.360buyimg.com/n0/jfs/t3865/16/1708535313/231596/db66b437/5896f1f8N9b83c357.jpg',
                [ 'min_price' => -2.0 ]
            );
        } catch (UnexpectedValueException $e) {
            $throw = true;
        }
        $this->assertTrue($throw);
    }
}