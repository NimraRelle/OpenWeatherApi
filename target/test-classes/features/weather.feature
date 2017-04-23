@OpenWeather
@weather

Feature: Open weather - 3/5 days forecast

@weatherValid
Scenario Outline: 3/5 days forecast - verify response with valid parameters 
Given I open urlpath weather with parameters <params>
Then Response is valid according to WeatherScheme.json
Then Response code is <code>
And Response contains <values>
Examples:
|params                                   |code|values                                                           |
|q=Utrecht              |200 |name=Utrecht,,id=2745912      |
|q=Utrecht,NL           |200 |name=Utrecht,,id=2745912      |
|lat=52.0908,,lon=5.1222|200 |name=Utrecht,,id=2745912      |
|id=2745912             |200 |name=Utrecht,,id=2745912      |
|zip=94040,us           |200 |name=Mountain View,,id=5375480|


@weatherInvalid
Scenario Outline: 3/5 days forecast - verify invalid results 
Given I open urlpath weather with parameters <params>
Then Response code is <code>
And Response contains <values>
Examples:
|params                  |code|values                          |
|q=jjjj                  |404 |message=city not found          |
|lat=5233.0908,,lon=666  |400 |message=5233.0908 is not a float|
|lat=52.0908             |400 |message=Nothing to geocode      |
|APPID= ,,q=Utrecht      |401 |message=Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.|
|APPID=222222,,q=Utrecht |401 |message=Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.|