@testui
Feature: HotelBooking Page UI Test
  Scenario Outline: This is an UI Test to create Booking entering valid inputs on HotelBooking Page
    Given User submit Parameters "<FormParameters>" with values "<FormValues>" on Hotel Booking Page
    When User saves the Booking
    Then Hotel Booking Page parameters "<FormParameters>" contains values "<FormValues>"
    Examples:
    |Test Case Desc|FormParameters|FormValues|
    |1 Deposit= true price= integer  | FNAME,SNAME,PRICE,DEPOSIT,CHECKIN,CHECKOUT  | TestFName,TestLName,123,false,2019-04-27,2019-04-29|
    |2 Deposit= false price= fraction| FNAME,SNAME,PRICE,DEPOSIT,CHECKIN,CHECKOUT  | FName,LName,123.45,true,2019-04-21,2019-04-29|

    # other test cases with other data variation for automation to follow here
    # Schema Validation
    # Data type validation


  Scenario Outline: This is an UI Test to delete an existing Booking on HotelBooking Page
    Given Booking exist for Parameters "<FormParameters>" with values "<FormValues>"
    When User deletes the Booking
    Then Booking is deleted
    Examples:
      |Test Case Desc|FormParameters|FormValues|
      |     1        | FNAME,SNAME,PRICE,DEPOSIT,CHECKIN,CHECKOUT  | TestFName,TestLName,123,true,2019-04-27,2019-04-29|
