<?php

namespace ProductAI;

use BadMethodCallException;
use CURLFile;
use DateTime;

class API extends Base
{
    public function __call($name, $args)
    {
        if (method_exists($this, $name)) {
            $this->initialize();

            return call_user_func_array([$this, $name], $args);
        } else {
            throw new BadMethodCallException('Call to undefined method '.get_class($this)."::{$name}()", 1);
        }
    }

    protected function searchImage($service_type, $service_id, $image, $loc = [], $tags = [],
                                   $count = 20, $page = 1)
    {
        $this->loadImage($image);

        if ($loc) {
            $this->body['loc'] = is_array($loc) ? implode('-', $loc) : $loc;
        }

        if ($tags) {
            if (is_array($tags)) {
                $this->body['tags'] = is_array(reset($tags)) ? json_encode($tags) : implode('|', $tags);
            } else {
                $this->body['tags'] = $tags;
            }
        }

        if ($count) {
            $this->body['count'] = intval($count);
        }

        if ($page) {
            $this->body['page'] = intval($page) - 1;
        }

        return $this->curl($service_type, $service_id);
    }

    protected function classifyImage($service_type, $service_id, $image, $loc = [])
    {
        return $this->searchImage($service_type, $service_id, $image, $loc);
    }

    protected function detectImage($service_type, $service_id, $image, $loc = [])
    {
        return $this->searchImage($service_type, $service_id, $image, $loc);
    }

    protected function getImageSet($set_id)
    {
        $this->method = 'GET';

        return $this->curl('image_sets', "_0000014/$set_id");
    }

    protected function createImageSet($name, $description = '')
    {
        $this->body['name'] = $name;
        $this->body['description'] = $description;

        return $this->curl('image_sets', '_0000014', true);
    }

    protected function removeImageSet($set_id)
    {
        $this->method = 'DELETE';

        return $this->curl('image_sets', "_0000014/$set_id");
    }

    protected function updateImageSetNameDesc($set_id, $name, $description='')
    {
        $this->method = 'PUT';

        $this->body['name'] = $name;
        $this->body['description'] = $description;

        return $this->curl('image_sets', "_0000014/$set_id", true);
    }

    protected function getService($service_id)
    {
        $this->method = 'GET';

        return $this->curl('customer_services', "_0000172/$service_id");
    }

    protected function createService($image_set_id, $name, $scenario)
    {
        $this->body['name'] = $name;
        $this->body['scenario'] = $scenario;

        return $this->curl('image_sets', "_0000014/$image_set_id/services", true);
    }

    protected function removeService($service_id)
    {
        $this->method = 'DELETE';

        return $this->curl('customer_services', "_0000172/$service_id");
    }

    protected function updateServiceName($service_id, $name)
    {
        $this->method = 'PUT';

        $this->body['name'] = $name;

        return $this->curl('customer_services', "_0000172/$service_id", true);
    }

    protected function addImageToSet($set_id, $image_url, $meta = '', $tags = [])
    {
        $this->body['image_url'] = $image_url;

        if ($meta) {
            $this->body['meta'] = $meta;
        }

        if ($tags) {
            $this->body['tags'] = implode('|', $tags);
        }

        return $this->curl('image_sets', "_0000014/$set_id");
    }

    protected function addImagesToSet($set_id, $image_urls)
    {
        if (is_array($image_urls)) {
            $image_urls = $this->convertArrayToCSV($image_urls);
        }

        $this->body['urls_to_add'] = new CURLFile($image_urls);

        return $this->curl('image_sets', "_0000014/$set_id");
    }

    protected function removeImagesFromSet($set_id, $image_urls)
    {
        if (is_array($image_urls)) {
            $image_urls = $this->convertArrayToCSV($image_urls);
        }

        $this->body['urls_to_delete'] = new CURLFile($image_urls);

        return $this->curl('image_sets', "_0000014/$set_id");
    }

    protected function imageColorAnalysis($image, $type, $granularity, $return_type, $loc = [])
    {
        $this->loadImage($image);

        switch ($type) {
            case 'everything':
                $service_type = 'image_analysis_everything';
                $service_id = '_0000072';

                break;

            case 'foreground':
                $service_type = 'image_analysis_foreground';
                $service_id = '_0000073';

                break;

            case 'person_outfit':
                $service_type = 'image_analysis_person_outfit';
                $service_id = '_0000074';

                break;

            default:
                throw new BadMethodCallException('Bad type.', 1);
        }

        if (!in_array($granularity, array('major', 'detailed', 'dominant', 'exhaustive'))) {
            throw new BadMethodCallException('Bad granularity.', 1);
        }
        $this->body['granularity'] = $granularity;

        if (!in_array($return_type, array('basic', 'w3c', 'ncs', 'cncs'))) {
            throw new BadMethodCallException('Bad return type.', 1);
        }
        $this->body['return_type'] = $return_type;

        if ($loc) {
            $this->body['loc'] = is_array($loc) ? implode('-', $loc) : $loc;
        }

        return $this->curl($service_type, $service_id);
    }

    protected function generalRequest($service_type, $service_id, $image = null, $args = [], $json = false)
    {
        if ($image !== null) {
            $this->loadImage($image);
        }

        if ($args) {
            $this->body += $args;
        }

        return $this->curl($service_type, $service_id, $json);
    }

    protected function prepareBatchTask($service_id, $image_urls)
    {
        if (is_array($image_urls)) {
            $image_urls = $this->convertArrayToCSV($image_urls);
        }

        $this->body['service_id'] = $service_id;
        $this->body['urls'] = new CURLFile($image_urls);

        return $this->curl('batch', '_1000001/task/prepare');
    }

    protected function applyBatchTask($task_id)
    {
        $this->body['task_id'] = $task_id;

        return $this->curl('batch', '_1000001/task/apply');
    }

    protected function getBatchTaskInfo($task_id)
    {
        $this->method = 'GET';

        return $this->curl('batch', "_1000001/task/info/$task_id");
    }

    protected function revokeBatchTask($task_id)
    {
        return $this->curl('batch', "_1000001/task/revoke/$task_id");
    }

    protected function retryBatchTask($task_id)
    {
        return $this->curl('batch', "_1000001/task/retry/$task_id");
    }

    protected function listBatchTasks($start=null, $end=null)
    {
        $this->method = 'GET';

        if ($start !== null) {
            if ($start instanceof DateTime) $start = $start->format(DateTime::ATOM);

            $this->body['start'] = $start;
        }

        if ($end !== null) {
            if ($end instanceof DateTime) $end = $end->format(DateTime::ATOM);

            $this->body['end'] = $end;
        }

        return $this->curl('batch', '_1000001/tasks');
    }

    protected function listBatchServices()
    {
        $this->method = 'GET';

        return $this->curl('batch', '_1000001/services');
    }
}
