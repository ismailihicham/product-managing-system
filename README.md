# product-managing-system
this is a project for managing products.
it is designed following hexagonal architecture, it contains four modules under the root module, 
the product-domain module here e have the business needs for the project, this module is not depending to any modules, 
the product-dataaccess module here we manage the calls with database , it depends to product-domain. 
the product-application here I implement the controllers with routes that will be called by end users.
product-container module this module have dependency to all modules and here we can launch our application.
this project is build with maven and java21 and using intellij IDE