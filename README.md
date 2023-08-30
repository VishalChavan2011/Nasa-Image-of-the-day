# NASA's Image of the day

In this application we display the NASA image of the day.    
The image is fetched from the server using NASA's API: https://api.nasa.gov 

## Tech Stack

- Kotlin
- Kotlin coroutines
- Hilt
- View Model
- Room Database
- Retrofit
- Glide
- ViewBinding
- Data class
- Sealed class

## WorkFlow

- For the initial launch of the application, the data is fetched from the Nasa API using Retrofit.
- This data is stored in the Room Database for future reference.
- This information will be used when the user relaunches the application.
- If the application is launched for the same date then the information from the database will be used, else it will be again fetched from the Nasa API.
- During the fetching stage of the application, whether from the database or server loading screen will be displayed.
- Any error encountered will be notified and an error screen will be displayed.
- User and retry the operation using the error button displayed on the error screen

## Screenshots

### 1) Image Screen
![Image Screen](https://github.com/VishalChavan2011/Nasa-Image-of-the-day/blob/master/Image%20Screen.jpg?raw=true)

### 2) Error Screen
![Error Screen](https://github.com/VishalChavan2011/Nasa-Image-of-the-day/blob/master/Error%20Screen.jpg?raw=true)

### 3) Loading Screen
![Loading Screen](https://github.com/VishalChavan2011/Nasa-Image-of-the-day/blob/master/Loading%20Screen.jpg?raw=true)


## API Reference

#### Get Image of the day

```http
  GET https://api.nasa.gov/planetary/apod?api_key=${key}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Example Query
https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY


### Generate your own API key

- Generate your own API key using personal email id from https://api.nasa.gov/#signUp
- After Sign up a mail will be received to your registered email address.
- DEMO_KEY can be used but it has its own limitations.
- In above examples, the special DEMO_KEY api key is used. This API key can be used for initially exploring APIs prior to signing up, but it has much lower rate limits, so you’re encouraged to signup for your own API key if you plan to use the API (signup is quick and easy). The rate limits for the DEMO_KEY are:

- Hourly Limit: 30 requests per IP address per hour
- Daily Limit: 50 requests per IP address per day

