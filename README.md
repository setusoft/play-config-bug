This repository shows a bug in Play which affects testing in a multi module application. The problem is that the `GuiceApplicationBuilder` doesn't load the `application.conf` in test mode, if the config is contained in the root module and the test in another module.

If you run the test with `play test`, then the test fails with the following error:
```
[info] Application should
[error]   x return the config value
[error]    'Config value is not available' doesn't contain 'This should work in test mode' (ApplicationSpec.scala:23)
```

If you start the application with `play run`, then all works as ecpected and the page prints the message:
```
This should work in test mode
```
