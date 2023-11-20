@Reg 
Feature: This is a feature to login to naveenautomationlabs and select product to cart

 @Reg
  Scenario: login to naveenautomationlabs and add Samsung Galaxy Tab to cart
    Given I login into application
    When I click on add to cart of Samsung Galaxy Tab 
    Then Galaxy Tab is added to cart
 @sanity
  Scenario: login to naveenautomationlabs and add iphone to cart
    Given I login into application
    When I click on add to cart of iphone
    Then iphone is added to cart
 @Reg   
  Scenario: login to naveenautomationlabs and add Nikon camera to cart
    Given I login into application
    When I click on add to cart of Nikon
    Then Nikon camera is added to cart
