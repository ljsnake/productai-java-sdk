package cn.productai.api.examples.productset;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.examples.IExample;
import cn.productai.api.pai.entity.productset.*;

public class ProductSetManagementApiExample implements IExample {

    private String productSetId = "";
    private String productId = "";
    private Object productSets;

    @Override
    public void run(IWebClient client) {
        // 创建商品集
        //productSetId = createProductSet(client);

        // 获取所有商品集
        //getProductSets(client);

        // 更新商品集
        //updateProductSet(client, productSetId);

        // 通过商品集 ID 删除商品集
        // TODO: now backend has bug
        //deleteProductSet(client, productSetId);

        // 删除所有商品集
        // TODO: now backend has bug
        //deleteAllProductSets(client);

        /*
         * 通过商品集 ID 获取商品集
         */
        //getProductSet(client, productSetId);

        /**
         * 添加商品到商品集中
         */
        productSetId = "0as4ocve";
        //addProduct(client, productSetId);

        // 获取商品集中的所有商品
        getProducts(client, productSetId, new String[]{"_001"});
        getProducts(client, productSetId, new String[]{"_002"});

        /**
         * 批量添加商品到商品集中
         */
//        addProducts();

        /**
         * 批量删除商品
         */
//        deleteProducts();

        /**
         * 通过商品 IDs 删除商品
         */
        deleteProductsByProductIDs(client, productSetId, new String[]{"_001"});

        /**
         * 创建服务
         */
        createService(client, productSetId);
    }

    private void createService(IWebClient client, String productSetId) {
    }

    private void deleteProductsByProductIDs(IWebClient client, String productSetId, String[] ids) {
        System.out.println("==>  Demo - 删除商品  <==");

        DeleteProductsByProductSetRequest request = new DeleteProductsByProductSetRequest();

        try {
            DeleteProductsByProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void addProduct(IWebClient client,
                            String productSetId) {
        System.out.println("==>  Demo - 添加商品到商品集  <==");

        String productId = "_001";
        String price = "35.6";
        String keywords = "皮鞋";
        String imageUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1987650312,1979230819&fm=27&gp=0.jpg";
        String meta = "白色";
        String tags = "短裙";

        AddProductRequest request1 = new AddProductRequest(
                productSetId, productId, price, keywords, imageUrl, meta, tags);
        AddProductRequest request2 = new AddProductRequest(
                productSetId, "_002", price, keywords, imageUrl, meta, tags);

        try {
            AddProductResponse response = client.getResponse(request1);
            client.getResponse(request1);


            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void getProducts(IWebClient client, String productSetId, String[] productIds) {
        System.out.println("==>  Demo - 获取商品集中所有商品  <==");

        GetProductsByProductSetRequest request = new GetProductsByProductSetRequest(productSetId, productIds);

        try {
            GetProductsByProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void getProductSet(IWebClient client, String productSetId) {
        System.out.println("==>  Demo - 查找商品集  <==");

        GetProductSetRequest request = new GetProductSetRequest(productSetId);

        try {
            GetProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(
                    String.format(
                            "ProductSet Name - %s, Description - %s",
                            response.getName(),
                            response.getDescription()));
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void updateProductSet(IWebClient client, String productSetId) {
        System.out.println("==>  Demo - 删除所有商品集  <==");

        UpdateProductSetRequest request = new UpdateProductSetRequest(
                productSetId,
                "New ProductSet Name",
                "Another product set description.");

        try {
            UpdateProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void deleteAllProductSets(IWebClient client) {
        System.out.println("==>  Demo - 删除所有商品集  <==");

        DeleteAllProductSetRequest request = new DeleteAllProductSetRequest();

        try {
//            DeleteAllProductSetResponse response = client.getResponse(request);

            // TODO 暂时逐个删除
            GetAllProductSetsRequest response = new GetAllProductSetsRequest();
            GetAllProductSetsResponse allProductsResponse = client.getResponse(response);

            for (ProductSet ps : allProductsResponse.getResults()) {
                DeleteProductSetRequest deleteRequest = new DeleteProductSetRequest(ps.getId());
                client.getResponse(deleteRequest);
            }

            System.out.println("==============================Result==============================");

            // access the response directly
//            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private void deleteProductSet(IWebClient client, String productSetId) {
        System.out.println("==>  Demo - 通过 ID 删除商品集  <==");

        DeleteProductSetRequest request = new DeleteProductSetRequest(productSetId);

        try {
            DeleteProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }

    private String createProductSet(IWebClient client) {
        System.out.println("==>  Demo - 新增商品集  <==");

        CreateProductSetRequest request = new CreateProductSetRequest(
                "My Product Set",
                "This is my product set of fashion images.");

        try {
            CreateProductSetResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            System.out.println(
                    String.format(
                            "ProductSet Name - %s, Description - %s",
                            response.getName(),
                            response.getDescription()));
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

            return response.getId();
        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }

        return null;
    }

    public void getProductSets(IWebClient client) {
        System.out.println("==>  Demo - 获取所有商品集  <==");

        GetAllProductSetsRequest request = new GetAllProductSetsRequest();

        try {
            GetAllProductSetsResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            // access the response directly
            for (ProductSet ps : response.getResults()) {
                System.out.println(String.format("Product Set, id: %s, name: name", ps.getId(), ps.getName()));
            }
            System.out.println(String.format("Response Json : %s", response.getResponseJsonString()));
            System.out.println("==============================Result==============================");

        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(
                    String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                            e.getErrorCode(),
                            e.getErrorMessage()));
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(
                    String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                            e.getErrorCode(),
                            e.getErrorMessage(),
                            e.getRequestId()));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}