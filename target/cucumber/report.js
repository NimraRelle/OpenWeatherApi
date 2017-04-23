$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("forecast.feature");
formatter.feature({
  "line": 3,
  "name": "Open weather - 3/5 days forecast",
  "description": "",
  "id": "open-weather---3/5-days-forecast",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@forecastrr"
    }
  ]
});
formatter.scenarioOutline({
  "line": 6,
  "name": "3/5 days forecast - verify response with valid parameters",
  "description": "",
  "id": "open-weather---3/5-days-forecast;3/5-days-forecast---verify-response-with-valid-parameters",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 5,
      "name": "@forecast"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I open urlpath forecast with parameters \u003cparams\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "Response is valid according to ForecastScheme.json",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "Response code is \u003ccode\u003e",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "Response contains \u003cvalues\u003e",
  "keyword": "And "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "open-weather---3/5-days-forecast;3/5-days-forecast---verify-response-with-valid-parameters;",
  "rows": [
    {
      "cells": [
        "params",
        "code",
        "values"
      ],
      "line": 12,
      "id": "open-weather---3/5-days-forecast;3/5-days-forecast---verify-response-with-valid-parameters;;1"
    },
    {
      "cells": [
        "q\u003dUtrecht",
        "200",
        "city.name\u003dUtrecht,,city.country\u003dNL,,city.id\u003d2745912asNumber"
      ],
      "line": 13,
      "id": "open-weather---3/5-days-forecast;3/5-days-forecast---verify-response-with-valid-parameters;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 13,
  "name": "3/5 days forecast - verify response with valid parameters",
  "description": "",
  "id": "open-weather---3/5-days-forecast;3/5-days-forecast---verify-response-with-valid-parameters;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 5,
      "name": "@forecast"
    },
    {
      "line": 1,
      "name": "@forecastrr"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I open urlpath forecast with parameters q\u003dUtrecht",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "Response is valid according to ForecastScheme.json",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "Response code is 200",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "Response contains city.name\u003dUtrecht,,city.country\u003dNL,,city.id\u003d2745912asNumber",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "forecast",
      "offset": 15
    },
    {
      "val": "q\u003dUtrecht",
      "offset": 40
    }
  ],
  "location": "StepDef.open_url(String,String)"
});
formatter.result({
  "duration": 2842187332,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ForecastScheme.json",
      "offset": 31
    }
  ],
  "location": "StepDef.validate_scheme(String)"
});
formatter.result({
  "duration": 48267334137,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 17
    }
  ],
  "location": "StepDef.status_code(int)"
});
formatter.result({
  "duration": 184337133,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "city.name\u003dUtrecht,,city.country\u003dNL,,city.id\u003d2745912asNumber",
      "offset": 18
    }
  ],
  "location": "StepDef.response_contains(String)"
});
formatter.result({
  "duration": 46581846188,
  "status": "passed"
});
});