<?php

namespace ProductAI;

use BadMethodCallException;

class SearchServiceManagementApi extends Base
{
    const SERVICE_TYPE = 'customer_services';

    const SERVICE_ID = '_0000172';

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

    protected function getServices()
    {
        $this->method = 'GET';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}");
    }

    protected function getService($serviceID)
    {
        $this->method = 'GET';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$serviceID");
    }

    protected function removeService($serviceID)
    {
        $this->method = 'DELETE';
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$serviceID");
    }

    protected function updateService($serviceID, $name)
    {
        $this->method = 'PUT';
        $this->body['name'] = $name;
        return $this->curl("{$this->serviceType()}", "{$this->actionID()}/$serviceID", true);
    }
}