# cds-swe-challenge

## How to deploy?

#### Clone into your local
```
git clone https://github.com/azmiratania/cds-swe-challenge.git
cd cds-swe-challenge
```

#### Set backend url
in the file: 
```
frontend/src/Services/ApiService.js
```

change:
```
const API_BASE_URL = "http://10.1.0.15:8080/api";
```
to:
```
const API_BASE_URL = "http://12.34.56.78:8080/api";
```
where 12.34.56.78 is your IP address.

to run locally, replace with `localhost`


#### Run with docker
```
docker compose build
docker compose up -d
```

#### To view the application
Backend running on http://12.34.56.78:8080/
<br/>Frontend running on http://12.34.56.78:3000/

#### To stop
```
docker compose stop
```
