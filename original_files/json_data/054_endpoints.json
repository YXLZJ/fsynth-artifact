
{
  "GET /api": {
    "description": "serves up a json representation of all the available endpoints of the api"
  },
  "GET /api/cities": {
    "description": "serves an array of all cities objects (in alphabetical order). including: city name, longitude, latitude and radius, filtered by user if username query is given",
    "queries": ["username"],
    "exampleResponse": {
      "cities": [{
        "city_name": "London",
        "city_longitude": -0.1275,
        "city_latitude": 51.5072,
        "city_radius": 12000
      }]
    }
  },
  "GET /api/users": {
    "description": "serves an array of all user objects",
    "queries": [],
    "exampleResponse": {
      "users": [
          { "username": "madexplorer", "password": "myPassword" },
          { "username": "bigtraveller", "password": "supersecret" },
          { "username": "nolist", "password": "sadtimes" }
        ]
      }
  },
  "GET /api/bucket_list": {
    "description": "serves an array of all bucket_list objects",
    "queries": [],
    "exampleResponse": {
        "bucketList": [
          {
            "bucket_list_id": 1,
            "place_displayname": "Big Ben",
            "place_json": "[Object]",
            "city_name": "London",
            "username": "madexplorer"
          },
          {
            "bucket_list_id": 2,
            "place_displayname": "Tower of London",
            "place_json": "[Object]",
            "city_name": "London",
            "username": "madexplorer"
          }
        ]
      }
  },
  "GET /api/users/username": {
    "description": "serves a user object for the requested username",
    "queries": [],
    "exampleResponse": { "user": { "username": "madexplorer", "password": "myPassword" }}
  },
  "GET /api/cities/:city_name": {
    "description": "serves a city object for the requested city_name",
    "queries": [],
    "exampleResponse": { "city": {
      "city_name": "London",
      "city_longitude": -0.1275,
      "city_latitude": 51.5072
    } 
    }
  },
  "GET /api/bucket_list/:username": {
    "description": "serves an array of bucket_list places objects for the requested username, filtered by city if city_name query is given",
    "queries": ["city_name"],
    "exampleResponse": {"bucketList": [
      {
        "bucket_list_id": 1,
        "place_displayname": "Big Ben",
        "place_json": "[Object]",
        "city_name": "London",
        "username": "madexplorer"
      },
      {
        "bucket_list_id": 2,
        "place_displayname": "Tower of London",
        "place_json": "[Object]",
        "city_name": "London",
        "username": "madexplorer"
      }
    ]
    }
  },
  "POST /api/users": {
    "description": "posts a new user to the users table and responds with the posted user, when provided with a body with valid username and password properties e.g. {username: 'newuser2', password: 'passypassword'}",
    "queries": [],
    "exampleResponse": { "addedUser": { "username": "newuser2", "password": "passypassword" }} 
  },
  "POST /api/bucket_list": {
    "description": "posts a new place object to the bucket_list table and responds with the posted place, when provided with a body with valid properties e.g. {place_displayname: 'Bullring & Grand Central', place_json: placeDataObject, city_name: 'Birmingham',username: 'madexplorer'}",
    "queries": [],
    "exampleResponse": {"addedPlace": {
      "bucket_list_id": 1,
      "place_displayname": "Big Ben",
      "place_json": "[Object]",
      "city_name": "London",
      "username": "madexplorer"
    }} 
  },
  "DELETE /api/bucket_list/:bucket_list_id": {
    "description": "deletes a places from the bucket_list table according to the provided bucket_list_id",
    "queries": [],
    "exampleResponse": null
  }
}
