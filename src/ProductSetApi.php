<?php

namespace ProductAI;

use BadMethodCallException;
use CURLFile;

class ProductSetApi extends Base
{
    const SERVICE_TYPE = 'product_sets';

    const SERVICE_ID = '_0000178';

    public function __call($name, $args)
    {
        if (method_exists($this, $name)) {
            $this->initialize();
            return call_user_func_array([$this, $name], $args);
        } 
        
        throw new BadMethodCallException('Call to undefined method '.get_class($this)."::{$name}()", 1);
    }

    private static function serviceType()
    {
        return static::SERVICE_TYPE;
    }

    private static function actionID()
    {
        return static::SERVICE_ID;
    }
    
    protected function getProductSets() {
        $this->method = 'GET';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}", false);
    }

    protected function deleteAllProductSets() {
        $this->method = 'DELETE';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}", false);
    }

    protected function createProductSet($name, $description='') {
        $this->body['name'] = $name;
        $this->body['description'] = $description;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}", true);
    }

    protected function getProductSet($productSetID) {
        $this->method = 'GET';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID", false);
    }

    protected function updateProductSet($productSetID, $name, $description='') {
        $this->method = 'PUT';
        $this->body['name'] = $name;
        $this->body['description'] = $description;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID", true);
    }

    protected function deleteProductSet($productSetID) {
        $this->method = 'DELETE';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID", false);
    }

    protected function getProducts($productSetID, $productIDArray) {
        if (!is_array($productIDArray)) {
            throw new InvalidArgumentException('productIDArray must be an array.');
        }
        $this->method = 'GET';
        $this->body['ids'] = $productIDArray;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/products", true);
    }

    protected function addProduct($productSetID, $productID, $price, $keywords, $imageUrl, $metadata, $tags) {
        $this->method = 'POST';
        $this->body['product_id'] = $productID;
        $this->body['price'] = $price;
        $this->body['keywords'] = $keywords;
        $this->body['image_url'] = $imageUrl;
        $this->body['meta'] = $metadata;
        $this->body['tags'] = $tags;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/products", false);
    }

    protected function addProducts($productSetID, $productsFilePath) {
        $this->method = 'POST';
        $this->body['products_to_add'] = new CURLFile($productsFilePath);
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/products", false);
    }

    protected function deleteProducts($productSetID, $productsFilePath) {
        $this->method = 'POST';
        $this->body['products_to_delete'] = new CURLFile($productsFilePath);
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/products", false);
    }

    protected function deleteProductsByProductIDs($productSetID, $productIDArray) {
        if (!is_array($productIDArray)) {
            throw new InvalidArgumentException('productIDArray must be an array.');
        }
        $this->method = 'DELETE';
        $this->body['ids'] = $productIDArray;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/products", true);
    }

    protected function createService($productSetID, $name, $scenario) {
        $this->method = 'POST';
        $this->body['name'] = $name;
        $this->body['scenario'] = $scenario;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$productSetID/services", true);
    }
}