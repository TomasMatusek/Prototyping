# Reactive programming with Java

## Part

Blocking, unnecessarily sequential (line 1 have to be done before accessing line 2)

It can be done at the same time as input is only userId

``` ssh
int userId = 123;
repository.getUser(userId);
repository.getPreferences(userId);
```

## Part 11

- Observer pattern
- Subscriber pattern

## Part 15

- Flow and Stream API in Java 9
- Three abstractions (interfaces only)
    - Publisher
    - Subscriber
    - Subscription
- We will use
    - Flux - asynchronous sequence of items 0..n items
    - Mono - asynchronous item - one item that can come in the future 0..1 item

## Part 16

- Mono
- 