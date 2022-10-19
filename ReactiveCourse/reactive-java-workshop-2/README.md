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
- 