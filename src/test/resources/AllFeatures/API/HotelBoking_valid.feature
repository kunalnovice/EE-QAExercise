@testapi
Feature: HotelBooking API Valid Test

  Scenario Outline: This is API Test to delete an existing Booking on HotelBooking Page
    Given Request is created for API booking
    And POST Method is invoked with Parameters Body "<JSONInstance>"
      | Test.json | src\test\resources\TestData\JSON\HotelBooking_invalid |
    When DELETE Method is invoked for API Booking
    Then Status is "<HttpStatusCode>"
    Examples:
      | Test Case Desc                                    | HttpStatusCode | JSONInstance |
      | 1 Booking is created and  further deleted via API | 201            | json4        |

# other test scenarios to follow
  #All combination of other test cases i.e max-1 length, min+1 length, boundary condition for each fields
  #checks for  valid set of formatted characters i.e FNAME  to include '-' or '''
  # Dates in future
  # upper and lower case char i.e LNAME = ADAm, EllA, oScaR
  # special character in fields. i.e FNAME= âdam, êlla, ôscar