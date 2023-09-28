package main.java.demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
public class IMDbTopMovies {
    public WebDriver driver;
    public IMDbTopMovies() 
    { WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
        public void endTest() 
        {
            System.out.println("End Test: TestCases");
            driver.close();
            driver.quit();
        }
        public void testcase03() 
        
        System.out.println("Start Test case: testCase03");
        // Navigate to IMDb Top 250 page
        driver.get("https://www.imdb.com/chart/top");

        // Find all movie rows in the table
        List<WebElement> movieRows = driver.findElements(By.xpath("//h1[@class='ipc-title__text chart-layout-specific-title-text']"));

        // Task 1: Find the highest rated movie on IMDb
        WebElement highestRatedMovie = movieRows.get(0); // The first row has the highest rating
        String highestRatedMovieTitle = highestRatedMovie.findElement(By.xpath("//h3[@class='ipc-title__text']")).getText();
        System.out.println("Highest rated movie on IMDb: " + highestRatedMovieTitle);

        // Task 2: Find the number of movies in the table
        int numberOfMovies = movieRows.size();
        System.out.println("Number of movies in the table: " + numberOfMovies);

        // Task 3: Find the oldest movie on the list
        WebElement oldestMovie = movieRows.get(numberOfMovies - 1); // The last row has the oldest release year
        String oldestMovieTitle = oldestMovie.findElement(By.xpath("//*[@id="__next"]/main/div/div[3]/section/div/div[2]/div/ul/li[198]/div[2]")).getText();
        System.out.println("Oldest movie on the list: " + oldestMovieTitle);

        // Task 4: Find the most recent movie on the list
        WebElement recentMovie = movieRows.get(0); // The first row has the most recent release year
        String recentMovieTitle = recentMovie.findElement(By.xpath("//*[@id="__next"]/main/div/div[3]/section/div/div[2]/div/ul/li[19]/div[2]/div/div")).getText();
        System.out.println("Most recent movie on the list: " + recentMovieTitle);

        // Task 5: Find the movie with the most user ratings
        WebElement mostRatedMovie = null;
        int maxUserRatings = 0;

        for (WebElement movieRow : movieRows) {
            int userRatings = Integer.parseInt(movieRow.findElement(By.xpath(".//td[@class='ratingColumn imdbRating']/strong")).getText().replaceAll(",", ""));
            if (userRatings > maxUserRatings) {
                mostRatedMovie = movieRow;
                maxUserRatings = userRatings;
            }
        }
        String mostRatedMovieTitle = mostRatedMovie.findElement(By.xpath("//*[@id="__next"]/main/div/div[3]/section/div/div[2]/div/ul/li[1]/div[2]/div/div/span/div/span")).getText();
        System.out.println("Movie with the most user ratings: " + mostRatedMovieTitle);

        // Quit the WebDriver
      //  driver.quit();
        System.out.println("End Test case: testCase03");
    }