# Mars Explorer MVC

Mars Explorer is a university project built using the Spring MVC framework. This application allows users to explore Mars Rover photos by fetching data from NASA's Mars Rover Photos API and view the weather forecast for the entire week. The project demonstrates how to consume a third-party API, handle data in a Spring application, and display it using a responsive frontend built with Thymeleaf and Bootstrap.

## Features

- Fetch Mars Rover photos by selecting a specific Earth date.
- Display the photos along with their associated information (such as the date they were taken).
- Show the weather forecast for the entire week, day by day.
- Responsive and user-friendly UI using Thymeleaf and Bootstrap.

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Thymeleaf**
- **Bootstrap**
- **NASA Mars Rover Photos API**
- **NASA InSight: Mars Weather Service API**

## How It Works

1. The user selects an Earth date using a date picker.
2. Upon form submission, the application sends a GET request to NASA's Mars Rover Photos API using the selected date.
3. The photos, if available, are displayed along with the date.
4. The application also fetches and displays the weather forecast for the selected date and the following days of the week.
5. The application uses Thymeleaf to render the HTML view, and Bootstrap for a simple, responsive UI.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/linimary/mars-explorer.git
    ```

2. Navigate into the project directory:
    ```bash
    cd mars-explorer
    ```

3. Update the `application.properties` file with your API keys:
    ```properties
    nasa.api.key=YOUR_NASA_API_KEY
    weather.api.key=YOUR_WEATHER_API_KEY
    ```

4. Build and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

5. Open your browser and go to:
    ```
    http://localhost:8080/
    ```

## Folder Structure

- **domain**: Contains DTOs and services related to the project.
- **infrastructure**: Contains exception handling classes.
- **web**: Contains controllers for handling web requests.

## API Integration

The application integrates with the NASA Mars Rover Photos API to fetch images taken by the Mars Rovers on specific Earth dates, and NASA InSight Mars Weather Service API to display the weekly weather forecast. You can learn more about the NASA API [here](https://api.nasa.gov) and the weather API documentation.

## Future Enhancements

- Add filtering by Mars Rover (Curiosity, Opportunity, Spirit).
- Display more metadata about each photo (camera type, rover information).
- Improve weather display by including additional weather metrics (temperature, humidity, etc.).

## Screenshots

![home](https://github.com/user-attachments/assets/fac3c5f6-bbe8-4989-96d5-36c76d9f1faa)

![rover-photos-date-picker](https://github.com/user-attachments/assets/6da47500-2cf0-45ce-a6e8-6f87fd4f7db1)

![rover-photos](https://github.com/user-attachments/assets/55bbebe2-b638-4f38-914d-e1bc7121391d)

![weather-data](https://github.com/user-attachments/assets/a54a68ce-8881-42bf-84fc-65522c324f25)
