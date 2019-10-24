# Example Application demonstrating the CQRS pattern

## Goal

The goal here is to demonstrate the CQRS pattern, using asynchronous event driven state transfer as the means of communication, between the services. The system is composed of two spring boot micro services, which are built using Domain driven design principles, each with their own MongoDB database.
The micro services use a publish/subscribe model to communicate with each other, using Kafka based messaging. Each of these micro services expose a REST API, which becomes their interface to the channel applications.

The first micro service is modelled around the `accounts` bounded context. 
The second micro service is modelled around the `orders` bounded context. 
We are going to establish that the `accounts` context is the system of record for the customer's information, and the `orders` bounded context, is the system of record for customer orders information.

The architecture of our domain’s data model requires some duplication of data across bounded contexts, or even between services within the same contexts, therefore we must ensure data consistency. 
Take, for example, a change in a customer’s name, or address. Since the `accounts` domain is the SOR for customer information, all the customer information is going to reside in the `accounts` Mongo database. 
However, to fulfill orders, the orders context also needs to maintain the customer’s address.

### Event carried state transfer
If a piece of shared data is changed, then the party making the change should be responsible for communicating the change, without the expectation of a response. 
They are stating a fact, not asking a question. Interested parties can choose if, and how, to act upon the change notification. This decoupled communication model is often described as `Event-Carried State Transfer`

In the case of this example, the `accounts` domain service exposes an API, which allows for adding a new customer, or changing an existing customer's information. This data is then written to the `accounts` MongoDB database. The state changes are composed into an Event, which is then published to a Kafka topic.

In our example, the `orders` domain service is interested in getting the latest customer updates, so it subscribes to the customer change topic, and updates the customer information in its own orders MongoDB database.

### Kafka as the Event store
In this model, we are also using Kafka as the Event store. All the state change actions are recorded in Kafka, and they can be replayed back to rebuild the state of the aggregate store (in this case, MongoDB). 
They also act as an audit mechanism for recording every single state change event that has happened over a certain time period.

Kafka also serves as a means of decoupling services from each one another, while still ensuring the data is exchanged.

## Technology Stack

Each domain service is built using Spring Boot 2.0 and Gradle. 
Each Spring Boot service includes Spring boot actuator, Spring Data REST, Spring Data MongoDB, Spring for Apache Kafka, and SpringFox for API documentation. We use managed cloud versions of MongoDB and Kafka for the database, and the messaging platform respectively. 

### MongoDB Atlas

MongoDB Atlas is a fully-managed MongoDB-as-a-Service, available on AWS, Azure, and GCP. For this example, I have created a free MongoDB atlas account, and setup a free, M0-sized MongoDB cluster on GCP in the `us-central1` region. 
The M0-sized 3-data node cluster, with shared RAM and 512 MB of storage.

### Confluent Cloud
Confluent Cloud is a fully-managed, cloud-based streaming service based on Apache Kafka. Confluent Cloud delivers a low-latency, resilient, scalable streaming service, deployable in minutes. 
For this example, I have created a Kafka cluster in the `us-central1` region on GCP.

### Google Kubernetes Engine (GKE)
Google Kubernetes Engine (GKE) provides a fully-managed, production-ready Kubernetes environment for deploying, managing, and scaling your containerized applications using Google infrastructure.
For this example, I have created a Kubernetes cluster in `GCP us-central1` region, which will allow me to deploy and manage the two micro services applications. Since Kafka and MongoDB clusters are also on the cloud, this makes the entire stack cloud native.
 
## Deployment Infrastructure

In our example, the two domain services, `'accounts` and `orders` are deployed on Kubernetes. They talk to the Kafka cluster, and the MongoDB cluster which are also managed services deployed in GCP.

## Code base

### Accounts Domain

See the source at: <https://github.com/iyerajesh/platform/tree/master/accounts-domain>

The `docker` and the `kubernetes` directories contain the docker and the kubernetes deployment configuration.

```bash
gradle clean build
./docker/docker-build-push.sh 
kubectl apply -f kubernetes/accounts-domain-dc.yaml
```

### Orders Domain

See the source at: <https://github.com/iyerajesh/platform/tree/master/orders-domain>

The `docker` and the `kubernetes` directories contain the docker and the kubernetes deployment configuration.

```bash
gradle clean build
./docker/docker-build-push.sh 
kubectl apply -f kubernetes/orders-domain-dc.yaml
```
## MongoDB and Kafka configuration

All the MongoDB and Kafka cluster configuration is deployed as Kubernetes secrets and ConfigMaps.

The MongoDB connecting string is deployed a secret.

```bash
kubectl apply -f accounts-domain/kubernetes/mongodb-atlas-secret.yaml 
```

The Kafka API key and secret is deployed as a kubernetes secret, and the SSL configuration is deployed as a ConfigMap.

```bash
kubectl apply -f accounts-domain/kubernetes/confluent-cloud-kafka-secret.yaml 
kubectl apply -f accounts-domain/kubernetes/confluent-cloud-kafka-configmap.yaml 
```

## Service deployments

The kubernetes deployments of the two services - accounts and orders are shown below:

```bash
kubectl apply -f accounts-domain/kubernetes/accounts-domain-dc.yaml
kubectl apply -f orders-domain/kubernetes/orders-domain-dc.yaml
```

## Kafka topic and MongoDB databases

We create two separated databases, for each of the two services, called `accounts` and `orders` in the MongoDB cluster. We create a collection called `customer.accounts` under the accounts database to hold the customer accounts.
We create a collection called `customer.orders` under the orders database, to hold the customer orders. 

The two services communicate via a Kafka topic called `accounts.customer.change`.


Shows the running pods, in the Kubernetes cluster. 
```text
$ kubectl get pods
NAME                           READY   STATUS    RESTARTS   AGE
accounts-655669b94c-dxgcg      1/1     Running   0          22h
api-gateway-59446dd9cb-c8l8s   1/1     Running   0          25h
orders-54db86f47b-kqw8l        1/1     Running   0          22h

$ kubectl get secrets
NAME                    TYPE                                  DATA   AGE
confluent-cloud-kafka   Opaque                                1      3d
default-token-4h59v     kubernetes.io/service-account-token   3      3d22h
mongodb-atlas           Opaque                                2      46h

$ kubectl get configmaps
NAME                    DATA   AGE
confluent-cloud-kafka   6      3d

```

## Ingress into the Kubernetes cluster

For this example, we are creating two kubernetes services, one for each domain, which will allow us external IP access to the APIs. The two services are of type `LoadBalancer`

```text
$ kubectl get services
NAME          TYPE           CLUSTER-IP      EXTERNAL-IP     PORT(S)        AGE
accounts      LoadBalancer   10.31.254.221   35.243.151.27   80:32622/TCP   2d17h
api-gateway   LoadBalancer   10.31.241.201   35.237.18.9     80:30620/TCP   2d18h
kubernetes    ClusterIP      10.31.240.1     <none>          443/TCP        3d22h
orders        LoadBalancer   10.31.241.237   34.74.58.243    80:32691/TCP   42h
```

# Testing the application

The accounts domain service exposes a create customer API, which will allow the creation of a new customer, or updating the information of a existing customer ID. 

```text
API: POST http://35.243.151.27/accounts/customer/create
Body: 
{
  "id": 123456,
  "name": {
    "title": "Mr.",
    "firstName": "Rajesh",
    "middleName": "V.",
    "lastName": "Iyer",
    "suffix": "Jr."
  },
  "contact": {
    "primaryPhone": "555-666-7777",
    "secondaryPhone": "555-444-9898",
    "email": "john.doe@internet.com"
  },
  "addresses": [
    {
      "type": "BILLING",
      "description": "My cc billing address",
      "address1": "123 Oak Street",
      "address2": null,
      "city": "Sunrise",
      "state": "CA",
      "postalCode": "12345-6789"
    },
    {
      "type": "SHIPPING",
      "description": "My home address",
      "address1": "123 Oak Street",
      "address2": null,
      "city": "Sunrise",
      "state": "CA",
      "postalCode": "12345-6789"
    }
  ],
  "creditCards": [
    {
      "type": "PRIMARY",
      "description": "VISA",
      "number": "1234-6789-0000-0000",
      "expiration": "6/19",
      "nameOnCard": "John S. Doe"
    },
    {
      "type": "ALTERNATE",
      "description": "Corporate American Express",
      "number": "9999-8888-7777-6666",
      "expiration": "3/20",
      "nameOnCard": "John Doe"
    }
  ]
}

```

The accounts domain emits a `CustomerChangeEvent` into the `accounts.customer.change` kafka topic. The orders domain service consumes the message, and updates its data model, with the customer information.

The orders micro service exposes a listing API, and a Details API which gives a list of all the customer orders in the orders database in MongoDB.

```text
Listing API: GET http://34.74.58.243/orders/list
Details API: GET http://34.74.58.243/orders/order/123456
```

The `postman` directory in the root of the project, contains the postman collection, which can be imported into Postman.