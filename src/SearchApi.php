<?php

namespace ProductAI;

use BadMethodCallException;
use UnexpectedValueException;

class SearchApi extends Base
{
    const PRODUCT_SEARCH_SERVICE_TYPE = 'product_search';

    const IMAGE_SEARCH_SERVICE_TYPE = 'search';

    public function __call($name, $args)
    {
        if (method_exists($this, $name)) {
            $this->initialize();
            return call_user_func_array([$this, $name], $args);
        }

        throw new BadMethodCallException('Call to undefined method '.get_class($this)."::{$name}()", 1);
    }

    protected function queryImage($serviceID, $image, $options=[]) {
        $this->loadImage($image);

        if (array_key_exists('loc', $options)) {
            $loc = $options['loc'];
            $this->body['loc'] = is_array($loc) ? implode('-', $loc) : $loc;
        }

        if (array_key_exists('tags', $options)) {
            $tags = $options['tags'];
            if (is_array($tags)) {
                $tags = is_array(reset($tags)) ? json_encode($tags) : implode('|', $tags);
            }
            $this->body['tags'] = $tags;
        }

        if (array_key_exists('count', $options)) {
            $this->body['count'] = intval($options['count']);
        }

        if (array_key_exists('page', $options)) {
            $this->body['page'] = intval($options['page']) - 1;
        }

        return $this->curl(static::IMAGE_SEARCH_SERVICE_TYPE, $serviceID);
    }

    protected function queryProduct($serviceID, $image, $options=[]) {
        $this->loadImage($image);

        if (array_key_exists('loc', $options)) {
            $loc = $options['loc'];
            $this->body['loc'] = is_array($loc) ? implode('-', $loc) : $loc;
        }

        if (array_key_exists('tags', $options)) {
            $tags = $options['tags'];
            if (is_array($tags)) {
                $tags = is_array(reset($tags)) ? json_encode($tags) : implode('|', $tags);
            }
            $this->body['tags'] = $tags;
        }

        if (array_key_exists('count', $options)) {
            $this->body['count'] = intval($options['count']);
        }

        if (array_key_exists('page', $options)) {
            $this->body['page'] = intval($options['page']) - 1;
        }

        $minPrice = 0.0;
        if (array_key_exists('min_price', $options)) {
            $minPrice = floatval($options['min_price']);
            if ($minPrice < 0) {
                throw new UnexpectedValueException('min_price must be larger than 0');
            }
            if ($minPrice > 0) {
                $this->body['min_price'] = $minPrice;
            }
        }

        $maxPrice = $minPrice + 1;
        if (array_key_exists('max_price', $options)) {
            $maxPrice = floatval($options['max_price']);
            if ($maxPrice < 0) {
                throw new UnexpectedValueException('max_price must be larger than 0');
            }
            if ($maxPrice > 0) {
                $this->body['max_price'] = $maxPrice;
            }
        }

        if ($maxPrice < $minPrice) {
            throw new UnexpectedValueException('max_price must be larger than min_price');
        }

        if (array_key_exists('keywords', $options)) {
            $this->body['keywords'] = $options['keywords'];
        }

        return $this->curl(static::PRODUCT_SEARCH_SERVICE_TYPE, $serviceID);
    }
}
