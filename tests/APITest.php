<?php

namespace ProductAI\Tests;

use DateTime;
use PHPUnit\Framework\TestCase;
use ProductAI\API;
use ProductAI\CURLException;

class APITest extends TestCase
{
    private $product_ai;

    protected function setUp()
    {
        $this->product_ai = new API(ACCESS_KEY_ID, SECRET_KEY);
        $this->product_ai->curl_opt[CURLOPT_TIMEOUT] = 120;
    }

    public function testBadMethodCall()
    {
        $this->expectException('BadMethodCallException');
        $this->product_ai->notExistsMethod();
    }

    public function testSearchImageByURL()
    {
        $result = $this->product_ai->searchImage(SERVICE_TYPE_SEARCH, SERVICE_ID_SEARCH,
            'http://www.sinaimg.cn/dy/slidenews/24_img/2013_13/40223_662671_794351.jpg', [], [
                '长靴',
                '针织衫',
                '短靴',
                '牛仔裤',
                '打底裤',
            ], 20, 0.8);
        $this->assertArrayHasKey('results', $result);
    }

    public function testSearchImageByFile()
    {
        $result = $this->product_ai->searchImage(SERVICE_TYPE_SEARCH, SERVICE_ID_SEARCH, '@'.__DIR__.'/test.jpg', [
            0.1, 0.2, 0.8, 0.6
        ], [], 10, 0);
        $this->assertArrayHasKey('results', $result);
    }

    public function testSearchImageByString()
    {
        $result = $this->product_ai->searchImage(SERVICE_TYPE_SEARCH, SERVICE_ID_SEARCH, file_get_contents(__DIR__.'/test.jpg'));
        $this->assertArrayHasKey('results', $result);
    }

    public function testSearchImageByForm()
    {
        $this->expectException('OutOfBoundsException');
        $this->product_ai->searchImage(SERVICE_TYPE_SEARCH, SERVICE_ID_SEARCH, '#test');
    }

    public function testClassifyImage()
    {
        $result = $this->product_ai->classifyImage(SERVICE_TYPE_CLASSIFY, SERVICE_ID_CLASSIFY, 'http://www.sinaimg.cn/dy/slidenews/24_img/2013_13/40223_662671_794351.jpg');
        $this->assertSame(0, $result['is_err']);
    }

    public function testDetectImage()
    {
        $result = $this->product_ai->detectImage(SERVICE_TYPE_DETECT, SERVICE_ID_DETECT, 'http://www.wed114.cn/jiehun/uploads/allimg/c130401/1364P42Q140-49539.jpg');
        $this->assertArrayNotHasKey('is_err', $result);
    }

    public function testAddImageToSet()
    {
        $result = $this->product_ai->addImageToSet(IMAGE_SET_ID, 'http://www.wed114.cn/jiehun/uploads/allimg/c130401/1364P42Q140-49539.jpg', 'test image', [
            '百褶裙',
            '针织衫',
            '紧身半裙',
            '短夹克',
            '直筒裙',
        ]);
        $this->assertArrayNotHasKey('error_code', $result);
    }

    public function testAddImagesToSet()
    {
        $result = $this->product_ai->addImagesToSet(IMAGE_SET_ID, [
            [
                'url' => 'http://images.yoka.com/pic/fashion/roadshow/2010/U162P1T117D149859F2577DT20100827201536.JPG',
                'meta' => 'test image 1',
                'tag' => [
                    '无肩带裙',
                    'A字裙',
                    '连体裤',
                    '紧身连衣裙',
                    '女式衬衫',
                ],
            ],
            [
                'url' => 'http://www.people.com.cn/mediafile/pic/20130924/54/1190700219959357062.jpg',
                'meta' => 'test image 2',
                'tags' => [
                    '短靴',
                    '吊带裙',
                    '时尚平底鞋',
                    '帆布鞋',
                    '短夹克',
                ]
            ],
        ]);
        $this->assertArrayNotHasKey('error_code', $result);
    }

    public function testRemoveImagesFromSet()
    {
        $result = $this->product_ai->removeImagesFromSet(IMAGE_SET_ID, [
            'http://images.yoka.com/pic/fashion/roadshow/2010/U162P1T117D149859F2577DT20100827201536.JPG',
            'http://www.people.com.cn/mediafile/pic/20130924/54/1190700219959357062.jpg',
        ]);
        $this->assertArrayNotHasKey('error_code', $result);
    }

    public function testImageColorAnalysis()
    {
        $result = $this->product_ai->imageColorAnalysis(file_get_contents(__DIR__.'/test.jpg'), 'person_outfit', 'major', 'basic');
        $this->assertArrayHasKey('results', $result);
    }

    public function testCRUDImageSet()
    {
        $set_id = $this->product_ai->createImageSet('name1', 'desc1')['id'];
        $image_set = $this->product_ai->getImageSet($set_id);
        $this->assertSame($set_id, $image_set['id']);
        $this->assertSame('name1', $image_set['name']);
        $this->assertSame('desc1', $image_set['description']);

        $this->product_ai->updateImageSetNameDesc($set_id, 'name2', 'desc2');
        $image_set = $this->product_ai->getImageSet($set_id);
        $this->assertSame($set_id, $image_set['id']);
        $this->assertSame('name2', $image_set['name']);
        $this->assertSame('desc2', $image_set['description']);

        $this->product_ai->removeImageSet($set_id);
        $this->expectException(CURLException::class);
        $this->product_ai->getImageSet($set_id);
        $this->assertSame(404, $this->product_ai->curl_info['http_code']);
    }

    public function testCRUDService()
    {
        $service_id = $this->product_ai->createService(IMAGE_SET_ID, 'name1', SERVICE_SCENARIO)['id'];
        $service = $this->product_ai->getService($service_id);
        $this->assertSame($service_id, $service['id']);
        $this->assertSame($service['image_set_id'], IMAGE_SET_ID);
        $this->assertSame($service['scenario'], SERVICE_SCENARIO);
        $this->assertSame('name1', $service['name']);

        $this->product_ai->updateServiceName($service_id, 'name2');
        $service = $this->product_ai->getService($service_id);
        $this->assertSame($service_id, $service['id']);
        $this->assertSame($service['image_set_id'], IMAGE_SET_ID);
        $this->assertSame($service['scenario'], SERVICE_SCENARIO);
        $this->assertSame('name2', $service['name']);

        $this->product_ai->removeService($service_id);
        $this->expectException(CURLException::class);
        try {
            $this->product_ai->getService($service_id);
        } catch (CURLException $e) {
            $this->assertSame(404, $this->product_ai->curl_info['http_code']);

            $result = json_decode($this->product_ai->curl_output, true);
            $this->assertSame(2002, $result['error_code']);

            throw $e;
        }
    }

    public function testAddAndGetBatchTask()
    {
        $services = $this->product_ai->listBatchServices()['data'];

        $task = $this->product_ai->prepareBatchTask($services[0], [
            'http://wx1.sinaimg.cn/large/63136032ly1fmuhazpck1j20fe0a8thm.jpg',
            'http://wx4.sinaimg.cn/large/6587622bly1fmv0usxdxuj20kk0cj76f.jpg',
        ])['data'];
        $this->assertSame('RECEIVED', $task['state']);

        $task = $this->product_ai->applyBatchTask($task['task_id'])['data'];
        $this->assertContains($task['state'], ['PENDING', 'STARTED']);
    }

    public function testRevokeBatchTask()
    {
        $tasks = $this->product_ai->listBatchTasks(
            new DateTime('2017-01-01T00:00Z'),
            new DateTime('2018-01-01T00:00Z')
        )['data'];
        $task = reset($tasks);
        $this->assertSame('SUCCESS', $task['state']);

        $this->expectException(CURLException::class);
        try {
            $this->product_ai->revokeBatchTask($task['task_id']);
        } catch (CURLException $e) {
            $this->assertSame(400, $this->product_ai->curl_info['http_code']);

            $result = json_decode($this->product_ai->curl_output, true)['data'];
            $this->assertSame(400012, $result['error_code']);

            throw $e;
        }
    }

    public function testRetryBatchTask()
    {
        $tasks = $this->product_ai->listBatchTasks(
            new DateTime('2017-01-01T00:00Z'),
            new DateTime('2018-01-01T00:00Z')
        )['data'];
        $task = reset($tasks);
        $this->assertSame('SUCCESS', $task['state']);

        $this->expectException(CURLException::class);
        try {
            $this->product_ai->retryBatchTask($task['task_id']);
        } catch (CURLException $e) {
            $this->assertSame(400, $this->product_ai->curl_info['http_code']);

            $result = json_decode($this->product_ai->curl_output, true)['data'];
            $this->assertSame(400014, $result['error_code']);

            throw $e;
        }
    }
}
