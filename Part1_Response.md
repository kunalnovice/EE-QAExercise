Application Under Test: http://hotel-test.equalexperts.io/

Possible Test cases documented as part of BDD Scenarios\outline.
Please refer to AllFeatures\API\HotelBooking_**.feature

UI Issues
1. First and Surname are accepting Numbers and special chars. usually only ' or -  special character is alowed.
2. There are no checks applied on max length of First and Surname. It further causes overlapping issues of same values.
3. There are no checks applied on max and minimum value of Integer and fraction part for Price Label.It further causes overlapping issues of same values.
4. Check-in and Check out dates : Past dates should be disabled on UI.
5. No UI\server side validation for check dates in past. Check -in and check-out dates should be any date from current date only.
6. No UI\server side validation for check dates rule i.e check-in date < check-out date.
7. Though Mandatory Checks are applied on UI for all field values but no suggestive related error message is displayed on UI.

UI Improvements

1  Price can say curreny on top.
2. Date Box can include quick navigation between years.
3. for unique identification of Booking- Booking ID could have also been displayed on the Page. makes automation testing easier.
4. for better UX - Deposit label should have been default none selected . Currently it is selected as true.
5. Pagination can be adopted.
6. Authentication - Access can be restricted.

API Issues

on making POST request http://hotel-test.equalexperts.io/booking and with below mentioned body
1. On Sending deposit paid with junk values i.e "fall" and in synch with content-length - response is 200.  It should be 400
        It seems server is defaulting the value to 'true', if request does not contain either of 'true' or 'false'.
2. Sending incorrect dates like 2019-02-30 , 2019-04-31 or ABCD-03-03  server is cleverly converting to 2019-03-02 , 2019-05-01, 2001-03-03. response is 200. It should be 400
        It appears the logic in BackEnd for processing Dates is incorrect.
3. Attributes deposit paid and dates with junk values - no appropriate error message sent by server. Status code 400 Bad request- not helpful for investigation.
4. Attributes firstname and lastname with null values - no appropriate error message sent by server. Status code 500 is returned- not helpful for investigation.
5. Eliminating any of the attribute in the Body - gives 500(Internal Server Error)- it can argued that it is a 4** condition i.e 422 or 412
6. Duplicating few attributes in the Body - gives 400(bad request)- No appropriate error mesage sent.

