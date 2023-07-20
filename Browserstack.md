# Browserstack
### Getting started

Before you do anything, you must create a local tunnel that Browserstack can use to access our internal environments.

On Windows, run the following:  
````startBrowserstackLocal.bat````

On Linux/Mac, run the following:
````./startBrowserstackLocal.sh````

### How to run tests
``mvn clean test -P browserstack -P <device> -P <suite>``

#### Accepted device values:
* iphone8
* iphone11
* iphone11-pro
* iphone11-pro-max
* samsung9
* samsung10
* windows10 (note: this currently defaults to Chrome as a browser)


#### Accepted suite values:
* parallel
* parallelMobile
* smoke
* smokeMobile
* anonymousMobile
* anonymousMobile2
* newUsersMobile
* userDependentMobile

Obviously, running a mobile suite using a desktop device will cause failures (and vice versa).