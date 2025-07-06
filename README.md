This is a simple new aggregator simulator which uses newsapi as external source:

features:
- uses JWT Token based authentication
- you can choose your preferences in news
- uses external news api: https://newsapi.org/v2


End points APIs supported are:

-register (post request): 
body: {
      userName:"your name:,
      password:"your password",
      mail: "your mail:
      }
http://localhost:8083/register

-verififyRegistration(post request, need to pass the token url):
http://localhost:8083/verifyRegistration?token="displayed token url"

-signin/login (post request)
body: {
      mail: "your mail,
      password:"your password"
      }
http://localhost:8083/signin

-update preferences (put request)
http://localhost:8083/preferences

-get preferences (get request)
http://localhost:8083/preferences






