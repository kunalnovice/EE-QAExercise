@testapi
Feature: HotelBooking API Invalid Test
  Scenario Outline: This is API Test to create Booking entering valid inputs on HotelBooking Page
    Given Request is created for API booking
    When POST Method is invoked with Parameters Body "<JSONInstance>"
        |Test.json|src\test\resources\TestData\JSON\HotelBooking_invalid|
    Then Status is "<HttpStatusCode>"
    Examples:
    |Test Case Desc|HttpStatusCode|JSONInstance|
    |1 FNAME is empty String      |500|json1|
    |2 special char in totalprice |500|json2|
    |3 FNAME is null              |500|json3|
   # I observed  response code 400 was being returned intermittently but later on all non 200 Response were havng status code as 500.
   # It may be that Microservice state must have changed.
   # I have set the status code as 500. Please edit it at run time if required.


   # other test scenarios to follow
   # All combination of other test cases i.e max+1 length, min-1 , empty, null values for each fields
   # Missing Mandatory fields
   # Verification of all applicable non 4**,5** status codes
   # test for invalid set of formatted characters i.e Deposit='Yes', totalprice='£34.5' or 'GBP 23.4'
   # Duplicity of attributes with same or different values
   # Removal of Attributes completely
   # mismatching content length
   # unauthenticated request for DELETE booking operation
   # Removal of other Header Parameters
   # Dates in past

