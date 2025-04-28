# product-managing-system
this is a project for managing products.
it is designed following hexagonal architecture, it contains four modules under the root module, 
the product-domain module here e have the business needs for the project, this module is not depending to any modules, 
the product-dataaccess module here we manage the calls with database , it depends to product-domain. 
the product-application here I implement the controllers with routes that will be called by end users.
product-container module this module have dependency to all modules and here we can launch our application.
this projet is running on 8080 port.
this project is build with maven and java21 and using intellij IDE


To be able to create an account you should run security-service,
you change directory to security service and run it by 
launching this command "mvn spring-boot:run"

it runs on 9898 port and create a token for user admin with password in users.yaml, then this token should be added to the request "/account" in "authenrization" and you select "bearer" then you add payload to create an account.
