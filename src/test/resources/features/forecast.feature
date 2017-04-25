@OpenWeather
@forecast

Feature: Open weather - 3/5 days forecast

@forecastValid
Scenario Outline: 3/5 days forecast - verify response with valid parameters 
Given I open urlpath forecast with parameters <params>
Then Response is valid according to ForecastScheme.json
Then Response code is <code>
And Response contains <values>
Examples:
|params                 |code|values                                                   |
|q=Utrecht              |200 |city.name=Utrecht,,city.country=NL,,city.id=2745912      |
|q=Utrecht,NL           |200 |city.name=Utrecht,,city.country=NL,,city.id=2745912      |
|lat=52.0908,,lon=5.1222|200 |city.name=Utrecht,,city.country=NL,,city.id=2745912      |
|id=2745912             |200 |city.name=Utrecht,,city.country=NL,,city.id=2745912      |
|zip=94040,us           |200 |city.name=Mountain View,,city.country=US,,city.id=5375480|


@forecastInvalid
Scenario Outline: 3/5 days forecast - verify invalid results 
Given I open urlpath forecast with parameters <params>
Then Response code is <code>
And Response contains <values>
Examples:
|params                         |code|values                    |
|q=jjjj                         |404 |message=city not found    |
|lat=5233.0908,,lon=666         |400 |message=5233.0908 is not a float|
|lat=52.0908                    |400 |message=Nothing to geocode|
|APPID= ,,q=Utrecht             |401 |message=Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.|
|APPID=333333333333,,q=Utrecht  |401 |message=Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.|


# The advantage of the above implementation is that it is easy to be reused and the key:value 
# pairs can be changed or to be added, removed acording to the specific scenarios
# Disadvantage is that it is not easiliy readable at the beginning and if the value is not equal to the 
# expected then it won't work
# i.e. if lat=52.0908 lon=5.1222 and we want to check them using the above table implementation
# the result will be a fail as the api response is  "lon": 5.12222, "lat": 52.090832
# which may or may not be considered a defect, depends on the requirement

# Another possible implementation of the tables would have looked like that:

# Given I open urlpath forecast with parameters <params>
# Then Response is valid according to ForecastScheme.json
# Then Response code is <code>
# Response contains key city.name with value <city>
# Response contains key city.country with value <country>
# Response contains key city.id with value <cityID>
# Examples:
# |params                 |code|city         |country|cityID |
# |q=Utrecht              |200 |Utrecht      |NL     |2745912|
# |q=Utrecht,NL           |200 |Utrecht      |NL     |2745912|
# |lat=52.0908,,lon=5.1222|200 |Utrecht      |NL     |2745912|
# |id=2745912             |200 |Utrecht      |NL     |2745912|
# |zip=94040,us           |200 |Mountain View|US     |5375480|

# The disadv. is that we can't change the key:value pairs per parameters
# And the scenario itself contains more lines to be read which means it will be slower
# The adv. is that every key:value pair can be asserted by unique way if needed



