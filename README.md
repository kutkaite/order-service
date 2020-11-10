Before starting the application:
`docker-composer up -d`

Topics created automatically (not advised in production environment):

`t.commodity.order`

`t.commodity.order-reply`

Topic to create:

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t.commodity.promotion

PromotionProducer and DiscountProducer publishes to the same topic `t.commodity.promotion`

#### API

`http://localhost:9001/api/order`

```
{
    "orderLocation": "Stockholm",
    "creditCardNumber": "bankAccount",
    "items" : [
        {
            "itemName": "Pizza",
            "price": "10",
            "quantity": "2"
        }
    ]
}
```

`http://localhost:9001/api/discount`

```
{
    "discountCode" : "whatever-40",
    "discountPercentage" : 40
}
```

`http://localhost:9001/api/promotion`

```
{
    "promotionCode" : "amazinPromotion"
}
```

#### H2 console

`http://localhost:9001/h2-console`

#### Consumer:
[StorageService](https://github.com/kutkaite/storage-service)
